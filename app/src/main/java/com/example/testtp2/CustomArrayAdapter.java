package com.example.testtp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CustomArrayAdapter extends ArrayAdapter {

    ArrayList listValues;
    LayoutInflater inflater;

    public CustomArrayAdapter(Context context, ArrayList listValues, LayoutInflater inflater){
        super(context, -1, listValues);
        this.listValues = listValues;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_row, parent, false);

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        TextView item1 = view.findViewById(R.id.idItemnomPrenon);
        TextView item2 = view.findViewById(R.id.idItemformation);

        Vector itemValues = (Vector) listValues.get(position);
        String nom, prenon, formation;
        nom = (String) itemValues.get(0);
        prenon = (String) itemValues.get(1);
        formation = (String) itemValues.get(2);

        item1.setText(nom + " "+ prenon);
        item2.setText(formation);
        imageView.setImageResource(R.mipmap.ic_launcher);
        return view;
    }

}
