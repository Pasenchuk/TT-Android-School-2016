package com.ucsoftworks.networkapp.ui.main_screen;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ucsoftworks.networkapp.R;
import com.ucsoftworks.networkapp.app.App;
import com.ucsoftworks.networkapp.network.AbbreviationsApi;
import com.ucsoftworks.networkapp.network.models.Lf;
import com.ucsoftworks.networkapp.network.models.SearchResponse;
import com.ucsoftworks.networkapp.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {

    public static final int TIMEOUT = 1;
    @Inject
    AbbreviationsApi abbreviationsApi;
    @Bind(R.id.abbreviation)
    EditText abbreviation;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.list_view)
    ListView listView;
    private Observable<String> stringObservable;
    private Subscription subscription;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApp(this).getAppComponent().inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rxComplexWay();

//        rxShorterWay();

//        syncInNewThread();

//        asyncViaCall();


    }

    private void asyncViaCall() {
        abbreviationsApi
                .getResponse("kg")
                .enqueue(new Callback<List<SearchResponse>>() {
                    @Override
                    public void onResponse(Call<List<SearchResponse>> call, Response<List<SearchResponse>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<SearchResponse>> call, Throwable t) {

                    }
                });
    }

    private void syncInNewThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Response<List<SearchResponse>> execute = abbreviationsApi
                            .getResponse("kg")
                            .execute();
                    final List<SearchResponse> body = execute.body();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void rxShorterWay() {
        abbreviationsApi
                .getObservableResponse("kg")
                .subscribeOn(Schedulers.io()) //работу с сетью и преобразования выполняем в отдельном потоке
                .observeOn(AndroidSchedulers.mainThread()) //результат получаем в главном потоке
                .subscribe(new Action1<List<SearchResponse>>() {
                    @Override
                    public void call(List<SearchResponse> searchResponses) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        subscribeOnTextChange();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void rxComplexWay() {
        stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                abbreviation.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        listView.setAdapter(getStringArrayAdapter(new ArrayList<String>()));
                        subscriber.onNext(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
            }
        })
                .debounce(TIMEOUT, TimeUnit.SECONDS) //если пользователь ничего не делал 1 секунду, то обрабатываем результат ввода. В противном случае игнорируем.
                .observeOn(AndroidSchedulers.mainThread()) //следующий коллбэк переводим в UI поток (будем изменять Views)
                .filter(new Func1<String, Boolean>() { //отсекаем слишком короткие строчки
                    @Override
                    public Boolean call(String s) {
                        final boolean b = !TextUtils.isEmpty(s) && s.length() > 1;
                        if (b) {
                            setProgressIndicator(View.VISIBLE, View.GONE);
                        }
                        return b;
                    }
                })
                .observeOn(Schedulers.io());//следущее действие отслеживаем в отдельном потоке
    }

    private void subscribeOnTextChange() {
        if (stringObservable != null)
            subscription = stringObservable
                    .flatMap(new Func1<String, Observable<List<SearchResponse>>>() { //последовательно после получения новой строки (прошедшей все предыдущие фильтры) делаем запрос в сеть
                        @Override
                        public Observable<List<SearchResponse>> call(String s) {
                            return abbreviationsApi.getObservableResponse(s);
                        }
                    })
                    .map(new Func1<List<SearchResponse>, List<String>>() { //преобразуем ответ к тому формату, с которым в последствии будем работать
                        @Override
                        public List<String> call(List<SearchResponse> searchResponses) {
                            Log.d("Rx view", "flatMap List<String>");
                            if (searchResponses.size() != 1)
                                return new ArrayList<>();
                            final List<Lf> lfs = searchResponses.get(0).getLfs();
                            ArrayList<String> strings = new ArrayList<>(lfs.size());
                            for (Lf lf : lfs)
                                strings.add(lf.getLf());
                            return strings;
                        }
                    })
                    .subscribeOn(Schedulers.io()) //работу с сетью и преобразования выполняем в отдельном потоке
                    .observeOn(AndroidSchedulers.mainThread()) //результат получаем в главном потоке
                    .subscribe(new Subscriber<List<String>>() { //подписываемся на результат
                        @Override
                        public void onCompleted() {
                            Log.d("Rx view", "onCompleted");
                        } //не вызовется, так как в самом первом Observable не вызывается onComplete

                        @Override
                        public void onError(Throwable e) { //вызовется в случае какой-нибудь ошибки
                            MainFragment.this.onError(e);
                            if (isVisible())
                                subscribeOnTextChange(); //подпишемся заново - не нашёл выхода лучше =(
                        }

                        @Override
                        public void onNext(List<String> strings) {//сюда придут уже преобразованные данные

                            setProgressIndicator(View.GONE, View.VISIBLE);
                            Log.d("Rx view", "onNext");
                            if (isVisible())
                                listView.setAdapter(getStringArrayAdapter(strings));
                        }
                    });
    }

    private void onError(Throwable e) {
        setProgressIndicator(View.GONE, View.VISIBLE);
        Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }

    private void setProgressIndicator(int progress, int list) {
        if (isVisible()) {
            progressBar.setVisibility(progress);
            listView.setVisibility(list);
        }
    }

    @NonNull
    private ArrayAdapter<String> getStringArrayAdapter(List<String> strings) {
        return new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, strings);
    }
}
