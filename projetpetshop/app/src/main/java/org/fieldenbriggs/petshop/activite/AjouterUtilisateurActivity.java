package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.model.Utilisateur;
import org.fieldenbriggs.petshop.mock.RetrofitUtil;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.request.AddUtilisateurRequest;
import org.fieldenbriggs.response.UtilisateurLogResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjouterUtilisateurActivity extends AppCompatActivity {

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
                // On fait le package
                AddUtilisateurRequest addUser = new AddUtilisateurRequest(txtCourrielUtilisateur.getText().toString(),txtNomUtilisateur.getText().toString(),txtMotDePasseUtilisateur.getText().toString());
                AnimalerieService.getInstance().getServer().adduser(addUser).enqueue(new Callback<UtilisateurLogResponse>() {
                    @Override
                    public void onResponse(Call<UtilisateurLogResponse> call, Response<UtilisateurLogResponse> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(AjouterUtilisateurActivity.this, "Utilisateur ajouté!", Toast.LENGTH_SHORT).show();
                            AnimalerieService.getInstance().setUtilisateurCourant(response.body());
                            Intent intentLog = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intentLog);
                        }
                        else
                        {
                            Toast.makeText(AjouterUtilisateurActivity.this, "L'utilisateur ne peut être ajouté!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UtilisateurLogResponse> call, Throwable t) {
                        Toast.makeText(AjouterUtilisateurActivity.this, "Erreur de connection!", Toast.LENGTH_SHORT).show();
                    }
                });

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
