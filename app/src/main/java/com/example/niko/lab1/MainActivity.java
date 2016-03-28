package com.example.niko.lab1;

import android.content.Intent;
import android.graphics.Camera;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvColor, tvSize;

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColor = (TextView) findViewById(R.id.tvColor);
        tvSize = (TextView) findViewById(R.id.tvSize);

        registerForContextMenu(tvColor);
        registerForContextMenu(tvSize);

        final Button btnStart = (Button)findViewById(R.id.btn_start);
        final Button btnStop = (Button)findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(
                        new Intent(MainActivity.this, MyService.class));
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(
                        new Intent(MainActivity.this, MyService.class));
            }
        });



    }

    public void onCanvasClick(View view) {
        Intent intent = new Intent(MainActivity.this, CanvasActivity.class);
        startActivity(intent);
    }

    public void onCameraClick(View view) {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivity(intent);
    }

    public void onAddContactClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
        startActivity(intent);
    }

    public void onAnimationClick(View view) {
        Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
        startActivity(intent);
    }

    public void onViewAllContactsClick(View view) {
        Intent intent = new Intent(MainActivity.this, ViewAllActivity.class);
        startActivity(intent);
    }

    public void onSearchContactsClick(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        TextView infoTextView = (TextView) findViewById(R.id.textView);

        switch (id) {
            case R.id.action_settings:
                infoTextView.setText("We switch Setting");
                return true;
            case R.id.action_contact1:
                infoTextView.setText("We switch All Contact");
                return true;
            case R.id.action_contact2:
                infoTextView.setText("We switch Last Contact");
                return true;
            case R.id.action_cat:
                infoTextView.setText("We switch KittenFix");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.tvColor:
                menu.add(0, MENU_COLOR_RED, 0, "Red");
                menu.add(0, MENU_COLOR_GREEN, 0, "Green");
                menu.add(0, MENU_COLOR_BLUE, 0, "Blue");
                break;
            case R.id.tvSize:
                menu.add(0, MENU_SIZE_22, 0, "22");
                menu.add(0, MENU_SIZE_26, 0, "26");
                menu.add(0, MENU_SIZE_30, 0, "30");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_COLOR_RED:
                tvColor.setTextColor(Color.RED);
                tvColor.setText("Text color");
                break;
            case MENU_COLOR_GREEN:
                tvColor.setTextColor(Color.GREEN);
                tvColor.setText("Text color");
                break;
            case MENU_COLOR_BLUE:
                tvColor.setTextColor(Color.BLUE);
                tvColor.setText("Text color");
                break;
            case MENU_SIZE_22:
                tvSize.setTextSize(22);
                tvSize.setText("Text size");
                break;
            case MENU_SIZE_26:
                tvSize.setTextSize(26);
                tvSize.setText("Text size");
                break;
            case MENU_SIZE_30:
                tvSize.setTextSize(30);
                tvSize.setText("Text size");
                break;
        }
        return super.onContextItemSelected(item);
    }
}
