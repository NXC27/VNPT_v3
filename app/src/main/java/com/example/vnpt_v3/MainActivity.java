package com.example.vnpt_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

        EditText editUserName = (EditText) findViewById(R.id.editUserName);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        Button btn_submit = (Button) findViewById(R.id.btnLogin);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editUserName.getText().toString();
                String pwd = editPassword.getText().toString();
                String url;

                if(checkBox.isChecked()){
                    url = "login_nhanvien.php";
                }else{
                    url = "login_khach.php";
                }

                if (name.length() == 0 || pwd.length() == 0){
                    Toast t = Toast.makeText(getBaseContext(),"Email and Password must be fill",Toast.LENGTH_SHORT);
                    t.show();
                }else
                {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("NAME", name);
                    param.put("PASS", pwd);

                    MySQLDB.getInstance(MainActivity.this).RequestCall(url, param,
                            true, new ICallBack() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        Log.d("response",jsonObject.getString("response"));

                                        Log.d("DataRes",jsonObject.getJSONArray("data").toString());
                                        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                                        String id = jsonObject.getJSONArray("data").get(0).toString();
                                        if(jsonObject.getString("response").equals("Success")){
                                            Intent intent = new Intent(getApplicationContext(),LichSuNguoiDung.class);
                                            intent.putExtra("ID", id);
                                            intent.putExtra("CKBOX", checkBox.isChecked());
                                            finishAffinity();
                                            startActivity(intent);
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }

                                @Override
                                public void onError(Throwable t) {
                                    Log.e("Error", t.toString());
                                }
                            });
                }
            }
        });

        TextView signup = (TextView) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                finishAffinity();
                startActivity(intent);
            }
        });

    }

}