package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.graphics.Region;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.model.Utilisateur;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.petshop.service.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    // On va chercher l'animalerie
    AnimalerieService animalerie = AnimalerieService.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // On va chercher la liste d'utilisateurs

        RetrofitUtil.getMock().users().enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                animalerie.setLstUtilisteurs(response.body());
            }
            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable t) {
                Log.e("projetpetshop", "onFailure: Error mock utilisateurs! " );
            }
        });
        // Identification des elements de l'interface
        final EditText courriel = (EditText) findViewById(R.id.inUser);
        final EditText motDePasse = (EditText) findViewById(R.id.inPass);
        Button btnLogin = (Button) findViewById(R.id.btnLog);
        Button btnCompte = (Button) findViewById(R.id.btnNew);
        // Identification des textfields
       // EditText txtUtil = (EditText) findViewById(R.id.)
        // Listener de connexion
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(animalerie.motDePasseValide(motDePasse.toString() , animalerie.getUtilisateur(courriel.toString()))
                        || (courriel.toString().equals("")  && courriel.toString().equals("") ))
                {
                    Intent intentLoging = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentLoging);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Authentification echouer!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener de creation de compte
        btnCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Pas encore implementé!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
