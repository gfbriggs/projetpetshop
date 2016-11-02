package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.service.AnimalerieService;

public class MainActivity extends DrawerActivity {
    AnimalerieService animalerie = AnimalerieService.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        // On va chercher les elements de l'interface
        Button btnVoirAnimaux = (Button)findViewById(R.id.btnAnimals);
        TextView txtMsgBienvenue = (TextView)findViewById(R.id.txtWelcome);

        // On change le message de Bienvenue tout dépend de l'utilisateur courant.

            txtMsgBienvenue.setText("Bienvenue dans ton Petshop, " + ((animalerie.getUtilisateurCourant() == null)?"Fantome":animalerie.getUtilisateurCourant().getNom()));
        btnVoirAnimaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoirAnimaux = new Intent(getApplicationContext(), ActivitylistItems.class);
                startActivity(intentVoirAnimaux);
            }

        });
    }
}
