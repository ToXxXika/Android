package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button btnlist,btndel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnlist = findViewById(R.id.button1);
        btndel = findViewById(R.id.button2);
        btnlist.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, AllUsersActivity.class);
            startActivity(intent);
        });
        btndel.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, DeleteUserActivity.class);
            startActivity(intent);
        });
    }
}