package com.cookandroid.practice11_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    Button btnResult;

    final int voteCount[] = new int[9];

    final String imgName[] = { "독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀", "이레느깡 단 베르양",
            "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들", "해변에서" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 11-5");

        gridView = (GridView) findViewById(R.id.gridView);
        btnResult = (Button) findViewById(R.id.btnResult);

        MyGridAdapter gridAdapter = new MyGridAdapter(this);
        gridView.setAdapter(gridAdapter);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);
                startActivity(intent);
            }
        });
    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        Integer[] imageID = { R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
                            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9 };
        public MyGridAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return imageID.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(300, 450));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(imageID[i]);

            final int pos = i;
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[pos]++;
                    Toast.makeText(getApplicationContext(), imgName[pos] + ": 총 " + voteCount[pos] + " 표", Toast.LENGTH_SHORT).show();
                }
            });
            return imageview;
        }
    }
}