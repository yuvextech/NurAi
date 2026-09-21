package com.yuvextech.NurAi;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("An error occurred. Please check the logs.");
        textView.setPadding(50, 50, 50, 50);
        setContentView(textView);
    }
}
