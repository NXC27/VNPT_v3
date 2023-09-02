package com.example.vnpt_v3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vnpt_v3.DataBaseHandler.ICallAPI;
import com.example.vnpt_v3.DataBaseHandler.ICallBack;
import com.example.vnpt_v3.DataBaseHandler.MySQLDB;
import com.example.vnpt_v3.DataBaseHandler.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class LichSuNguoiDung extends AppCompatActivity{
    RecyclerView recyclerView;
    ArrayList<String> name, datetime;
    AdapterUser adapterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_nguoidung);
        recyclerView = findViewById(R.id.recycle_view);
//        name = new ArrayList<>();
//        datetime = new ArrayList<>();

        ArrayList<String> name = new ArrayList<>();
        name.add("a");
        name.add("b");
        name.add("c");
        ArrayList<String> datetime = new ArrayList<>();
        datetime.add("27/10/2001");
        datetime.add("27/11/2001");
        datetime.add("27/12/2001");

        adapterUser = new AdapterUser(this, name, datetime);
        recyclerView.setAdapter(adapterUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
