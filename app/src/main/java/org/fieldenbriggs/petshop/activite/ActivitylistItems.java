package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.adapteur.AnimalAdapter;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.service.AnimalerieService;

public class ActivitylistItems extends DrawerActivity {
    AnimalerieService animalerie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_activitylist_items);
        super.onCreate(savedInstanceState);
        animalerie = AnimalerieService.getInstance();

        // Champs du layout
        ListView lstviewAnimaux = (ListView) findViewById(R.id.lstAnimaux);
        Button btnAjouter = (Button) findViewById(R.id.btnAddPet);

        // On va chercher les données dans le jeux de test de datas
        animalerie.remplirListeAnimaux();
        // On plug l'adapteur (On doit s'assurer que la liste n'est pas vide!)
        if (!animalerie.getLstAnimaux().isEmpty()) {
            AnimalAdapter adapteur = new AnimalAdapter(ActivitylistItems.this, animalerie.getLstAnimaux());
            lstviewAnimaux.setAdapter(adapteur);

        }

        // Pour le bouton de add
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddPet = new Intent(getApplicationContext(),AjouterAnimalActivity.class);
                startActivity(intentAddPet);
            }
        });

    }

        // On doit set un tag pour le bouton de details.
        public void details (View v)
        {
            animalerie.setAnimalCourant((Animal) v.getTag());
            Intent intentVoirAnimalCourant = new Intent(this.getApplicationContext(), ItemDetailActivity.class);
            startActivity(intentVoirAnimalCourant);
        }





    }

