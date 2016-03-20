package com.ucsoftworks.concurrency;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConcurrencyFragment extends Fragment {


    public static final int EMITS_COUNT = 5;
    @Bind(R.id.message)
    TextView message;

    @Bind(R.id.edit_text)
    EditText editText;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;


    private Handler handler = new Handler();
    private AsyncTask<Double, Integer, String> asyncTask;
    private Timer timer;
    private Subscription subscription;

    private ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    public ConcurrencyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_concurrency, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                editText.addTextChangedListener(new TextWatcher() {
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
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s) && s.length() > 2;
                    }
                })
                .debounce(2, TimeUnit.SECONDS)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("Rx", s);
                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        butterknife.ButterKnife.unbind(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        cancelAsyncTask();

        cancelTimer();

        cancelRx();

    }

    private void cancelAsyncTask() {
        if (asyncTask != null && !asyncTask.isCancelled())
            asyncTask.cancel(true);
    }

    private void cancelTimer() {
        if (timer != null)
            timer.cancel();
    }

    private void cancelRx() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    @OnClick({R.id.runnable, R.id.new_thread, R.id.handler_post, R.id.async_task, R.id.timer_task, R.id.rx, R.id.thread_pool})
    public void onClick(View view) {
        message.setText(null);
        progressBar.setProgress(0);
        switch (view.getId()) {
            case R.id.runnable:

                new Runnable() {
                    @Override
                    public void run() {
                        message.setText("From runnable!");
                    }
                }.run();

                break;
            case R.id.new_thread:

                new Thread(getRunnable()).start();

                break;
            case R.id.handler_post:
                message.setText("wait...");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        message.setText("Done!");
                        progressBar.setProgress(100);
                    }
                }, 3000);
                break;
            case R.id.async_task:

                cancelAsyncTask();

                asyncTask = new AsyncTask<Double, Integer, String>() {
                    @Override
                    protected String doInBackground(Double... doubles) {
                        for (int i = 0; i < doubles.length; i++) {
                            publishProgress(i * 100 / doubles.length);
                            SystemClock.sleep(1000);
                        }
                        return Arrays.toString(doubles);
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        super.onPostExecute(result);
                        if (isVisible()) {
                            message.setText(result);
                            progressBar.setProgress(100);
                        }
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                        if (isVisible())
                            progressBar.setProgress(values[0]);
                    }
                };

                asyncTask.execute(1d, 2d, 3d);

                break;
            case R.id.timer_task:
                cancelTimer();

                message.setText("Timer started");


                timer = new Timer();


                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (isVisible()) {
                            progressBar.setProgress(100);

                            postTextDirectly("Timer finished");
                        }

                    }
                };

                timer.schedule(timerTask, 2000);
                break;
            case R.id.rx:
                cancelRx();

                subscription = Observable
                        .interval(1, TimeUnit.SECONDS)
                        .take(EMITS_COUNT)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Long>() {
                            @Override
                            public void onCompleted() {
                                if (isVisible()) {
                                    progressBar.setProgress(100);
                                    message.setText("Rx finished");
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(Long aLong) {
                                if (isVisible()) {
                                    progressBar.setProgress((int) (aLong * 100 / EMITS_COUNT));
                                    message.setText(String.valueOf(aLong));
                                }
                            }
                        });


                Observable
                        .from(new String[]{"1", "2", "3", "4"})
                        .map(new Func1<String, Integer>() {
                            @Override
                            public Integer call(String s) {
                                return Integer.valueOf(s);
                            }
                        })
                        .filter(new Func1<Integer, Boolean>() {
                            @Override
                            public Boolean call(Integer integer) {
                                return (integer % 2) == 1;
                            }
                        })
                        .flatMap(new Func1<Integer, Observable<Void>>() {
                            @Override
                            public Observable<Void> call(Integer integer) {
                                return Observable.create(new Observable.OnSubscribe<Void>() {
                                    @Override
                                    public void call(Subscriber<? super Void> subscriber) {
                                        SystemClock.sleep(1000);
                                        Log.d("Rx", "Sent");
                                        subscriber.onNext(null);
                                        subscriber.onCompleted();
                                    }
                                });
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Void>() {
                            @Override
                            public void onCompleted() {
                                Log.d("Rx", "onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(Void aVoid) {
                                Log.d("Rx", "onNext");
                            }
                        });

                break;
            case R.id.thread_pool:
                threadPoolExecutor.submit(getRunnable());
                break;
        }
    }

    @NonNull
    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    Log.d("Background Computation", String.valueOf(i));
                    SystemClock.sleep(1000);
                    final String text = String.valueOf(i);
                    progressBar.setProgress(i * 10);
                    postText(text);
                }
            }
        };
    }

    private void postText(final String text) {
        if (isVisible())
            handler.post(new Runnable() {
                @Override
                public void run() {
                    message.setText(text);
                }
            });
    }

    private void postTextDirectly(final String text) {
        if (isVisible())
            message.post(new Runnable() {
                @Override
                public void run() {
                    message.setText(text);
                }
            });
    }
}
