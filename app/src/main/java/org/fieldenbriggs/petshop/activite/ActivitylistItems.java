package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.adapteur.AnimalAdapter;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.petshop.service.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitylistItems extends DrawerActivity {
    AnimalerieService animalerie = AnimalerieService.getInstance();
    TextView txtAnimauxDe;
    ListView lstviewAnimaux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // On va chercher les données dans le jeux de test de datas
        setContentView(R.layout.activity_activitylist_items);
        super.onCreate(savedInstanceState);


        // Champs du layout
        lstviewAnimaux = (ListView) findViewById(R.id.lstAnimaux);
        Button btnAjouter = (Button) findViewById(R.id.btnAddPet);
        txtAnimauxDe = (TextView) findViewById(R.id.txtAnimauxDe);
        txtAnimauxDe.setText("Animaux de " + ((animalerie.getUtilisateurCourant() == null)?"Fantome":animalerie.getUtilisateurCourant().getNom()));

        // On va chercher la liste des animaux
          RetrofitUtil.getMock().animals().enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                animalerie.setLstAnimaux(response.body());
                AnimalAdapter adapteur = new AnimalAdapter(ActivitylistItems.this, animalerie.getLstAnimaux());
                lstviewAnimaux.setAdapter(adapteur);
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.e("projetpetshop", "onFailure: Error mock animaux! " );
            }
        });

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

