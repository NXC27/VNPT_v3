package com.example.vnpt_v3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vnpt_v3.DataBaseHandler.ICallAPI;
import com.example.vnpt_v3.DataBaseHandler.ICallBack;
import com.example.vnpt_v3.DataBaseHandler.MySQLDB;
import com.example.vnpt_v3.DataBaseHandler.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class LichSuNguoiDung extends AppCompatActivity{
    RecyclerView recyclerView;
    ArrayList<String> name, date, time;
    AdapterUser adapterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_nguoidung);
        recyclerView = findViewById(R.id.recycle_view);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        Boolean get_ckBox = intent.getBooleanExtra("CKBOX", false);
        String url;

        name = new ArrayList<>();
//        name.add("Nguyen Xuan Chu");
//        name.add("Nguyen Xuan Minh Nhat");
//        name.add("Phan Tuan");

        date = new ArrayList<>();
//        date.add("27/10/2001");
//        date.add("27/11/2001");
//        date.add("27/12/2001");

        time = new ArrayList<>();
//        time.add("12:00");
//        time.add("22:10");
//        time.add("23:41");

        loadData();
        adapterUser = new AdapterUser(LichSuNguoiDung.this, name, date, time);
        recyclerView.setAdapter(adapterUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        TextView settings = (TextView) findViewById(R.id.setting);
        settings.setOnClickListener(new View.OnClickListener() {
//            Intent intent = getIntent();
//            final String get_id = intent.getStringExtra("ID");
//            final Boolean get_ckBox = intent.getBooleanExtra("CKBOX", false);
//            String url;

            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(LichSuNguoiDung.this, settings);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.menu_change_password){
                            Toast.makeText(LichSuNguoiDung.this, "changed password", Toast.LENGTH_SHORT).show();
                        } else if (item.getItemId() == R.id.menu_log_out) {
                            Toast.makeText(LichSuNguoiDung.this, "logged out", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            finishAffinity();
                            startActivity(intent);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void loadData() {
        HashMap<String,String> params = new HashMap<>();
        params.put("ID",getIntent().getStringExtra("ID"));
        String url_path = getIntent().getBooleanExtra("CKBOX",false) ? "lich_su_nhanvien.php" : "lich_su_khach.php";
        MySQLDB.getInstance(LichSuNguoiDung.this).RequestCall(
                url_path, params, true,
                new ICallBack() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            Log.d("DATAOBJ",jsonObject.toString());
                            if (jsonObject.getString("response").equals("Success"))
                            {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                JSONObject data = new JSONObject();
                                for (int i = 0 ; i < jsonArray.length() ; i++){
                                    data = jsonArray.getJSONObject(i);
                                    name.add(data.get("NAME").toString());
                                    date.add(data.get("DATE_TIME").toString().substring(0,10));
                                    time.add(data.get("DATE_TIME").toString().substring(11));
//                                    name.add(data.get("").toString());
                                    Log.d("NAME",name.toString());
                                    Log.d("DATE",date.toString());
                                    Log.d("TIME",time.toString());
                                }
                            }
                            recyclerView.setAdapter(adapterUser);
                        }catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        throw new RuntimeException(t);
//                        Log.e("Error",t.toString());
                    }
                }
        );
    }


}
