package com.ik_2dm3.agenda;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ik_2dm3 on 28/11/2016.
 */

public class JsonPersonaParser {


    public List<Persona> leerFlujoJson(InputStream in) throws IOException {
                //nueva instancia de JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in,"UTF-8"));
        try{
            //leemos array
            return leerArrayPersonas(reader);
        }finally{
            reader.close();
        }

        }


    public List<Persona> leerArrayPersonas (JsonReader reader) throws IOException{
        //lista temporal

        ArrayList<Persona> personas = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()){

            personas.add(leerPersona(reader));

        }

        reader.endArray();

        return personas;

    }

    public Persona leerPersona(JsonReader reader) throws IOException{
        String dni=null;
        String telefono=null;
        String nombre=null;
        String mail=null;


        reader.beginObject();
        while(reader.hasNext()){
            /*nextName (clave) devuelve el nombre telefono dni ..
            * Tambien tenemos nextInt nextString segun el tipo de dato...peek() nos devuelve el tipo de dato del siguiente elemento*/
            String name = reader.nextName();

            switch (name){
                 case "nombre":
                      nombre = reader.nextString();
                       break;
                 case "dni":
                       dni =  reader.nextString();
                        break;
                 case "telefono":
                       telefono = reader.nextString();
                        break;
                case "mail":
                         mail = reader.nextString();
                        break;
                default:
                    reader.skipValue();
            }


    }

        reader.endObject();
        return new Persona(dni, nombre, telefono, mail);

    }






}
