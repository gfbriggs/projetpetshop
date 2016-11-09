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
import org.fieldenbriggs.petshop.interfaceanimalerie.IWebService;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.UtilisateurLogResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnCompte;
    EditText motDePasse;
    EditText courriel;
    IWebService server;
    TextView chargement;
    ProgressBar progress;
    Boolean isLoading;
    // On va chercher l'animalerie
    AnimalerieService animalerie = AnimalerieService.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        server = animalerie.getServer();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Identification des elements de l'interface
        courriel = (EditText) findViewById(R.id.inUser);
        motDePasse = (EditText) findViewById(R.id.inPass);
        btnLogin = (Button) findViewById(R.id.btnLog);
        btnCompte = (Button) findViewById(R.id.btnNew);
        chargement = (TextView) findViewById(R.id.prgChargement);
        progress = (ProgressBar) findViewById(R.id.progress);
        isLoading = false;
        // Reset de la barre de chargement au cas ou...
        // Listener de connexion
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        // On affiche le chargement en cours
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.setVisibility(View.VISIBLE);
                                chargement.setVisibility(View.VISIBLE);
                            }
                        });
                        if(!isLoading)
                        {
                            isLoading = true;
                        // On commence par contruire la requête
                        UtilisateurLogRequest ur = new UtilisateurLogRequest(courriel.getText().toString(), motDePasse.getText().toString());
                        server.signin(ur).enqueue(new Callback<UtilisateurLogResponse>() {
                            @Override
                            public void onResponse(Call<UtilisateurLogResponse> call, Response<UtilisateurLogResponse> response) {

                                if(response.isSuccessful())
                                {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progress.setVisibility(View.INVISIBLE);
                                            chargement.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                    // Si la requête marche on met l'utilisateur courant et on rentre dans l'application
                                    animalerie.setUtilisateurCourant(response.body());
                                    isLoading = false;
                                    Intent intentLoging = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intentLoging);
                                }
                                else
                                {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progress.setVisibility(View.INVISIBLE);
                                            chargement.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                    isLoading = false;
                                    Toast.makeText(LoginActivity.this, "Authentification Echouée! : Authentifiant ou mot de passe invalide!", Toast.LENGTH_SHORT).show();
                                }

                            }
                            @Override
                            public void onFailure(Call<UtilisateurLogResponse> call, Throwable t) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progress.setVisibility(View.INVISIBLE);
                                        chargement.setVisibility(View.INVISIBLE);
                                    }
                                });
                                t.printStackTrace();
                                isLoading = false;
                                Toast.makeText(LoginActivity.this, "Aucune connexion au serveur!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        }

            }
        });
        // Listener de creation de compte
        btnCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLog = new Intent(getApplicationContext(), AjouterUtilisateurActivity.class);
                startActivity(intentLog);
            }
        });
    }
}
