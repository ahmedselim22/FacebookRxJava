package com.selim.facebookrxjava.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.selim.facebookrxjava.data.DataClient;
import com.selim.facebookrxjava.pojo.Data;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.*;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends ViewModel {
    private static final String TAG = "DataViewModel";
    MutableLiveData<List<Data>> mutableLiveData = new MutableLiveData<>();
    DataClient dataClient =DataClient.getINSTANCE();
    public void getDataFormDb(){
        Observable observable = dataClient.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<List<Data>> observer = new Observer<List<Data>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Data> value) {
                mutableLiveData.setValue(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e);
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
}
