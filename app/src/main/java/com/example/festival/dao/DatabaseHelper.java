package com.example.festival.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nom de la base de données
    private static final String DATABASE_VERSION = "user_roles.db";

    // Version de la base de données
    private static final int DATA_VERSION = 1;

    // Commande SQL pour créer les tables User et Role
    private static final String CR2EATE_TABLE_USER =
            "CREATE TABLE User (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT NOT NULL, " +
                    "prenom TEXT NOT NULL, " +
                    "email TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "roleId INTEGER, " +
                    "FOREIGN KEY(roleId) REFERENCES Role(id))";

    private static final String CREATE_TABLE_ROLE =
            "CREATE TABLE Role (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "roleName TEXT NOT NULL)";
    private static final String CREATE_TABLE_USER = ;

    // Constructeur
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // Création des tables User et Role
        db.execSQL(CREATE_TABLE_ROLE);
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newversion){
        // Gérer maj du schéma de bdd
        //if oldVersion<2
    }
}
