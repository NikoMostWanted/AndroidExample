package com.example.niko.lab1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private ContactsHelper contactsHelper;
    private SQLiteDatabase sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contactsHelper = new ContactsHelper(this, "contacts.db", null, 1);
        sdb = contactsHelper.getReadableDatabase();

        name = (EditText)findViewById(R.id.editText);
        phone = (EditText)findViewById(R.id.editText2);
    }

    public void onAddClick(View view) {
        ContentValues values = new ContentValues();
        if(name.length() == 0 || phone.length() == 0) {
            Toast.makeText(this, "Error, empty field!", Toast.LENGTH_SHORT).show();
        }
        else {
            values.put(contactsHelper.NAME_COLUMN, name.getText().toString());
            values.put(contactsHelper.PHONE_COLUMN, phone.getText().toString());
            sdb.insert("contacts", null, values);
            Toast.makeText(this, "Add Contact", Toast.LENGTH_SHORT).show();
            name.setText("");
            phone.setText("");
        }

    }

}
