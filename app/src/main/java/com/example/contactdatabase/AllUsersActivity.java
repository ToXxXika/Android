package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.contactdatabase.DAO.DBHelper;
import com.example.contactdatabase.Models.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class AllUsersActivity extends AppCompatActivity implements CustomAdapter.OnUpdateClickListener {

    private RecyclerView recyclerView;
    private CustomAdapter apdater ;
    private List<Utilisateur> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        DBHelper db = new DBHelper(this);
        List<Utilisateur> users  = db.getAllUsers();
        System.out.println("Taille du tableau " +users.size());
         data = new ArrayList<>();
        for (Utilisateur user:
                users
             ) {
            data.add(user);
        }
        recyclerView = findViewById(R.id.user_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apdater = new CustomAdapter(data,this);
        recyclerView.setAdapter(apdater);

    }

    @Override
    public void onUpdateClick(int position) {
        Utilisateur user = data.get(position);
        DBHelper db = new DBHelper(this);
        System.out.println("User to delete "+user);
        if(db.deleteUser(user.getEmail())){
            Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show();
            data.remove(position);
            apdater.notifyDataSetChanged();

        }else {
            Toast.makeText(this, "User not deleted", Toast.LENGTH_SHORT).show();
        }

    }
}