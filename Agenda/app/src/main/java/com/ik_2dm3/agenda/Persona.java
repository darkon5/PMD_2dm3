package com.ik_2dm3.agenda;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ik_2dm3 on 24/11/2016.
 */

public class Persona {

    private String dni, nombre, telefono, mail;

    public Persona(){
        dni = "";
        nombre = "";
        telefono = "";
        mail = "";

    }

    public Persona(String dni, String nombre, String telefono, String mail){
        this.dni = dni;
        this.nombre = nombre;

        this.telefono = telefono;
        this.mail = mail;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }





}


