package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegActivity extends AppCompatActivity {
    DBHelper dbHelper;
    TextView error ;
    EditText login, mail, pass, rpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        dbHelper = new DBHelper(this);

        login = (EditText)findViewById(R.id.loginText);
        mail = (EditText) findViewById(R.id.emailText);
        pass = (EditText) findViewById(R.id.passText);
        rpass = (EditText) findViewById(R.id.repPassText);

        error = (TextView) findViewById(R.id.errorText);



    }

    public void goBackbtt(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void regBtt(View v){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String slogin = login.getText().toString();
        String smail = mail.getText().toString();
        String spass = pass.getText().toString();
        String srPass = rpass.getText().toString();

        if (slogin.matches("") || smail.matches("") || spass.matches("") || srPass.matches("")) {
            error.setText("Проверьте введенные данные!");
            error.setVisibility(View.VISIBLE);

        }
        else{
            Cursor c = db.rawQuery("SELECT * FROM UsersTB WHERE (Login = ?) OR (Email = ?)",new String [] {slogin,smail});
            c.moveToFirst();
            if(c.getCount() != 0)
            {
                error.setText("Такие логин или почта уже используются");
                error.setVisibility(View.VISIBLE);
                c.close();

            }
            else{
                if(!srPass.matches(spass)){
                    error.setText("Пароли не совпадают");
                    error.setVisibility(View.VISIBLE);
                }
                else{
                    ContentValues cont = new ContentValues();
                    cont.put(DBHelper.KEY_LOGIN , slogin);
                    cont.put(DBHelper.EMAIL, smail);
                    cont.put(DBHelper.PASSWORD , spass);

                    db.insert(DBHelper.TABLE_USERS, null , cont);
                    Intent intent = new Intent(this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        }

    }
}