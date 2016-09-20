package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.adapteur.AnimalAdapter;
import org.fieldenbriggs.petshop.mock.Data;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Animalerie;
import org.fieldenbriggs.petshop.service.AnimalerieService;

import java.util.ArrayList;
import java.util.List;

public class ActivitylistItems extends DrawerActivity {
    Animalerie animalerie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_activitylist_items);
        super.onCreate(savedInstanceState);
        // Animalerie et sa liste
        animalerie = Animalerie.getInstance();

        // Champs du layout
        ListView lstviewAnimaux = (ListView) findViewById(R.id.lstAnimaux);
        ////// TODO: 2016-09-15 Faire le add pour un animal
        Button btnAjouter = (Button) findViewById(R.id.btnAddPet);

        // On va chercher les données dans le jeux de test de datas
        animalerie.setLstAnimaux(Data.getInstance().ListeAnimaux());
        // On plug l'adapteur (On doit s'assurer que la liste n'est pas vide!)
        if (!animalerie.getLstAnimaux().isEmpty()) {
            AnimalAdapter adapteur = new AnimalAdapter(ActivitylistItems.this, animalerie.getLstAnimaux());
            lstviewAnimaux.setAdapter(adapteur);

        }
    }

        // On doit set un tag pour le bouton de details.
        public void details (View v)
        {
            animalerie.setAnimalCourant((Animal) v.getTag());
            Intent intentVoirAnimalCourant = new Intent(this.getApplicationContext(), ItemDetailActivity.class);
            startActivity(intentVoirAnimalCourant);
        }
    }

