package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.service.AnimalerieService;

public class AjouterUtilisateurActivity extends AppCompatActivity {
    AnimalerieService animalerie = AnimalerieService.getInstance();
    EditText txtNomUtilisateur;
    EditText txtCourrielUtilisateur;
    EditText txtMotDePasseUtilisateur;
    Button btnAjouterUtilisateur;
    Button btnAnnuler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_utilisateur);

        // Les elements du layout
        txtNomUtilisateur = (EditText)findViewById(R.id.editUtilisateurAjout);
        txtCourrielUtilisateur = (EditText)findViewById(R.id.editCourrielAjout);
        txtMotDePasseUtilisateur = (EditText)findViewById(R.id.editMotDePasseAjout);

        btnAjouterUtilisateur = (Button)findViewById(R.id.buttonAjouterUtilisateur);
        btnAnnuler = (Button) findViewById(R.id.buttonAnnulerAjoutUtilisateur);

        //Listeners

        btnAjouterUtilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animalerie.ajouterUtilisateur(txtNomUtilisateur.getText().toString(),txtCourrielUtilisateur.getText().toString(),txtMotDePasseUtilisateur.getText().toString());
                Toast.makeText(AjouterUtilisateurActivity.this, "Utilisateur ajouté!", Toast.LENGTH_SHORT).show();
                Intent intentLog = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentLog);
            }
        });

        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLog = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentLog);
            }
        });
    }
}
