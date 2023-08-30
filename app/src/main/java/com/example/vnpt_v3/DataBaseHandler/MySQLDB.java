package com.example.vnpt_v3.DataBaseHandler;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySQLDB {
    private static MySQLDB instance;
    private RequestQueue requestQueue;
    private static Context ctx;

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



}

// Get a RequestQueue
//RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
//        getRequestQueue();
//
//// ...
//
//// Add a request (in this example, called stringRequest) to your RequestQueue.
//MySingleton.getInstance(this).addToRequestQueue(stringRequest);
