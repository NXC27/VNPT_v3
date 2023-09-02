package com.example.vnpt_v3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vnpt_v3.Model.User;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.Myviewholder> {
    private Context context;
    private ArrayList name_id, datetime_id;

    public AdapterUser(Context context, ArrayList name_id, ArrayList datetime_id) {
        this.context = context;
        this.name_id = name_id;
        this.datetime_id = datetime_id;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry, parent, false);
        return new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.datetime_id.setText(String.valueOf(datetime_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView name_id;
        TextView datetime_id;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.txtName);
            datetime_id = itemView.findViewById(R.id.txtDatetime);
        }
    }
}
