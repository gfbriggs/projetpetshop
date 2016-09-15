package org.fieldenbriggs.petshop.activite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.model.Animalerie;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnVoirAnimaux = (Button)findViewById(R.id.btnAnimals);
        btnVoirAnimaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoirAnimaux = new Intent(getApplicationContext(), ActivitylistItems.class);
                startActivity(intentVoirAnimaux);
            }

        });
    }
}