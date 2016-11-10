package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.request.AddUtilisateurRequest;
import org.fieldenbriggs.response.UtilisateurLogResponse;
import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjouterUtilisateurActivity extends AppCompatActivity {

    EditText txtNomUtilisateur;
    EditText txtCourrielUtilisateur;
    EditText txtMotDePasseUtilisateur;
    Button btnAjouterUtilisateur;
    Button btnAnnuler;
    Boolean onLoading;
    ProgressBar progressBar;
    TextView txtLoading;
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
        progressBar = (ProgressBar) findViewById(R.id.chargementUser);
        txtLoading = (TextView) findViewById(R.id.chargementTxt);
        onLoading = false;
        //Listeners

        btnAjouterUtilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!onLoading)
                {
                    onLoading = true;
                    // On rend la barre de progrès visible
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.VISIBLE);
                            txtLoading.setVisibility(View.VISIBLE);
                        }
                    });
                // On fait le package
                    try
                    {
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
                            Toast.makeText(AjouterUtilisateurActivity.this, "L'utilisateur ne peut être ajouté! : Vérifier si les champs sont bon!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UtilisateurLogResponse> call, Throwable t) {
                        Toast.makeText(AjouterUtilisateurActivity.this, "Erreur de connection!", Toast.LENGTH_SHORT).show();
                    }

                });
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally {
                        // Après la requête le tout ne load plus donc on mets le loading a false.
                        onLoading = false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                txtLoading.setVisibility(View.GONE);
                            }
                        });
                    }
                }
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
