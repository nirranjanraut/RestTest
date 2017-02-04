package in.gauriinfotech.resttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SelectDemoActivity extends AppCompatActivity {

    private EditText textName, textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_demo);
        textName = (EditText) findViewById(R.id.textName);
        textResult = (EditText) findViewById(R.id.textResult);
    }

    public void showAll(View view) {

    }

    public void showByName(View view) {
        String name = textName.getText().toString().trim();
        if(name.isEmpty()) {
            return;
        }

    }

}