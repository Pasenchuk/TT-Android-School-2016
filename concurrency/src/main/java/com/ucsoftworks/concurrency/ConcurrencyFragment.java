package com.ucsoftworks.concurrency;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConcurrencyFragment extends Fragment {


    public static final int EMITS_COUNT = 5;
    @Bind(R.id.message)
    TextView message;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private Handler handler = new Handler();
    private AsyncTask<Double, Integer, String> asyncTask;
    private Timer timer;
    private Subscription subscription;

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

    @OnClick({R.id.runnable, R.id.new_thread, R.id.handler_post, R.id.async_task, R.id.timer_task, R.id.rx})
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

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 10; i++) {
                            SystemClock.sleep(1000);
                            final String text = String.valueOf(i);
                            progressBar.setProgress(i * 10);
                            postText(text);
                        }
                    }
                }).start();

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
                break;
        }
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
