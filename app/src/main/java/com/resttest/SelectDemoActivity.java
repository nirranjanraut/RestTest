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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.gauriinfotech.commons.Commons;
import in.gauriinfotech.commons.CustomRequest;

public class SelectDemoActivity extends AppCompatActivity {

    private static final String tag = "SelectDemo.java";
    private EditText textName, textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_demo);
        textName = (EditText) findViewById(R.id.textName);
        textResult = (EditText) findViewById(R.id.textResult);
    }

    public void showAll(View view) {
        if(!Commons.isOnline(this)) {
            Commons.toast(this, "Please connect to internet.");
            return;
        }
        String URL = "http://192.168.0.6:8080/JavaRestDemo/rest/Service/student/login";
        CustomRequest request = CustomRequest.getInstance(URL, Request.Method.GET, null, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String status = object.getString("status");
                    if("success".equals(status)) {
                        // everything is fine
                        JSONArray array = object.getJSONArray("records");
                        StringBuilder builder = new StringBuilder();
                        builder.append("USERNAME : PASSWORD\n");
                        for(int i=0;i<array.length();i++) {
                            JSONObject recordObject = array.getJSONObject(i);
                            builder.append(recordObject.getString("username")).append(" : ");
                            builder.append(recordObject.getString("password")).append("\n");
                        }
                        textResult.setText(builder.toString());
                    } else {
                        Commons.toast(SelectDemoActivity.this, status);
                    }
                } catch (Exception ex) {
                    Log.e(tag, Log.getStackTraceString(ex));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(tag, Log.getStackTraceString(error));
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void showByName(View view) {
        String name = textName.getText().toString().trim();
        if(name.isEmpty()) {
            Commons.toast(this, "Enter name.");
            return;
        }
        if(!Commons.isOnline(this)) {
            Commons.toast(this, "Please connect to internet.");
            return;
        }
        String URL = "http://192.168.0.6:8080/JavaRestDemo/rest/Service/student/login";
        Map<String, String> params = new HashMap<>();
        params.put("username", name);
        CustomRequest request = CustomRequest.getInstance(URL, Request.Method.GET, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String status = object.getString("status");
                    if("success".equals(status)) {
                        // everything is fine
                        JSONArray array = object.getJSONArray("records");
                        StringBuilder builder = new StringBuilder();
                        builder.append("USERNAME : PASSWORD\n");
                        for(int i=0;i<array.length();i++) {
                            JSONObject recordObject = array.getJSONObject(i);
                            builder.append(recordObject.getString("username")).append(" : ");
                            builder.append(recordObject.getString("password")).append("\n");
                        }
                        textResult.setText(builder.toString());
                    } else {
                        Commons.toast(SelectDemoActivity.this, status);
                    }
                } catch (Exception ex) {
                    Log.e(tag, Log.getStackTraceString(ex));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(tag, Log.getStackTraceString(error));
            }
        }, "?", "=", "&");
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}