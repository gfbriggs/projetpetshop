package org.fieldenbriggs.petshop.activite;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.fieldenbriggs.petshop.R;


/**
 * Created by Geoffrey on 9/19/2016.
 */
public class DrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On instancie l'objet de la nav + son drawer pour avoir le controle.
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout nav_drawer = (DrawerLayout) findViewById(R.id.drawer);

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

                    //// TODO: 9/19/2016 Don't forget to set the user to null on this ples.
                    //AnimalerieService.getInstance();
                    Intent intentLog = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentLog);
                }
                return false;
            }
        });

    }
}
