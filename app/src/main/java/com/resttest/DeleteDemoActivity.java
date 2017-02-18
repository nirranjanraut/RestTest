package com.resttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import in.gauriinfotech.commons.Commons;
import in.gauriinfotech.commons.CustomRequest;

public class DeleteDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_demo);
    }

    public void deleteRecords(View view) {
        String URL = "http://192.168.0.4:8080/JavaRestDemo/rest/Service/student/login/username/niranjan/eq";// /lt or /gt
        CustomRequest request = CustomRequest.getInstance(URL, Request.Method.DELETE, null, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Commons.toast(DeleteDemoActivity.this, "Response : " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Commons.toast(DeleteDemoActivity.this, Log.getStackTraceString(error));
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}