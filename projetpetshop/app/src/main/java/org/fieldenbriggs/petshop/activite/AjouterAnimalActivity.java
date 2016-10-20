package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.petshop.mock.RetrofitUtil;
import org.joda.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Geoffrey on 8/26/2016.
 */
public class AjouterAnimalActivity extends DrawerActivity {
    AnimalerieService animalerie = AnimalerieService.getInstance();
    EditText txtTypeAnimal;
    EditText txtNomAnimal;
    EditText txtDateAnimal;
    EditText txtRaceAnimal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_pet);
        super.onCreate(savedInstanceState);
        // les elements
        txtTypeAnimal = (EditText) findViewById(R.id.editTypeAnimal);
        txtNomAnimal = (EditText) findViewById(R.id.editNomAnimal);
        txtDateAnimal = (EditText) findViewById(R.id.editDateAnimal);
        txtRaceAnimal = (EditText) findViewById(R.id.editRace);
        //Boutons
        Button addPet = (Button)findViewById(R.id.btnAddPet);
        Button cancel = (Button) findViewById(R.id.btnCancel);


        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitUtil.getMock().addAnimals(new Animal(txtNomAnimal.getText().toString(),txtTypeAnimal.getText().toString(),txtRaceAnimal.getText().toString(), LocalDate.parse(txtDateAnimal.getText().toString()))).enqueue(new Callback<Animal>() {
                    @Override
                    public void onResponse(Call<Animal> call, Response<Animal> response) {
                        Toast.makeText(AjouterAnimalActivity.this, "Sucess!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Animal> call, Throwable t) {
                        Toast.makeText(AjouterAnimalActivity.this, "L'animal ne peut être ajouté", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intentLoging = new Intent(getApplicationContext(), ActivitylistItems.class);
                startActivity(intentLoging);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quand on cancel on retourne à la liste des animaux courants.
                Intent intentLoging = new Intent(getApplicationContext(), ActivitylistItems.class);
                startActivity(intentLoging);
            }
        });





    }
}
