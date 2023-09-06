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
    private ArrayList name_id, date_id, time_id;

    public AdapterUser(Context context, ArrayList name_id, ArrayList date_id, ArrayList time_id) {
        this.context = context;
        this.name_id = name_id;
        this.date_id = date_id;
        this.time_id = time_id;
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
        holder.date_id.setText(String.valueOf(date_id.get(position)));
        holder.time_id.setText(String.valueOf(time_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView name_id;
        TextView date_id;
        TextView time_id;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.txtName);
            date_id = itemView.findViewById(R.id.txtDate);
            time_id = itemView.findViewById(R.id.txtTime);
        }
    }
}
