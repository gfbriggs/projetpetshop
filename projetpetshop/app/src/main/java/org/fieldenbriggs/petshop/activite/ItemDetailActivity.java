package org.fieldenbriggs.petshop.activite;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.adapteur.EvenementAdapter;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.response.AnimalDetailResponse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailActivity extends DrawerActivity {
    AnimalerieService animalerie;
    TextView txtinputAnimal;
    TextView txtInputAge;
    TextView txtInputType;
    TextView txtInputRace;
    TextView txtUtilisateur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_item_detail);
        super.onCreate(savedInstanceState);
        animalerie = AnimalerieService.getInstance();
        // Champs du layout
         txtinputAnimal = (TextView)findViewById(R.id.inputNomAnimal);
         txtInputAge = (TextView)findViewById(R.id.inputAgeAnimal);
         txtInputType = (TextView)findViewById(R.id.inputTypeAnimal);
         txtInputRace = (TextView) findViewById(R.id.inputRace);
         txtUtilisateur = (TextView) findViewById(R.id.inputUsername);
        
        // On va chercher l'animal courant avec l'item de list choisi.

        animalerie.getServer().getAnimalDetail(animalerie.getAnimalCourant().getId()).enqueue(new Callback<AnimalDetailResponse>() {
            @Override
            public void onResponse(Call<AnimalDetailResponse> call, Response<AnimalDetailResponse> response) {
                if(response.isSuccessful())
                {
                    String dateNaissance;
                    txtinputAnimal.setText(response.body().getNom());
                    dateNaissance = new SimpleDateFormat("yyyy-MM-dd").format(response.body().getDateDeNaissance());
                    txtInputAge.setText(dateNaissance);
                    txtInputType.setText(response.body().getType());
                    txtInputRace.setText(response.body().getRace());
                    txtUtilisateur.setText(animalerie.getUtilisateurCourant().getNom());
                }
                else
                {
                    Toast.makeText(ItemDetailActivity.this, "L'animal choisi n'est pas disponible! : " + response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AnimalDetailResponse> call, Throwable t) {
                Toast.makeText(ItemDetailActivity.this, "La connection au serveur à échouée!", Toast.LENGTH_SHORT).show();
            }
        });
        // On associe les champs





    }
}
