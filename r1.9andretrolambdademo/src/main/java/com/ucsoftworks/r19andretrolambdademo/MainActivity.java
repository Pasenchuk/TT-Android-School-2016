package com.ucsoftworks.r19andretrolambdademo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ucsoftworks.r19andretrolambdademo.models.SearchResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://nactem.ac.uk/software/acromine/";
    public static final String SEARCH_STRING = "kg";

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxAndLambdas();

        rxAndSubscriber();

        manualThreading();

        retrofitsCallback();

    }

    private void retrofitsCallback() {
        getApi()
                .getResponse(SEARCH_STRING, new Callback<List<SearchResponse>>() {
                    @Override
                    public void success(List<SearchResponse> searchResponses, Response response) {
                        //handle response
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //handle error
                    }
                });
    }

    private void manualThreading() {
        new Thread(() -> {
            try {
                final List<SearchResponse> kg = getApi().getResponseSync("kg");

                handler.post(() -> {
                    //handle response
                });
            } catch (Exception e) {
                e.printStackTrace();
                handler.post(() -> {
                    //handle error
                });
            }
        }).start();
    }

    private void rxAndSubscriber() {
        getApi()
                .getObservableResponse("kg")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<SearchResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //handle error
                    }

                    @Override
                    public void onNext(List<SearchResponse> searchResponses) {
                        //handle response
                    }
                });
    }

    private void rxAndLambdas() {
        getApi()
                .getObservableResponse("kg")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResponses -> {
                    //handle response
                }, throwable -> {
                    //handle error
                });
    }


    private AbbreviationsApi getApi() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        return restAdapter.create(AbbreviationsApi.class);
    }
}
