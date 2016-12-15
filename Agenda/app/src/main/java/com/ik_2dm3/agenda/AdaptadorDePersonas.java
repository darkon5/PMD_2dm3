package com.ik_2dm3.agenda;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ik_2dm3 on 28/11/2016.
 */

public class AdaptadorDePersonas extends ArrayAdapter<Persona> {

    public AdaptadorDePersonas(Context context, List<Persona> objects){
        super(context, 0, objects);
    }

    //Called for each item in the list, called when you set adapter
    public View getView (int position, View convertView, ViewGroup parent){
        //obtener una instancia inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;


        //comprobacion de seguridad
        if (convertView == null){
            v = inflater.inflate(R.layout.item_lista, parent, false);
        }

        //Obtenemos instancias de los elementos
        TextView tvNombre = (TextView) v.findViewById(R.id.nombre);/*
        TextView tvTelefono = (TextView) v.findViewById(R.id.telefono);
        TextView tvMail = (TextView) v.findViewById(R.id.mail);
        TextView tvDni = (TextView) v.findViewById(R.id.dni);*/

        //obtener la instancia de la tarea de posicion ACTUAL
        Persona item = getItem(position);
        tvNombre.setText(item.getNombre());/*
        tvMail.setText(item.getMail());
        tvDni.setText(item.getDni());
        tvTelefono.setText(item.getTelefono());*/

        return v;
    }



}

