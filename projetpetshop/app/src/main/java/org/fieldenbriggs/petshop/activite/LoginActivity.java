package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.interfaceanimalerie.IWebService;
import org.fieldenbriggs.petshop.model.Utilisateur;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.petshop.mock.RetrofitUtil;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.UtilisateurLogResponse;

import java.io.Console;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnCompte;
    EditText motDePasse;
    EditText courriel;
    IWebService server;
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

        // Dans la page de log l'utilisateur courant est par default null avant d'être authentifié.
        animalerie.setUtilisateurCourant(null);

        // Listener de connexion
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 // On commence par contruire la requête
                UtilisateurLogRequest ur = new UtilisateurLogRequest(courriel.getText().toString(), motDePasse.getText().toString());

                // On fait la requête
                server.signin(ur).enqueue(new Callback<UtilisateurLogResponse>() {
                    @Override
                    public void onResponse(Call<UtilisateurLogResponse> call, Response<UtilisateurLogResponse> response) {
                        if(response.isSuccessful())
                        {
                            // Si la requête marche on met l'utilisateur courant et on rentre dans l'application
                            animalerie.setUtilisateurCourant(response.body());
                            Intent intentLoging = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intentLoging);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Authentification Echouée!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<UtilisateurLogResponse> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Aucune connexion au serveur!", Toast.LENGTH_SHORT).show();
                    }
                });

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
