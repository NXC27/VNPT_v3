package com.example.vnpt_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.vnpt_v3.DataBaseHandler.ICallAPI;
import com.example.vnpt_v3.DataBaseHandler.ICallBack;
import com.example.vnpt_v3.DataBaseHandler.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ICallBack {

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
                if (name == "" || pwd == ""){
                    Toast.makeText(MainActivity.this,"Email and Password must be fill",Toast.LENGTH_SHORT);
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
                Intent intent = new Intent(getApplicationContext(),FirstFragment.class);
                // Sua FirstFragment.class thanh SignupActivity
                startActivity(intent);
            }
        });
    }

    private void processLogin(String name, String pwd) {
        ICallAPI loginService = RetrofitClient.getInstance().create(ICallAPI.class);
        Call<JSONObject> call = loginService.Login(name,pwd);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()){
                    MainActivity.this.onResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                MainActivity.this.onError(t);
            }
        });
    }

    @Override
    public void onResponse(JSONObject jsonObject)
    {
        System.out.println(jsonObject);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(t.getLocalizedMessage());
    }
}