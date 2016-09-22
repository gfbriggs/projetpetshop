package org.fieldenbriggs.petshop.activite;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.fieldenbriggs.petshop.R;

/**
 * Created by Geoffrey on 8/26/2016.
 */
public class AjouterAnimalActivity extends DrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_pet);
        super.onCreate(savedInstanceState);
        // les elements
        EditText txtTypeAnimal = (EditText) findViewById(R.id.editTypeAnimal);
        EditText txtNomAnimal = (EditText) findViewById(R.id.editNomAnimal);
        EditText txtDateAnimal = (EditText) findViewById(R.id.editDateAnimal);

        //Boutons
        Button addPet = (Button)findViewById(R.id.btnAddPet);
        Button cancel = (Button) findViewById(R.id.btnCancel);


        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            ////// TODO: 9/22/2016 Implementer les méthodes pour valider et rajouter un animal à la liste 
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quand on cancel on retourne à la liste des animaux courants.

            }
        });





    }
}
