package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityKarkardMahanehBoloersVaIstgahPomPazh extends AppCompatActivity {

    CardView cdBoloers,cdPomps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karkard_mahaneh_boloers_va_istgah_pom_pazh);
        cdBoloers=findViewById(R.id.card_activityKarkardBoloerVaPomp_boloers);
        cdPomps=findViewById(R.id.card_activityKarkardBoloerVaPomp_pomps);


        cdBoloers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(G.context,ActivityBoloers.class);
                startActivity(intent);

            }
        });

        cdPomps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(G.context,ActivityPomps.class);
                startActivity(intent);

            }
        });
    }
}