package org.fieldenbriggs.petshop.activite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.adapteur.AnimalAdapter;
import org.fieldenbriggs.petshop.mock.Data;
import org.fieldenbriggs.petshop.model.Animal;

import java.util.List;

public class ActivitylistItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitylist_items);
        /**
         * La liste courante des animaux de l'application
         */
        List<Animal> lstAnimaux;

        // On va chercher les éléments du layout

        ListView lstviewAnimaux = (ListView) findViewById(R.id.lstAnimaux);
        Button btnAjouter = (Button) findViewById(R.id.btnAddPet);


        // TEMPORAIRE POUR UNE LISTE MOCK
        lstAnimaux = Data.getInstance().ListeAnimaux();
        // On plug l'adapteur (On doit s'assurer que la liste est vide!)
        if (!lstAnimaux.isEmpty()) {
            AnimalAdapter adapteur = new AnimalAdapter(ActivitylistItems.this, lstAnimaux);
            lstviewAnimaux.setAdapter(adapteur);
        }
    }


        // On doit set un tag pour le bouton de details.
        public void details (View v)
        {

        }
    }

