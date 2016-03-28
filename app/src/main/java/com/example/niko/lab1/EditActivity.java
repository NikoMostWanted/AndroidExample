package com.example.niko.lab1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    public EditText name;
    public EditText phone;
    private ContactsHelper contactsHelper;
    private SQLiteDatabase sdb;
    public String phone_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name = (EditText) findViewById(R.id.editText5);
        phone = (EditText) findViewById(R.id.editText6);

        Intent intent = getIntent();

        String fName = intent.getStringExtra("name");
        String fPhone = intent.getStringExtra("phone");
        phone_con = fPhone;

        name.setText(fName);
        phone.setText(fPhone);

        contactsHelper = new ContactsHelper(this, "contacts.db", null, 1);
        sdb = contactsHelper.getReadableDatabase();
    }

    public void onEditClick(View view)
    {
        ContentValues values = new ContentValues();
        if(name.length() == 0 || phone.length() == 0) {
            Toast.makeText(this, "Error, empty field!", Toast.LENGTH_SHORT).show();
        }
        else {
            values.put("name", name.getText().toString());
            values.put("phone", phone.getText().toString());
            sdb.update("contacts", values, "phone = ?",
                    new String[]{phone_con});
            sdb.close();
            Toast.makeText(this, "Type was updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditActivity.this, ViewAllActivity.class);
            startActivity(intent);
        }
    }
}
