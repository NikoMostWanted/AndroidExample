package com.example.niko.lab1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer mPlayer;
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        Toast.makeText(this, "Service create",
                Toast.LENGTH_SHORT).show();
        mPlayer = MediaPlayer.create(this, R.raw.mmdance);
        mPlayer.setLooping(false);
    }

    @Override
    public void onStart(Intent intent, int startid)
    {
        Toast.makeText(this, "Service start",
                Toast.LENGTH_SHORT).show();
        mPlayer.start();
    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Service stop",
                Toast.LENGTH_SHORT).show();
        mPlayer.stop();
    }
}
