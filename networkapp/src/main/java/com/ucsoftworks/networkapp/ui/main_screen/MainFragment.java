package com.ucsoftworks.networkapp.ui.main_screen;


import android.os.Bundle;
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
import android.widget.AutoCompleteTextView;

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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {

    @Inject
    AbbreviationsApi abbreviationsApi;
    @Bind(R.id.abbreviation)
    AutoCompleteTextView abbreviation;
    private ArrayAdapter<String> autocompleteAdapter;

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


        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                abbreviation.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        subscriber.onNext(charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
            }
        })
                .debounce(2, TimeUnit.SECONDS)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s) && s.length() > 1;
                    }
                })
                .flatMap(new Func1<String, Observable<List<SearchResponse>>>() {
                    @Override
                    public Observable<List<SearchResponse>> call(String s) {
                        return abbreviationsApi.getResponse(s);
                    }
                })
                .flatMap(new Func1<List<SearchResponse>, Observable<SearchResponse>>() {

                    @Override
                    public Observable<SearchResponse> call(List<SearchResponse> searchResponses) {
                        Log.d("Rx view", "flatMap List<SearchResponse>");
                        return Observable.from(searchResponses);
                    }
                })
                .map(new Func1<SearchResponse, List<String>>() {
                    @Override
                    public List<String> call(SearchResponse searchResponse) {
                        Log.d("Rx view", "flatMap List<String>");
                        final List<Lf> lfs = searchResponse.getLfs();
                        ArrayList<String> strings = new ArrayList<>(lfs.size());
                        for (Lf lf : lfs)
                            strings.add(lf.getLf());
                        return strings;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Rx view", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        Log.d("Rx view", "onNext");

                        autocompleteAdapter = new ArrayAdapter<>(getActivity(),
                                android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
                        abbreviation.setAdapter(autocompleteAdapter);
                        abbreviation.showDropDown();
                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
