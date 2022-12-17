package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText note;
    DBHelper dbHelper;
    String login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbHelper = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        login = extras.getString("key");

        note = (EditText) findViewById(R.id.editTextTextMultiLine2);


    }
    public void gobckBtt(View v){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void addBtt(View v){
        String snote = note.getText().toString();

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String rightNow = Calendar.getInstance().toString();
            ContentValues cont = new ContentValues();
            cont.put(DBHelper.KEY_LOGINS, login);
            cont.put(DBHelper.DATE, rightNow);
            cont.put(DBHelper.KEY_NOTE , snote);
            cont.put(DBHelper.STATUS , "public");


            db.insert(DBHelper.TABLE_NOTES, null , cont);
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);







    }
}