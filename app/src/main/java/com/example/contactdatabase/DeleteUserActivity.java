package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactdatabase.DAO.DBHelper;

public class DeleteUserActivity extends AppCompatActivity {


    EditText msg;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        msg = findViewById(R.id.email_edit_text);
        btn = findViewById(R.id.submit_button);
        DBHelper db = new DBHelper(this);

        btn.setOnClickListener(v->{
            String email = msg.getText().toString();
           if( db.deleteUser(email)){
               Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(DeleteUserActivity.this, AllUsersActivity.class);
           }else{
               Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
           }
        });
    }
}