package org.fieldenbriggs.petshop.activite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.adapteur.EvenementAdapter;
import org.fieldenbriggs.petshop.model.Animalerie;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.w3c.dom.Text;

public class ItemDetailActivity extends DrawerActivity {
    Animalerie animalerie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_item_detail);
        super.onCreate(savedInstanceState);
        animalerie = Animalerie.getInstance();
        // Champs du layout
        TextView txtinputAnimal = (TextView)findViewById(R.id.inputNomAnimal);
        TextView txtInputAge = (TextView)findViewById(R.id.inputAgeAnimal);
        TextView txtInputType = (TextView)findViewById(R.id.inputTypeAnimal);
        ListView lstView = (ListView) findViewById(R.id.listEvenements);

        // On associe les champs
        txtinputAnimal.setText(animalerie.getAnimalCourant().getNom());
        txtInputAge.setText(String.valueOf(animalerie.getAnimalCourant().getAge()));
        txtInputType.setText(animalerie.getAnimalCourant().getTypeAnimal());

        // Il reste la liste des évenements associés.
        EvenementAdapter adapteur = new EvenementAdapter(ItemDetailActivity.this,animalerie.getAnimalCourant().getLstEvenements());
        lstView.setAdapter(adapteur);


    }
}
