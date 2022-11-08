package com.example.contactsfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IContactosListener, FragmentListado.IOnAttachListener, FragmentDetalle.IOnAttachListener {
    private static final String CONTACTS_KEY = "com.germangascon.recyclerviewfragments.contacts";
    private static final String SELECTED_CONTACT_KEY = "com.germangascon.recyclerviewfragments.selectedcontact";
    private FragmentDetalle frgDetalle;
    private boolean tabletLayout;
    private Contacto[] contactos;
    private Contacto contactoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            contactos = (Contacto[]) savedInstanceState.getSerializable(CONTACTS_KEY);
            contactoSeleccionado = (Contacto) savedInstanceState.getSerializable(SELECTED_CONTACT_KEY);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabletLayout = findViewById(R.id.FrgDetalle) != null;

        if (tabletLayout) {
            FragmentManager manager = getSupportFragmentManager();
            frgDetalle = (FragmentDetalle) manager.findFragmentById(R.id.FrgDetalle);
        }
    }

    private void loadData() {
        ParserContactos parser = new ParserContactos(this);

        if(parser.parse()) {
            contactos = parser.getContactos();
        } else {
            Toast.makeText(this, "Error al obtener los contactos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(CONTACTS_KEY, contactos);
        outState.putSerializable(SELECTED_CONTACT_KEY, contactoSeleccionado);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onContactoSeleccionado(int position) {
        contactoSeleccionado = contactos[position];
        if (tabletLayout) {
            Contacto contacto = contactos[0];
            frgDetalle.mostrarDetalle(contacto);
        } else {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.FrgListado, FragmentDetalle.class, null)
                    .commit();
        }
    }

    @Override
    public Contacto[] getContacts() {
        if (contactos == null) {
            loadData();
        }
        return contactos;
    }

    @Override
    public Contacto getContact() {
        if (contactos == null) {
            loadData();
        }

        if (contactoSeleccionado == null) {
            contactoSeleccionado = contactos[0];
        }

        return contactoSeleccionado;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setTitle(R.string.app_name);
    }
}