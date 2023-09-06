package com.example.vnpt_v3.DataBaseHandler;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MySQLDB {


    private String url = "http://10.128.101.129/Web/android/";
    private static MySQLDB instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private static JsonObjectRequest jsonObjectRequest;

    private MySQLDB(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySQLDB getInstance(Context context) {
        if (instance == null) {
            instance = new MySQLDB(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public <T,V> void RequestCall(String url_path, @Nullable HashMap<T,V> params,
                                  boolean Method_PnG, final ICallBack callBack)
    {
        if (Method_PnG)
        {
            //                        Log.d("Response Volley",response.toString());
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    url + url_path,
                    new JSONObject(params),
                    (Response.Listener<JSONObject>) jsonObject -> callBack.onResponse(jsonObject),
                    (Response.ErrorListener) callBack::onError
            );
        }
        else
        {
            jsonObjectRequest = new JsonObjectRequest(
                    url + url_path,
                    (Response.Listener<JSONObject>) response -> callBack.onResponse(response),
                    (Response.ErrorListener) error -> callBack.onError(error)
            );
        }
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.d("URL",url+url_path);
        getRequestQueue().add(jsonObjectRequest);
    }

    public void RequestCall1(String url_path, @Nullable HashMap<String,String> params,
                                  boolean Method_PnG, final ICallBack callBack)
    {
        StringRequest stringRequest;
        if (Method_PnG)
        {
            stringRequest = new StringRequest(1,
                    url + url_path,
                    response -> {
                        Log.d("Responsea1",response);
                        try {
                            callBack.onResponse(new JSONObject(response));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> callBack.onError(error)
            ){
                @androidx.annotation.Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return  params;
                }
            };

        }
        else
        {
            stringRequest = new StringRequest(
                    url + url_path,
                    response -> {
                        try {
                            callBack.onResponse(new JSONObject(response));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> callBack.onError(error)
            );
        }
        Log.d("URL",url+url_path);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(stringRequest);
    }
}

// Get a RequestQueue
//RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
//        getRequestQueue();
//
//// ...
//
//// Add a request (in this example, called stringRequest) to your RequestQueue.
//MySingleton.getInstance(this).addToRequestQueue(stringRequest);
