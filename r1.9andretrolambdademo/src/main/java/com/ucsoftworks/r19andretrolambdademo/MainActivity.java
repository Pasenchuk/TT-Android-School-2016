package com.ucsoftworks.r19andretrolambdademo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<SearchResponse> kg = getApi().getResponseSync("kg");

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //handle response
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //handle error
                        }
                    });
                }
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
                .subscribe(new Action1<List<SearchResponse>>() {
                    @Override
                    public void call(List<SearchResponse> searchResponses) {
                        //handle response
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //handle error
                    }
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
