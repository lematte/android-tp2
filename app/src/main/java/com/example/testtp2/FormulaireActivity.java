 package com.example.testtp2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormulaireActivity extends AppCompatActivity {

    Button OK, Annuler;
    EditText Nom, Prenom, Formation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulaire);

        OK=findViewById(R.id.btnOk);
        Annuler=findViewById(R.id.btnAnnuler);

        Nom=findViewById(R.id.etNom);
        Prenom=findViewById(R.id.etPrenom);
        Formation=findViewById(R.id.etForm);



        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationOk() ;
            }
        });

        Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationAnnuler();
            }
        });


    }
    void operationOk() {
        if(Nom.length() >0){
            Intent intent = new Intent();
            intent.putExtra("message",Nom.getText().toString() + "    " + Prenom.getText().toString() + ", formation : " + Formation.getText().toString() );
            intent.putExtra("Nom", Nom.getText().toString());
            intent.putExtra("Prenom", Prenom.getText().toString());
            intent.putExtra("Formation", Formation.getText().toString());
            Toast.makeText(FormulaireActivity.this, "Done", Toast.LENGTH_LONG).show();
            setResult(Activity.RESULT_OK, intent);
            finish();
    }
    }
    void operationAnnuler() {
        Intent intent = new Intent();
        intent.putExtra("message", "Annuler");
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}