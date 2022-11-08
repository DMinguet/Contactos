package com.example.contactsfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetalle extends Fragment {
    public interface IOnAttachListener {
        Contacto getContact();
    }
    private TextView tvNombre;
    private TextView tvApellidos;
    private TextView tvFechaNacimiento;
    private TextView tvCompanyia;
    private TextView tvEmail;
    private TextView tvMovil1;
    private TextView tvMovil2;
    private TextView tvDireccion;
    private Contacto contacto;

    public FragmentDetalle() {
        super(R.layout.fragment_detalle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNombre = view.findViewById(R.id.tvNombre);
        tvApellidos = view.findViewById(R.id.tvApellidos);
        tvFechaNacimiento = view.findViewById(R.id.tvFechaNacimiento);
        tvCompanyia = view.findViewById(R.id.tvEmpresa);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvMovil1 = view.findViewById(R.id.tvMovil1);
        tvMovil2 = view.findViewById(R.id.tvMovil2);
        tvDireccion = view.findViewById(R.id.tvDireccion);

        if (contacto != null) {
            mostrarDetalle(contacto);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        contacto = attachListener.getContact();
    }

    public void mostrarDetalle(Contacto contacto) {
        this.contacto = contacto;
        tvNombre.setText(contacto.getNombre());
        tvApellidos.setText(contacto.getApellidos());
        tvFechaNacimiento.setText(contacto.getFechaNacimiento());
        tvCompanyia.setText(contacto.getCompanyia());
        tvEmail.setText(contacto.getEmail());
        tvMovil1.setText(contacto.getMovil1());
        tvMovil2.setText(contacto.getMovil2());
        tvDireccion.setText(contacto.getDireccion());
    }
}
