package com.cookandroid.practice14_4;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {
    MediaPlayer mp;

    ArrayList<String> mp3List = new ArrayList<String>();
    int selectedMP3 = 0;

    String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        android.util.Log.i("서비스 테스트", "onCreate()");

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;
        for (File file : listFiles) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3);
            if (extName.equals((String) "mp3"))
                mp3List.add(fileName);
        }
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        android.util.Log.i("서비스 테스트", "onDestroy()");
        mp.stop();
        mp.reset();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.i("서비스 테스트", "onStartCommand()");

        mp = new MediaPlayer();
        try {
            mp.setDataSource(mp3Path + mp3List.get(selectedMP3));
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.reset();
                if (selectedMP3 >= mp3List.size() - 1) {
                    selectedMP3 = 0;
                    try {
                        mp.setDataSource(mp3Path + mp3List.get(selectedMP3));
                        mp.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mp.start();
                } else {
                    selectedMP3++;
                    try {
                        mp.setDataSource(mp3Path + mp3List.get(selectedMP3));
                        mp.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mp.start();
                }
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }
}
