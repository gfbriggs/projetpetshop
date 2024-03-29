package org.fieldenbriggs.petshop.activite;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.service.AnimalerieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Geoffrey on 9/19/2016.
 */
public class DrawerActivity extends AppCompatActivity {
    AnimalerieService animalerie = AnimalerieService.getInstance();
    ActionBarDrawerToggle toggle;
    TextView header;
    NavigationView navView;
    View navHeader;
    DrawerLayout nav_drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On instancie l'objet de la nav + son drawer pour avoir le controle.
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_drawer = (DrawerLayout) findViewById(R.id.drawer);
        navHeader = nav_view.getHeaderView(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        header = (TextView)navHeader.findViewById(R.id.txtDrawerHeader);
        header.setText("Animalerie de " + animalerie.getUtilisateurCourant().getNom() + "\n Courriel:" + animalerie.getUtilisateurCourant().getCourriel() );
        // Pour aller chercher les elements de la nav
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                nav_drawer.closeDrawers();
                // On fait le tri des items du menu par leur id
                if(item.getItemId() == R.id.navigation_item_1)
                {

                    Intent intentAccueil = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentAccueil);
                }
                else if (item.getItemId() == R.id.navigation_item_2)
                {

                    Intent intentVoirAnimaux = new Intent(getApplicationContext(), ActivitylistItems.class);
                    startActivity(intentVoirAnimaux);
                }
                else if (item.getItemId() == R.id.navigation_item_3)
                {
                    Intent intentAddPet = new Intent(getApplicationContext(),AjouterAnimalActivity.class);
                    startActivity(intentAddPet);
                }
                else if (item.getItemId() == R.id.navigation_item_4)
                {
                    // On effectue la deconnection a partir du serveur.
                    AnimalerieService.getInstance().getServer().deconnecterUtilisateur().enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Intent intentLog = new Intent(getApplicationContext(), LoginActivity.class);
                            intentLog.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intentLog.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intentLog);
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(DrawerActivity.this, "Quelque chose c'est mal passé au niveau du serveur!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                return false;
            }
        });

         toggle = new ActionBarDrawerToggle(this,nav_drawer,R.string.open_drawer,R.string.close_drawer){
             @Override
             public void onDrawerOpened(View drawerView) {
                 getSupportActionBar().setTitle("Menu");
                 super.onDrawerOpened(drawerView);
             }

             @Override
             public void onDrawerClosed(View drawerView) {
                 getSupportActionBar().setTitle(R.string.app_name);
                 super.onDrawerClosed(drawerView);
             }
         };
        nav_drawer.addDrawerListener(toggle);
        toggle.syncState();



    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        toggle.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
