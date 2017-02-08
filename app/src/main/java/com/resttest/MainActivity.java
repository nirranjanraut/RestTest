package com.resttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectDemo(View view) {
        Intent intent = new Intent(this, SelectDemoActivity.class);
        startActivity(intent);
    }

    public void showInsertDemo(View view) {
        Intent intent = new Intent(this, InsertDemoActivity.class);
        startActivity(intent);
    }

}