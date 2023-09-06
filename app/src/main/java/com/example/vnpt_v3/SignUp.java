package com.example.vnpt_v3;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vnpt_v3.DataBaseHandler.ICallBack;
import com.example.vnpt_v3.DataBaseHandler.MySQLDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SignUp extends AppCompatActivity {

    Calendar calendar;
    Calendar calendar1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ki);

        EditText editUserName = (EditText) findViewById(R.id.editUserName);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);
        EditText editDate = (EditText) findViewById(R.id.editDate);
        EditText editTimein = (EditText) findViewById(R.id.editTimein);
        EditText editTimeout = (EditText) findViewById(R.id.editTimeout);
        EditText editReason = (EditText) findViewById(R.id.editReason);
        List<EditText> lstEditText = new ArrayList<>();
        Button btnSignup = (Button) findViewById(R.id.btnSignup);

        TextView login = (TextView) findViewById(R.id.login);

        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                editDate.setText(sdf.format(calendar.getTime()));
            }
        };
        TimePickerDialog.OnTimeSetListener timein = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar1.set(Calendar.MINUTE, minute);
                SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
                editTimein.setText(stf.format(calendar1.getTime()));
            }
        };
        TimePickerDialog.OnTimeSetListener timeout = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar2.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar2.set(Calendar.MINUTE, minute);
                SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
                editTimeout.setText(stf.format(calendar2.getTime()));
            }
        };

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new DatePickerDialog(SignUp.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editTimein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new TimePickerDialog(SignUp.this, timein, calendar1.get(Calendar.HOUR_OF_DAY), calendar1.get(Calendar.MINUTE), true).show();
            }
        });

        editTimeout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new TimePickerDialog(SignUp.this, timeout, calendar2.get(Calendar.HOUR_OF_DAY), calendar2.get(Calendar.MINUTE), true).show();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editUserName.length()==0 || editPassword.length()==0 ||
                editDate.length()==0 || editTimein.length()==0 || editTimeout.length()==0){
                    Toast.makeText(SignUp.this, "Vui Long Dien Day Du Thong Tin", Toast.LENGTH_SHORT).show();
                }else {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("ID", generateRandomId());
                    param.put("NAME", editUserName.getText().toString().toLowerCase());
                    param.put("PASS", editPassword.getText().toString());
                    param.put("DATE", editDate.getText().toString());
                    param.put("TIMEIN", editTimein.getText().toString());
                    param.put("TIMEOUT", editTimeout.getText().toString());
                    param.put("REASON", editReason.getText().toString());
                    MySQLDB.getInstance(SignUp.this).RequestCall("signup.php", param,
                            true, new ICallBack() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    try {
                                        Log.d("response", jsonObject.getString("response"));
                                        Log.d("DataRes", jsonObject.getJSONArray("data").toString());
                                        Toast.makeText(SignUp.this, "Success", Toast.LENGTH_SHORT).show();
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
    }

    private String generateRandomId() {
        String chars = "0123456789ABCDEF";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(chars.length());
            result.append(chars.charAt(index));
        }

        return result.toString();
    }
}
