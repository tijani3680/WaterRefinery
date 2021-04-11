package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityHavadesVaQateeBarq extends AppCompatActivity {

    CardView cdHavades,cdQateeBarq,cdTajhizatMotavaqef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_havades_va_qatee_barq);

        cdHavades=findViewById(R.id.card_activityHavadesVaQateeBarq_havades);
        cdQateeBarq=findViewById(R.id.card_activityHavadesVaQateeBarq_qateeBarq);
        cdTajhizatMotavaqef=findViewById(R.id.card_activityHavadesVaQateeBarq_tajhizatMotavaqef);


        cdHavades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G.context,ActivityHavades.class);
                startActivity(intent);

            }
        });

        cdQateeBarq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G.context,ActivityQateeBarq.class);
                startActivity(intent);

            }
        });

        cdTajhizatMotavaqef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G.context,ActivityTajhizatMotavaqef.class);
                startActivity(intent);


            }
        });
    }
}