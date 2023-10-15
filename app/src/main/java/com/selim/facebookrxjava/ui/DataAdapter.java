package com.selim.facebookrxjava.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selim.facebookrxjava.R;
import com.selim.facebookrxjava.pojo.Data;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    ArrayList<Data> list = new ArrayList<>();
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_layout,parent,false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        int id =list.get(position).getId();
        int userId =list.get(position).getUserId();
        String title =list.get(position).getTitle();
        String body =list.get(position).getBody();
        holder.tv_id.setText(id+"");
        holder.tv_userId.setText(userId+"");
        holder.tv_title.setText(title);
        holder.tv_body.setText(body);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder{
        TextView tv_id ,tv_userId,tv_title ,tv_body;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id=itemView.findViewById(R.id.tv_id);
            tv_userId=itemView.findViewById(R.id.tv_userId);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_body=itemView.findViewById(R.id.tv_body);
        }
    }

    public void setList(ArrayList<Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
