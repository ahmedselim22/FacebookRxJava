package com.selim.facebookrxjava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.selim.facebookrxjava.R;
import com.selim.facebookrxjava.databinding.ActivityMainBinding;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                binding.edittext.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length()>0)
                            emitter.onNext(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        }).doOnNext(A-> Log.d(TAG, "ahmed: upstream "+A))
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Throwable {
                        return Integer.parseInt(o.toString())*2;
                    }
                })
                .distinctUntilChanged()
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Throwable {
                        Observable observable =Observable.just("hello form second !" +o.toString());
                        return observable;
                    }
                })
                .subscribe(S-> Log.d(TAG, "ahmed: downstream "+S));
    }
}