package com.example.practice13_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnStart, btnPause, btnStop;
    TextView tvMP3, tvTime;
    ProgressBar pbMP3;

    ArrayList<String> mp3List;
    String selectedMP3;

    MediaPlayer mPlayer;
    String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/";

    boolean PAUSED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 13-6");
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        listView = (ListView) findViewById(R.id.listView);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnStop = (Button) findViewById(R.id.btnStop);
        tvMP3 = (TextView) findViewById(R.id.tvMP3);
        tvTime = (TextView) findViewById(R.id.tvTime);
        pbMP3 = (ProgressBar) findViewById(R.id.pbMP3);

        mp3List = new ArrayList<String>();

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;
        for (File file : listFiles) {
            if (file.isDirectory() == false) {
                fileName = file.getName();
                extName = fileName.substring(fileName.length() - 3);
                if (extName.equals((String) "mp3")) {
                    mp3List.add(fileName);
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setItemChecked(0, true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMP3 = mp3List.get(i);
            }
        });
        selectedMP3 = mp3List.get(0);

        Handler mp3Handler = new Handler() {
            SimpleDateFormat timeFormat = new SimpleDateFormat( " mm:ss ");
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                pbMP3.setMax(mPlayer.getDuration());
                if(mPlayer==null) {
                    return;
                }
                pbMP3.setProgress(mPlayer.getCurrentPosition());
                tvTime.setText("진행 시간: " + timeFormat.format(mPlayer.getCurrentPosition()));
                this.sendEmptyMessageDelayed(0, 200);
            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    mPlayer.prepare();
                    mPlayer.start();
                    btnStart.setClickable(false);
                    btnPause.setClickable(true);
                    btnStop.setClickable(true);
                    tvMP3.setText("실행중인 음악 : " + selectedMP3);

                    mp3Handler.sendEmptyMessage(0);

                    /*
                    new Thread() {
                        SimpleDateFormat timeFormat = new SimpleDateFormat( " mm:ss ");
                        public void run() {
                            if(mPlayer==null)
                                return;
                            pbMP3.setMax(mPlayer.getDuration());
                            while (mPlayer.isPlaying()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pbMP3.setProgress(mPlayer.getCurrentPosition());
                                        tvTime.setText("진행 시간: " + timeFormat.format(mPlayer.getCurrentPosition()));
                                    }
                                });
                                SystemClock.sleep(200);
                            }
                        }
                    }.start();
                    */
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PAUSED == false) {
                    mPlayer.pause();
                    btnPause.setText("이어듣기");
                    PAUSED = true;
                    pbMP3.setVisibility(View.INVISIBLE);
                } else {
                    mPlayer.start();
                    PAUSED = false;
                    btnPause.setText("일시정지");
                    pbMP3.setVisibility(View.VISIBLE);

                    new Thread() {
                        SimpleDateFormat timeFormat = new SimpleDateFormat( " mm:ss ");
                        public void run() {
                            if(mPlayer==null) {
                                return;
                            }
                            while (mPlayer.isPlaying()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pbMP3.setProgress(mPlayer.getCurrentPosition());
                                        tvTime.setText("진행 시간 : " + timeFormat.format(mPlayer.getCurrentPosition()));
                                    }
                                });
                                SystemClock.sleep(200);
                            }
                        }
                    }.start();
                }
            }
        });
        btnPause.setClickable(false);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                mPlayer.reset();
                btnStart.setClickable(true);
                btnStop.setClickable(false);
                btnPause.setClickable(false);
                tvMP3.setText("실행중인 음악 : ");
                btnPause.setText("일시정지");
                tvTime.setText("진행 시간 : ");
                //pbMP3.setVisibility(View.INVISIBLE);
                pbMP3.setProgress(0);
            }
        });
        btnStop.setClickable(false);

    }
}