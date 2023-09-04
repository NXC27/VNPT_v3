package com.example.vnpt_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edname = (EditText) findViewById(R.id.editUserName);
        EditText edpassword = (EditText) findViewById(R.id.editPassword);

        Button btn_submit = (Button) findViewById(R.id.btnLogin);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edname.getText().toString().toLowerCase();
                String pwd = edpassword.getText().toString();
                if (name.length() == 0 || pwd.length() == 0){
                    Toast t = Toast.makeText(getBaseContext(),"Email and Password must be fill",Toast.LENGTH_SHORT);
                    t.show();
                }else
                {
                    processLogin(name,pwd);
                }
            }
        });

        TextView signup = (TextView) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                // Sua FirstFragment.class thanh SignupActivity
                startActivity(intent);
            }
        });
    }

    private void processLogin(String name, String pwd) {
        String url = "http://192.168.1.146/API/";
//        String url = "IP/login.php";
        HashMap<String,String> params = new HashMap<>();
        params.put("EMAIL","Nguyễn Xuân Chủ");
        params.put("PASSWORD","123");
        MySQLDB.getInstance(getBaseContext()).RequestCall("login.php", params, true, new ICallBack() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    Log.d("response",jsonObject.getString("response"));
                    Log.d("DataRes",jsonObject.getJSONArray("data").toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onError(Throwable t) {
                Log.e("Error Response",t.toString());
            }
        });
//        StringRequest stringRequest = new StringRequest(1, "http://192.168.1.146/API/login.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(getBaseContext(),"Success",Toast.LENGTH_SHORT).show();
//                        Log.d("Response", response);
//                    }
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Response Error",error.toString());
//            }
//        });
//        Log.d("URL","http://192.168.1.146/API/login.php");
//        MySQLDB.getInstance(getBaseContext()).addToRequestQueue(stringRequest);
    }
}