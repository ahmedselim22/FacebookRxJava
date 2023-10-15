package com.selim.facebookrxjava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.selim.facebookrxjava.R;
import com.selim.facebookrxjava.pojo.Data;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    DataViewModel dataViewModel;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataViewModel= ViewModelProviders.of(this).get(DataViewModel.class);
        dataViewModel.getDataFormDb();
        DataAdapter adapter =new DataAdapter();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataViewModel.mutableLiveData.observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                adapter.setList((ArrayList<Data>) data);
            }
        });
    }
}