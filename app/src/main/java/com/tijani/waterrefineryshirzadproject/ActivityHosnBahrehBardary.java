package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityHosnBahrehBardary extends AppCompatActivity {

    CardView cdAverageResult, cdMaqadirLajan, cdGandZodaye,cdNegahdariVaTamiratBarq,cdNegahdariVaTamirTajhizatMekaniki,cdHavadesVaQateeBarq,cdBazsaziVaNosazi,cdKarKardBoloersVaPomps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosn_bahreh_bardary);

        cdAverageResult = findViewById(R.id.card_activityHosnBahrehBardary_averageResult);
        cdMaqadirLajan = findViewById(R.id.card_activityHosnBahrehBardary_maqadirLajan);
        cdGandZodaye = findViewById(R.id.card_activityHosnBahrehBardary_gandZodaye);
        cdNegahdariVaTamiratBarq=findViewById(R.id.card_activityHosnBahrehBardary_negahdaryVaTamirBarq);
        cdNegahdariVaTamirTajhizatMekaniki=findViewById(R.id.card_activityHosnBahrehBardary_negahdaryVaTamirTajhizatMekaniki);
        cdHavadesVaQateeBarq=findViewById(R.id.card_activityHosnBahrehBardary_qateeBarqVaHavadec);
        cdBazsaziVaNosazi=findViewById(R.id.card_activityHosnBahrehBardary_bazSazi);
        cdKarKardBoloersVaPomps=findViewById(R.id.card_activityHosnBahrehBardary_karkardBoloers);

        cdAverageResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G.context, ActivityAverageResult.class);
                startActivity(intent);

            }
        });

        cdMaqadirLajan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G.context, ActivityMaqadirLajan.class);
                startActivity(intent);

            }
        });


        cdGandZodaye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G.context, ActivityGandZodae.class);
                startActivity(intent);

            }
        });

        cdNegahdariVaTamiratBarq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G.context, ActivityNegahdariVaTamiratBarq.class);
                startActivity(intent);


            }
        });

        cdNegahdariVaTamirTajhizatMekaniki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G.context, ActivityNegahdariVaTamiratTajhizatMekaniki.class);
                startActivity(intent);

            }
        });


        cdHavadesVaQateeBarq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G.context, ActivityHavadesVaQateeBarq.class);
                startActivity(intent);


            }
        });


        cdBazsaziVaNosazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G.context,ActivityBazsaziVaRangAmizi.class);
                startActivity(intent);
            }
        });

        cdKarKardBoloersVaPomps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G.context,ActivityKarkardMahanehBoloersVaIstgahPomPazh.class);
                startActivity(intent);

            }
        });




    }
}