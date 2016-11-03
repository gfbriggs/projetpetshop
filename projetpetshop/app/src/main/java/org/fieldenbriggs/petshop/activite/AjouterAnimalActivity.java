package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.request.AddAnimalRequest;
import org.fieldenbriggs.response.AnimalListResponse;
import org.joda.time.LocalDate;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
    DatePicker datePickerAnimal;
    Button addPet;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_pet);
        super.onCreate(savedInstanceState);
        // les elements
        txtTypeAnimal = (EditText) findViewById(R.id.editTypeAnimal);
        txtNomAnimal = (EditText) findViewById(R.id.editNomAnimal);
        datePickerAnimal = (DatePicker) findViewById(R.id.editDateAnimal);
        txtRaceAnimal = (EditText) findViewById(R.id.editRace);
        //Boutons
        addPet = (Button)findViewById(R.id.btnAddPet);
        cancel = (Button) findViewById(R.id.btnCancel);
        // Time to sever dem ties.


        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Calendar calendar = new GregorianCalendar(datePickerAnimal.getYear(),datePickerAnimal.getMonth(),datePickerAnimal.getDayOfMonth());
                    animalerie.getServer().addAnimal(new AddAnimalRequest(animalerie.getUtilisateurCourant().getId(),
                            txtNomAnimal.getText().toString(),txtTypeAnimal.getText().toString(),txtRaceAnimal.getText().toString(),
                            calendar.getTime())).enqueue(new Callback<AnimalListResponse>() {
                        @Override
                        public void onResponse(Call<AnimalListResponse> call, Response<AnimalListResponse> response) {
                            if(response.isSuccessful())
                            {
                                Toast.makeText(AjouterAnimalActivity.this, "L'animal : "+ response.body().getNom() + " a été ajouté!", Toast.LENGTH_SHORT).show();
                                Intent intentLoging = new Intent(getApplicationContext(), ActivitylistItems.class);
                                startActivity(intentLoging);
                            }
                            else
                            {
                                Toast.makeText(AjouterAnimalActivity.this, "Les champs doivent être bien remplit! La date doit être avant aujourd'hui.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AnimalListResponse> call, Throwable t) {
                            Toast.makeText(AjouterAnimalActivity.this, "La connection au serveur ne peut être faite!", Toast.LENGTH_SHORT).show();
                        }
                    });


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
