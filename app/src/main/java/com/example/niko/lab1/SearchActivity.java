package com.example.niko.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    private EditText name;
    private EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        name = (EditText)findViewById(R.id.editText3);
        phone = (EditText)findViewById(R.id.editText4);

        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        name.setVisibility(View.VISIBLE);
                        phone.setVisibility(View.GONE);
                        break;
                    case R.id.radioButton2:
                        name.setVisibility(View.GONE);
                        phone.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void onSearchClick(View view)
    {
        Toast.makeText(this, "Why you touch this?", Toast.LENGTH_SHORT).show();
    }

}
