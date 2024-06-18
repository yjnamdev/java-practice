package com.cookandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNum1, etNum2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRest;
    TextView textResult;
    String num1, num2;
    Double result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기(수정)");

        etNum1 = (EditText) findViewById(R.id.EtNum1);
        etNum2 = (EditText) findViewById(R.id.EtNum2);

        btnAdd = (Button) findViewById(R.id.BtnAdd);
        btnSub = (Button) findViewById(R.id.BtnSub);
        btnMul = (Button) findViewById(R.id.BtnMul);
        btnDiv = (Button) findViewById(R.id.BtnDiv);
        btnRest = (Button) findViewById(R.id.BtnRest);

        textResult = (TextView) findViewById(R.id.TextResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                result = Double.parseDouble(num1) + Double.parseDouble(num2);

                if (num1.trim().equals("") || num2.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "입력 값이 비었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                result = Double.parseDouble(num1) - Double.parseDouble(num2);

                if (num1.trim().equals("") || num2.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "입력 값이 비었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                result = Double.parseDouble(num1) * Double.parseDouble(num2);

                if (num1.trim().equals("") || num2.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "입력 값이 비었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                result = Double.parseDouble(num1) / Double.parseDouble(num2);

                if (num1.trim().equals("") || num2.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "입력 값이 비었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    if (num2.trim().equals("0")) {
                        Toast.makeText(getApplicationContext(),"0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        textResult.setText("계산 결과 : " + result.toString());
                    }
                }
            }
        });

        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = etNum1.getText().toString();
                num2 = etNum2.getText().toString();
                result = Double.parseDouble(num1) % Double.parseDouble(num2);

                if (num1.trim().equals("") || num2.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "입력 값이 비었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    if (num2.trim().equals("0")) {
                        Toast.makeText(getApplicationContext(),"0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        textResult.setText("계산 결과 : " + result.toString());
                    }
                }
            }
        });
    }
}