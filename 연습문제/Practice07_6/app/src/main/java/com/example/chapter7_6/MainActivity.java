package com.example.chapter7_6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button btn;
    RadioButton rdoDog, rdoCat, rdoRabbit, rdoHorse;
    RadioGroup rdoGroup;
    ImageView img;
    View dlgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 7-6");

        btn = (Button) findViewById(R.id.btn);
        rdoDog = (RadioButton) findViewById(R.id.rdoDog);
        rdoCat = (RadioButton) findViewById(R.id.rdoCat);
        rdoRabbit = (RadioButton) findViewById(R.id.rdoRabbit);
        rdoHorse = (RadioButton) findViewById(R.id.rdoHorse);
        rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlgView = (View) View.inflate(MainActivity.this, R.layout.dialog, null) ;;
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                 img = (ImageView) dlgView.findViewById(R.id.ivAnimal);
                dlg.setView(dlgView);

                switch(rdoGroup.getCheckedRadioButtonId()) {
                    case R.id.rdoDog:
                        dlg.setTitle("강아지");
                        img.setImageResource(R.drawable.dog);
                        break;
                    case R.id.rdoCat:
                        dlg.setTitle("고양이");
                        img.setImageResource(R.drawable.cat);
                    case R.id.rdoRabbit:
                        dlg.setTitle("토끼");
                        img.setImageResource(R.drawable.rabbit);
                    case R.id.rdoHorse:
                        dlg.setTitle("말");
                        img.setImageResource(R.drawable.horse);
                        break;
                }
                dlg.setPositiveButton("닫기", null);
                dlg.show();
            }
        });
    }

}