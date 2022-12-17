package com.example.application;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.application.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText loginText, passText;
    TextView error;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginText = (EditText) findViewById(R.id.LoginText);
        passText = (EditText) findViewById(R.id.PasswordText);

        error = (TextView) findViewById(R.id.textView) ;

        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
    }

    public void loginClick(View v){
        String login = loginText.getText().toString();
        String pass = passText.getText().toString();
        SQLiteDatabase database = dbHelper.getWritableDatabase();




        ContentValues contentValues = new ContentValues();

        Cursor c = database.rawQuery("SELECT * FROM UsersTB WHERE Login = ? AND Password = ?",new String [] {login,pass});
        c.moveToFirst();
        if(c.getCount() == 0)
        {
            error.setVisibility(View.VISIBLE);
            c.close();

        }
        else
        {
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("key", login);
            startActivity(intent);
            c.close();

        }



    }

    public void regAct(View v){
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
    }



}