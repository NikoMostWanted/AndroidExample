package com.example.niko.lab1;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class AnimationActivity extends AppCompatActivity {
    private ImageView imageView;
    private Animation animOne, animTwo, animThree;

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;
    private int mCatSound, mChickenSound, mCowSound;
    private int mStreamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        imageView = (ImageView) findViewById(R.id.imageView);

        Button btn1 = (Button) findViewById(R.id.button8);
        Button btn2 = (Button) findViewById(R.id.button9);
        Button btn3 = (Button) findViewById(R.id.button10);


        animOne = AnimationUtils.loadAnimation(this, R.anim.animation_one);
        animTwo = AnimationUtils.loadAnimation(this, R.anim.animation_two);
        animThree = AnimationUtils.loadAnimation(this, R.anim.animation_three);

        btn1.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                if (eventAction == MotionEvent.ACTION_UP) {
                    if (mStreamID > 0)
                        mSoundPool.stop(mStreamID);
                }
                if (eventAction == MotionEvent.ACTION_DOWN) {
                    mStreamID = playSound(mChickenSound);
                }
                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    mSoundPool.stop(mStreamID);
                }
                return true;
            }
        });

        btn2.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                if (eventAction == MotionEvent.ACTION_UP) {
                    if (mStreamID > 0)
                        mSoundPool.stop(mStreamID);
                }
                if (eventAction == MotionEvent.ACTION_DOWN) {
                    mStreamID = playSound(mCowSound);
                }
                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    mSoundPool.stop(mStreamID);
                }
                return true;
            }
        });

        btn3.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                if (eventAction == MotionEvent.ACTION_UP) {
                    if (mStreamID > 0)
                        mSoundPool.stop(mStreamID);
                }
                if (eventAction == MotionEvent.ACTION_DOWN) {
                    mStreamID = playSound(mCatSound);
                }
                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    mSoundPool.stop(mStreamID);
                }
                return true;
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    playSound(mCowSound);
                    break;
                case R.id.button2:
                    playSound(mChickenSound);
                    break;
                case R.id.button3:
                    playSound(mCatSound);
                    break;
            }
        }
    };

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }

    private int playSound(int sound) {
        if (sound > 0) {
            mStreamID = mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Can't find file" + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            createOldSoundPool();
        } else {
            createNewSoundPool();
        }

        mAssetManager = getAssets();

        mCatSound = loadSound("cat.ogg");
        mChickenSound = loadSound("chicken.ogg");
        mCowSound = loadSound("cow.ogg");

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSoundPool.release();
        mSoundPool = null;
    }

    public void onAnimationImageOneClick(View v){
        imageView.startAnimation(animOne);
    }


    public void onAnimationImageTwoClick(View v){
        imageView.startAnimation(animTwo);
    }


    public void onAnimationImageThreeClick(View v){
        imageView.startAnimation(animThree);
    }
}
