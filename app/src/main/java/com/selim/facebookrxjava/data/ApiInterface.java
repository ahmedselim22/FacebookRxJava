package com.selim.facebookrxjava.data;

import com.selim.facebookrxjava.pojo.Data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("posts")
    public Observable<List<Data>> getData();
}
