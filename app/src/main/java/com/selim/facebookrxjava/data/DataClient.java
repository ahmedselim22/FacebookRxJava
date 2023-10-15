package com.selim.facebookrxjava.data;

import com.selim.facebookrxjava.pojo.Data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataClient {
    private ApiInterface apiInterface;
    private static DataClient INSTANCE;
    private static final String BASE_URL ="https://jsonplaceholder.typicode.com/";

    public DataClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiInterface=retrofit.create(ApiInterface.class);
    }

    public static DataClient getINSTANCE() {
        if (INSTANCE==null)
            return new DataClient();
        return INSTANCE;
    }

    public Observable<List<Data>> getData(){
        return apiInterface.getData();
    }

}
