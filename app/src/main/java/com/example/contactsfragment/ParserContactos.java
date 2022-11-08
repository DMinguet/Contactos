package com.example.contactsfragment;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

public class ParserContactos {
    private Contacto[] contactos;
    private final InputStream contactosArchivo;

    public ParserContactos(Context context) {
        this.contactosArchivo = context.getResources().openRawResource(R.raw.contacts);
    }

    public boolean parse() {
        boolean parsed = false;
        contactos = null;
        StringBuilder stringBuilder = new StringBuilder();
        String json = null;

        try {
            int size = contactosArchivo.available();
            byte[] buffer = new byte[size];
            contactosArchivo.read(buffer);
            contactosArchivo.close();
            json = new String(buffer, "UTF-8");
            JSONTokener tokener = new JSONTokener(json);
            JSONArray arr = new JSONArray(tokener);
            contactos = new Contacto[arr.length()];

            for (int i = 0; i < arr.length(); i++) {
                stringBuilder.setLength(0);
                String nombre = arr.getJSONObject(i).getString("name");
                String primerApellido = arr.getJSONObject(i).getString("firstSurname");
                String segundoApellido = arr.getJSONObject(i).getString("secondSurname");
                stringBuilder.append(primerApellido).append(" ").append(segundoApellido);
                String apellidos = stringBuilder.toString();
                String fechaNacimiento = arr.getJSONObject(i).getString("birth");
                String companyia = arr.getJSONObject(i).getString("company");
                String email = arr.getJSONObject(i).getString("email");
                String movil1 = arr.getJSONObject(i).getString("phone1");
                String movil2 = arr.getJSONObject(i).getString("phone2");
                String direccion = arr.getJSONObject(i).getString("address");
                contactos[i] = new Contacto(nombre, apellidos, fechaNacimiento, companyia, email, movil1, movil2, direccion);
            }

            parsed = true;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return parsed;
    }

    public Contacto[] getContactos() {
        return this.contactos;
    }
}
