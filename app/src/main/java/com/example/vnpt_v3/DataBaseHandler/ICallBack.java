package com.example.vnpt_v3.DataBaseHandler;

import org.json.JSONObject;

public interface ICallBack {
    void onResponse(JSONObject jsonObject);
    void onError(Throwable t);
}
