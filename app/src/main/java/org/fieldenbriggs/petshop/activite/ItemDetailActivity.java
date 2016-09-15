package org.fieldenbriggs.petshop.activite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.model.Animalerie;
import org.fieldenbriggs.petshop.service.AnimalerieService;

public class ItemDetailActivity extends AppCompatActivity {
    Animalerie animalerie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        animalerie = Animalerie.getInstance();
        // Champs du layout
        TextView txtinputAnimal = (TextView)findViewById(R.id.inputNomAnimal);
        TextView txtInputAge = (TextView)findViewById(R.id.inputAgeAnimal);

        // On associe les champs
        txtinputAnimal.setText(animalerie.getAnimalCourant().getNom());
        ////// TODO: 9/15/2016  Trouver une façon de output l'age
       // txtInputAge.setText(Integer.toString(animalerie.getAnimalCourant().getAge()));
    }
}
