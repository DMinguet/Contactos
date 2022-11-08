package com.example.contactsfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentListado extends Fragment {
    private Contacto[] contactos;
    private IContactosListener listener;

    public interface IOnAttachListener {
        Contacto[] getContacts();
    }

    public FragmentListado() {
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvListado = view.findViewById(R.id.rvListado);
        rvListado.setHasFixedSize(true);
        rvListado.setAdapter(new AdaptadorContactos(getContext(), contactos, listener));
        rvListado.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (IContactosListener) context;
        IOnAttachListener attachListener = (IOnAttachListener) context;
        contactos = attachListener.getContacts();
    }
}
