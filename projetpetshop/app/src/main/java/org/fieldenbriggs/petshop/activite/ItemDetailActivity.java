package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.adapteur.AnimalAdapter;
import org.fieldenbriggs.petshop.adapteur.EvenementAdapter;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.response.AnimalDetailResponse;
import org.fieldenbriggs.response.GetEvenementResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    ListView lstEvenements;
    LinearLayout pgrLayout;
    LinearLayout detailLayout;
    ProgressBar prgLand;
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
         lstEvenements = (ListView) findViewById(R.id.lstEvenements);
         pgrLayout =(LinearLayout) findViewById(R.id.prgDetail);
         detailLayout = (LinearLayout) findViewById(R.id.detailAnimauxView);
         prgLand = (ProgressBar) findViewById(R.id.prgDetect);
        // Si jamais on detect la barre en landscape on revient à l'activité d'avant
        if(prgLand != null)
        {
            Intent intentListe = new Intent(getApplicationContext(), ActivitylistItems.class);
            intentListe.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentListe.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentListe);
        }
        else
        {
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
                        detailLayout.setVisibility(View.VISIBLE);
                        pgrLayout.setVisibility(View.GONE);
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

            // On va en même temps chercher la liste des evenements
            animalerie.getServer().getEvenements(animalerie.getAnimalCourant().getId()).enqueue(new Callback<List<GetEvenementResponse>>() {
                @Override
                public void onResponse(Call<List<GetEvenementResponse>> call, Response<List<GetEvenementResponse>> response) {
                    if(response.isSuccessful())
                    {

                        EvenementAdapter adapteur = new EvenementAdapter(ItemDetailActivity.this, response.body());
                        lstEvenements.setAdapter(adapteur);
                    }
                    else
                    {
                        Toast.makeText(ItemDetailActivity.this, "La liste d'evenement ne peut être chargée!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<GetEvenementResponse>> call, Throwable t) {
                    Toast.makeText(ItemDetailActivity.this, "La connection au server à échouée!", Toast.LENGTH_SHORT).show();
                }
            });
        }





    }
}
