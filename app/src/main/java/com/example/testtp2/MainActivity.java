package com.example.testtp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {



    Button btnAjouter;
    ListView lvEtudiants;;
   // ArrayAdapter adapter;
   CustomArrayAdapter cAdapter;
    ArrayList listEtudiants = new ArrayList();
    public static int RESULT_SUPPRIMER = 2020;
    public static int RESULT_OK_FROM_INFO = 2019;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEtudiants = findViewById(R.id.lvEtudiants);
        btnAjouter = findViewById(R.id.btnAjouter);

        cAdapter = new CustomArrayAdapter(this, listEtudiants, getLayoutInflater());
        ListView lvEtudiants = findViewById(R.id.lvEtudiants);
        lvEtudiants.setAdapter(cAdapter);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormulaireActivity.class);
                startActivityForResult(intent, 1);

            }
        });

        //appui simple :
        lvEtudiants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Vector etudiant = (Vector) parent.getItemAtPosition(position);
                gotoDetailEtudiant(etudiant, position);
            }
        });

        //appui long :
        lvEtudiants.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                   int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        final Vector itemValues = (Vector) lvEtudiants.getItemAtPosition(position);
                        builder.setTitle((String) itemValues.get(0));
                        builder.setItems(new String[]{"Supprimer", "Detailles"},
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (which == 0) {
                                            listEtudiants.remove(position);//remove the etudiant who has the index
                                            cAdapter.notifyDataSetChanged();
                                            dialog.dismiss();
                                        }
                                        else if (which == 1) {
                                            //bouton Detailles
                                            Vector etudiant = (Vector) parent.getItemAtPosition(position);
                                            gotoDetailEtudiant(etudiant, position);
                                        }
                                    }
                                });
                        builder.show();
                       return true;
                    }
                });

    }
    public void gotoDetailEtudiant(Vector etudiant, int position){
        Vector itemValues = (Vector) lvEtudiants.getItemAtPosition(position);
        Intent mIntent = new Intent(MainActivity.this, InfoActivity.class);
        mIntent.putExtra("nom", itemValues.get(0).toString());
        mIntent.putExtra("prenom", itemValues.get(1).toString());
        mIntent.putExtra("formation", itemValues.get(2).toString());
        mIntent.putExtra("indexEtudiant", position);
        startActivityForResult(mIntent, 0);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String strNom = data.getStringExtra("Nom");
                String strPrenom = data.getStringExtra("Prenom");
                String strformation = data.getStringExtra("Formation");

                Vector itemValues = new Vector();
                itemValues.add(strNom);
                itemValues.add(strPrenom);
                itemValues.add(strformation);
                listEtudiants.add(itemValues);
                cAdapter.notifyDataSetChanged();

               /* listEtudiants.add(strNom +" "+strPrenom+"  " + strformation);
                adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, listEtudiants);
                lvEtudiants.setAdapter(adapter);*/

            } else if (resultCode == RESULT_CANCELED) {
                String message = data.getStringExtra("message");
                Toast.makeText(this, message + " ADD ", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_SUPPRIMER) {

                //get the index of that etudiant from InfoActivity if button Delete Clicked
                int indexEtudiant = data.getIntExtra("indexEtudiant", -1); //put -1 as default value

                //verifier si l'index est une valeur correcte
                if(indexEtudiant > -1) {
                    listEtudiants.remove(indexEtudiant); //remove the etudiant who has the index == indexEtudiant
                    cAdapter.notifyDataSetChanged();}
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.miNouveau:
                Intent intent = new Intent( MainActivity.this,FormulaireActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.miSupprimer:
                listEtudiants.clear();
                cAdapter.notifyDataSetChanged();

                break;
            case R.id.miQuitter:
                finish();
                break;
        }
        return true;
    }


    void buidAndShowDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Vector itemValues = (Vector)
                lvEtudiants.getItemAtPosition(position);
        builder.setTitle((String) itemValues.get(0));
        builder.setItems(new String[]{"Supprimer", "Detailles"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            //bouton Supprimer
                            System.out.println("Supprimer");
                        }
                        else if (which == 1) {
                            //bouton Detailles
                          //  gotoDetailEtudiant();
                        }
                    }
                });
        builder.show();
    }


}