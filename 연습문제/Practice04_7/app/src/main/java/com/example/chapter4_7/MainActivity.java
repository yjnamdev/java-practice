package com.example.chapter4_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    CheckBox chkEnbl, chkClck, chkDeg;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 4-7");

        chkEnbl = (CheckBox) findViewById(R.id.chkEnbl);
        chkClck = (CheckBox) findViewById(R.id.chkClck);
        chkDeg = (CheckBox) findViewById(R.id.chkDeg);

        btn = (Button) findViewById(R.id.btn);

        chkEnbl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true) {
                    btn.setEnabled(true);
                } else {
                    btn.setEnabled(false);
                }
            }
        });

        chkClck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true) {
                    btn.setClickable(true);
                } else {
                    btn.setClickable(false);
                }
            }
        });

        chkDeg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true) {
                    btn.setRotation(45);
                } else {
                    btn.setRotation(0);
                }
            }
        });
    }
}