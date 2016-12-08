package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.adapteur.AnimalAdapter;
import org.fieldenbriggs.petshop.adapteur.EvenementAdapter;
import org.fieldenbriggs.petshop.service.AnimalerieService;
import org.fieldenbriggs.response.AnimalDetailResponse;
import org.fieldenbriggs.response.AnimalListResponse;
import org.fieldenbriggs.response.GetEvenementResponse;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitylistItems extends DrawerActivity {
    AnimalerieService animalerie = AnimalerieService.getInstance();
    TextView txtAnimauxDe;
    ListView lstviewAnimaux;
    String txtanimauxDeChaine;
    long idUtilisateurCourant;
    ProgressBar progressBar;

    // Pour la moitier detail landscape
    TextView txtinputAnimal;
    TextView txtInputAge;
    TextView txtInputType;
    TextView txtInputRace;
    TextView txtUtilisateur;
    ListView lstEvenements;
    LinearLayout viewDetails;
    boolean enPaysage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // On va chercher les données dans le jeux de test de datas
        setContentView(R.layout.activity_activitylist_items);
        super.onCreate(savedInstanceState);
        idUtilisateurCourant = animalerie.getUtilisateurCourant().getId();
        // Champs du layout Liste
        lstviewAnimaux = (ListView) findViewById(R.id.lstAnimaux);
        lstviewAnimaux.setVisibility(View.GONE);
        Button btnAjouter = (Button) findViewById(R.id.btnAddPet);
        txtAnimauxDe = (TextView) findViewById(R.id.txtAnimauxDe);
        progressBar = (ProgressBar) findViewById(R.id.chargementanimaux);
        txtanimauxDeChaine = ("Animaux de " + animalerie.getUtilisateurCourant().getNom());
        txtAnimauxDe.setText(txtanimauxDeChaine);

        // Champs du layout DETAIL
        txtinputAnimal = (TextView)findViewById(R.id.inputNomAnimald);
        txtInputAge = (TextView)findViewById(R.id.inputAgeAnimald);
        txtInputType = (TextView)findViewById(R.id.inputTypeAnimald);
        txtInputRace = (TextView) findViewById(R.id.inputRaced);
        txtUtilisateur = (TextView) findViewById(R.id.inputUsernamed);
        lstEvenements = (ListView) findViewById(R.id.lstEvenementsd);
        viewDetails = (LinearLayout) findViewById(R.id.viewDetails);
        // On set le bool pour detecter le mode paysage
        enPaysage = viewDetails != null;
        // Liste animaux version server
        AnimalerieService.getInstance().getServer().getAnimaux(idUtilisateurCourant).enqueue(new Callback<List<AnimalListResponse>>() {
            @Override
            public void onResponse(Call<List<AnimalListResponse>> call, Response<List<AnimalListResponse>> response) {

                if(response.isSuccessful())
                {
                    animalerie.setLstAnimaux(response.body());
                    AnimalAdapter adapteur = new AnimalAdapter(ActivitylistItems.this, animalerie.getLstAnimaux());
                    lstviewAnimaux.setAdapter(adapteur);
                    progressBar.setVisibility(View.GONE);
                    lstviewAnimaux.setVisibility(View.VISIBLE);
                }
                else
                {
                    Intent intentLog = new Intent(getApplicationContext(),LoginActivity.class);
                    intentLog.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentLog.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentLog);
                }

            }

            @Override
            public void onFailure(Call<List<AnimalListResponse>> call, Throwable t) {
                Toast.makeText(ActivitylistItems.this, "La liste d'animaux ne peut être récupérée!", Toast.LENGTH_SHORT).show();
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
            if(enPaysage)
            {
                Toast.makeText(this, "Je suis en paysage!", Toast.LENGTH_SHORT).show();
                animalerie.setAnimalCourant((AnimalListResponse) v.getTag());
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
                            viewDetails.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Toast.makeText(ActivitylistItems.this, "L'animal choisi n'est pas disponible! : " + response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AnimalDetailResponse> call, Throwable t) {
                        Toast.makeText(ActivitylistItems.this, "La connection au serveur à échouée!", Toast.LENGTH_SHORT).show();
                    }
                });

                // On va en même temps chercher la liste des evenements
                animalerie.getServer().getEvenements(animalerie.getAnimalCourant().getId()).enqueue(new Callback<List<GetEvenementResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetEvenementResponse>> call, Response<List<GetEvenementResponse>> response) {
                        if(response.isSuccessful())
                        {

                            EvenementAdapter adapteur = new EvenementAdapter(ActivitylistItems.this, response.body());
                            lstEvenements.setAdapter(adapteur);
                        }
                        else
                        {
                            Toast.makeText(ActivitylistItems.this, "La liste d'evenement ne peut être chargée!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GetEvenementResponse>> call, Throwable t) {
                        Toast.makeText(ActivitylistItems.this, "La connection au server à échouée!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else
            {
                animalerie.setAnimalCourant((AnimalListResponse) v.getTag());
                Intent intentVoirAnimalCourant = new Intent(this.getApplicationContext(), ItemDetailActivity.class);
                startActivity(intentVoirAnimalCourant);
            }

        }





    }

