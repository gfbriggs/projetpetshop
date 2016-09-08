package org.fieldenbriggs.petshop.activite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.fieldenbriggs.petshop.R;
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

        // TEMPORAIRE POUR UNE LISTE MOCK
        lstAnimaux = Data.getInstance().ListeAnimaux();


    }
}
