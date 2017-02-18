package com.resttest;

import android.content.DialogInterface;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.gauriinfotech.commons.Commons;
import in.gauriinfotech.commons.CustomRequest;
import in.gauriinfotech.commons.Progress;

public class InsertDemoActivity extends AppCompatActivity {

    private EditText textUsername, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_demo);
        textUsername = (EditText) findViewById(R.id.textUsername);
        textPassword = (EditText) findViewById(R.id.textPassword);
    }

    public void save(View view) {
        String username = textUsername.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        if(username.isEmpty()) {
            Commons.toast(this, "Please enter username.");
            return;
        }
        if(password.isEmpty()) {
            Commons.toast(this, "Please enter password.");
            return;
        }
        if(!Commons.isOnline(this)) {
            Commons.toast(this, "Please connect to internet.");
            return;
        }
        String URL = "http://192.168.0.7:8080/JavaRestDemo/rest/Service/student/login";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        final Progress progress = Progress.from(this).title("Please wait")
                .message("Inserting into database...")
                .cancelable(true)
                .onCancel(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Commons.toast(InsertDemoActivity.this, "Cancelled.");
                    }
                });
        progress.display();
        CustomRequest request = CustomRequest.getInstance(URL, Request.Method.POST, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progress.dismiss();
                Commons.toast(InsertDemoActivity.this, response);
                try {
                    JSONObject object = new JSONObject(response);
                    if("success".equals(object.getString("status"))) {
                        Commons.toast(InsertDemoActivity.this, "Record inserted successfully.");
                        textUsername.setText(null);
                        textPassword.setText(null);
                        textUsername.requestFocus();
                    } else {
                        Commons.toast(InsertDemoActivity.this, object.getString("status"));
                    }
                } catch (Exception ex) {
                    Log.e("InsertDemo", Log.getStackTraceString(ex));
                }
                Log.e("Insert", "Response : " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                Log.e("Insert", Log.getStackTraceString(error));
            }
        }, "?", "=", "&");
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}