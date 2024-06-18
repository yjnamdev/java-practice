package com.cookandroid.practice12_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    EditText edtDiary;
    Button btnWrite;
    MyDBHelper myHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 12-6");

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        myHelper = new MyDBHelper(this);

        sqLiteDatabase = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT content FROM myDiary WHERE diaryDate = "
                + Integer.toString(cYear) + Integer.toString(cMonth + 1) + Integer.toString(cDay) + ";", null);
        if (cursor.getCount() == 0) {
            edtDiary.setText("");
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        } else {
            cursor.moveToNext();
            edtDiary.setText(cursor.getString(0));
            btnWrite.setText("수정하기");
        }
        cursor.close();
        sqLiteDatabase.close();
        btnWrite.setEnabled(true);

        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                sqLiteDatabase = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqLiteDatabase.rawQuery("SELECT content FROM myDiary WHERE diaryDate = '"
                        + Integer.toString(i) + Integer.toString(i1 + 1) + Integer.toString(i2) + "';", null);
                if (cursor.getCount() == 0) {
                    edtDiary.setText("");
                    edtDiary.setHint("일기 없음");
                    btnWrite.setText("새로 저장");
                } else {
                    cursor.moveToNext();
                    edtDiary.setText(cursor.getString(0));
                    btnWrite.setText("수정하기");
                }
                cursor.close();
                sqLiteDatabase.close();
                btnWrite.setEnabled(true);
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnWrite.getText().equals("수정하기")) {
                    sqLiteDatabase = myHelper.getWritableDatabase();
                    sqLiteDatabase.execSQL("UPDATE myDiary SET content = '" + edtDiary.getText().toString() + "' WHERE diaryDate = '" 
                            + Integer.toString(cYear) + Integer.toString(cMonth + 1) + Integer.toString(cDay) + "';");
                    sqLiteDatabase.close();
                    Toast.makeText(getApplicationContext(), Integer.toString(cYear) + Integer.toString(cMonth + 1) + Integer.toString(cDay) + " 이 수정됨", Toast.LENGTH_SHORT).show();
                } else {
                    sqLiteDatabase = myHelper.getWritableDatabase();
                    sqLiteDatabase.execSQL("INSERT INTO myDiary (diaryDate, content) VALUES ('" + Integer.toString(cYear) + Integer.toString(cMonth + 1) + Integer.toString(cDay) + "','" + edtDiary.getText().toString() + "');");
                    sqLiteDatabase.close();
                    Toast.makeText(getApplicationContext(), Integer.toString(cYear) + Integer.toString(cMonth + 1) + Integer.toString(cDay) + " 이 저장됨", Toast.LENGTH_SHORT).show();
                    btnWrite.setText("수정하기");
                }
            }
        });
    }

    public class MyDBHelper extends SQLiteOpenHelper {
        public MyDBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE myDiary(diaryDate CHAR(10) PRIMARY KEY, content VARCHAR(500));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS myDiary;");
            onCreate(sqLiteDatabase);
        }
    }
}