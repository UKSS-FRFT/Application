package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    public void addBtt(View v){
        Intent intent = new Intent(this, AddActivity.class);
        Bundle extras = getIntent().getExtras();
        String login = extras.getString("key");
        intent.putExtra("key", login);
        startActivity(intent);
    }

    public void gobckBtt(View V){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}