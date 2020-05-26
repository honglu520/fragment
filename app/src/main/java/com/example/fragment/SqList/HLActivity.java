package com.example.fragment.SqList;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fragment.R;

import org.litepal.tablemanager.Connector;

public class HLActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hl);
        onclick();
    }

    private void onclick() {
        button=findViewById(R.id.hl);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getWritableDatabase();
            }
        });
    }
}
