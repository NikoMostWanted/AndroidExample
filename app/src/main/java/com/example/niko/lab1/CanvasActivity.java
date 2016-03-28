package com.example.niko.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Draw2D draw2D = new Draw2D(this);
        setContentView(draw2D);
    }
}
