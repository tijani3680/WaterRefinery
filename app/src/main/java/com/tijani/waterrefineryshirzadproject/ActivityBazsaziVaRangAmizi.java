package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityBazsaziVaRangAmizi extends AppCompatActivity {

    CardView cardRangAmizi,cardBazsaziVaNoSazi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bazsazi_va_rang_amizi);

        cardRangAmizi=findViewById(R.id.card_activityBazSaziVaRangAmizi_rangAmizi);
        cardBazsaziVaNoSazi=findViewById(R.id.card_activityBazSaziVaRangAmizi_bazSaziVaNoSazi);

        cardRangAmizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G.context,ActivityRangAmizi.class);
                startActivity(intent);

            }
        });


        cardBazsaziVaNoSazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G.context,ActivityBazsaziVaNosazi.class);
                startActivity(intent);
            }
        });
    }
}