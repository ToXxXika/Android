package com.example.contactdatabase.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.contactdatabase.Models.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "contact.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Utilisateur(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, prenom TEXT, email TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Utilisateur");

    }

    public Boolean insertuserdata(String nom, String prenom, String email, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", nom);
        contentValues.put("prenom", prenom);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = DB.insert("Utilisateur", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean verifyuser(String mail, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", mail);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("Select * from Utilisateur where email = ? and password = ?", new String[]{mail, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Utilisateur> getAllUsers() {
        List<Utilisateur> users = new ArrayList<>();
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Utilisateur", null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Utilisateur user = new Utilisateur();
                user.setNom(cursor.getString(1));
                user.setPrenom(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPassword(cursor.getString(4));
                users.add(user);
            }
        }
        return users;
    }
    public boolean deleteUser(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.delete("Utilisateur", "email = ?", new String[]{email}) > 0;
    }
}

