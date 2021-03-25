package com.example.testtp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {


    Button OK, Supprimer;
    TextView itemNom, itemPrenom, itemFormation;
    int indexEtudiant = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        OK=findViewById(R.id.btninfok);
        Supprimer=findViewById(R.id.btninfoSupprimer);
        itemNom=findViewById(R.id.tv1);
        itemPrenom =findViewById(R.id.tv2);
        itemFormation=findViewById(R.id.tv3);

        Intent intent = getIntent();
        String strNom = intent.getStringExtra("nom");
        String strPrenon = intent.getStringExtra("prenom");
        String strform = intent.getStringExtra("formation");
        indexEtudiant = intent.getIntExtra("indexEtudiant", -1);

        itemNom.setText(strNom);
        itemPrenom.setText(strPrenon);
        itemFormation.setText(strform);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnclick(v, 1);
            }
        });

        Supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnclick(v,2);
            }
        });
    }
    private void btnOnclick(View view, int choice) {

        Intent intent = new Intent();
        if(choice == 1)
            setResult(MainActivity.RESULT_OK_FROM_INFO, intent);
        if(choice == 2){
            intent.putExtra("indexEtudiant", indexEtudiant);
            setResult(MainActivity.RESULT_SUPPRIMER, intent);
        }
        finish();
    }

}