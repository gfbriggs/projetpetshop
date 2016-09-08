package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Identification des boutons
        Button btnLogin = (Button) findViewById(R.id.btnLog);
        Button btnCompte = (Button) findViewById(R.id.btnNew);
        // Identification des textfields
       // EditText txtUtil = (EditText) findViewById(R.id.)
        // Listener de connexion
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 2016-09-01 Faire eventuellement une vérification d'utilisateur
                Intent intentLoging = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentLoging);
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
