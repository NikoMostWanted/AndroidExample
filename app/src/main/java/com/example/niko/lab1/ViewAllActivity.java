package com.example.niko.lab1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewAllActivity extends AppCompatActivity {
    private ContactsHelper contactsHelper;
    private SQLiteDatabase sdb;
    private ListView listView;
    final int MENU_EDIT = 1;
    final int MENU_DELETE = 2;
    public Cursor cursor;
    public String[] names;
    public String[] phones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        updateListView();
    }

    public void updateListView()
    {
        listView = (ListView)findViewById(R.id.listView);
        registerForContextMenu(listView);

        contactsHelper = new ContactsHelper(this, "contacts.db", null, 1);
        sdb = contactsHelper.getWritableDatabase();
        cursor = sdb.query("contacts", new String[]{ContactsHelper.NAME_COLUMN,
                        ContactsHelper.PHONE_COLUMN},
                null, null,
                null, null, null) ;

        int count = cursor.getCount();
        String[] contacts = new String[count];
        names = new String[count];
        phones = new String[count];
        int i = 0;
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsHelper.NAME_COLUMN));
            int phoneNumber = cursor.getInt(cursor.getColumnIndex(ContactsHelper.PHONE_COLUMN));
            contacts[i] = name+" : "+phoneNumber;
            names[i] = name;
            phones[i] = String.valueOf(phoneNumber);
            i++;
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, contacts);

        listView.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listView) {
            menu.add(0, MENU_EDIT, 0, "Edit");
            menu.add(0, MENU_DELETE, 0, "Delete");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_EDIT:
                AdapterView.AdapterContextMenuInfo acmi1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Intent intent = new Intent(ViewAllActivity.this, EditActivity.class);
                intent.putExtra("name",names[((int) acmi1.id)]);
                intent.putExtra("phone", phones[((int) acmi1.id)]);
                startActivity(intent);
                break;
            case MENU_DELETE:
                AdapterView.AdapterContextMenuInfo acmi2 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                sdb.delete("contacts", "phone = ? AND name = ?", new String[] {phones[((int) acmi2.id)], names[((int) acmi2.id)]});
                sdb.close();
                Toast.makeText(this, names[((int) acmi2.id)]+" : "+phones[((int) acmi2.id)]+" was deleted", Toast.LENGTH_SHORT).show();
                updateListView();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
