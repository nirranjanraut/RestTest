package com.resttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import in.gauriinfotech.commons.Commons;
import in.gauriinfotech.commons.CustomRequest;

public class UpdateDemoActivity extends AppCompatActivity {

    private EditText textName, textPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_demo);
        textName = (EditText) findViewById(R.id.textName);
        textPassword = (EditText) findViewById(R.id.textPassword);
    }

    public void update(View view) {
        String name = textName.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        String URL = "http://192.168.1.100:8080/JavaRestDemo/rest/Service/student/login/username/"+name+"/eq";
        Map<String, String> params = new HashMap<>();
        params.put("password", password);
        CustomRequest request = CustomRequest.getInstance(URL, Request.Method.PUT, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("UpdateDemo", response);
                Commons.toast(UpdateDemoActivity.this, "Success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Commons.toast(UpdateDemoActivity.this, "Failed");
                Log.e("UpdateDemo", Log.getStackTraceString(error));
            }
        }, "?", "=", "&");
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}