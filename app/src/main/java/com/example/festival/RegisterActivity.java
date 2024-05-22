package com.example.festival;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.festival.dao.UserDao;
import com.example.festival.entity.User;


public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextNom, editTextPrenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
        private void registerUser() {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String nom = editTextNom.getText().toString().trim();
            String prenom = editTextPrenom.getText().toString().trim();

            // Validation des champs (à implémenter)
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password); // Pensez à hasher le mot de passe pour la sécurité
            newUser.setNom(nom);
            newUser.setPrenom(prenom);

            UserDao.saveUser(newUser);

            // Affichage d'un message de succès
            Toast.makeText(this, "Utilisateur enregistré avec succès", Toast.LENGTH_SHORT).show();

            // Redirection vers MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Termine RegisterActivity pour empêcher l'utilisateur de revenir à cette activité en appuyant sur le bouton retour
        }



    }
