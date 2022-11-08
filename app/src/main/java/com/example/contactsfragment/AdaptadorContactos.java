package com.example.contactsfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorContactos extends RecyclerView.Adapter<AdaptadorContactos.ViewHolder> {
    private final Contacto[] contactos;
    private final Context context;
    private final IContactosListener listener;

    public AdaptadorContactos(Context context, Contacto[] contactos, IContactosListener listener) {
        this.context = context;
        this.contactos = contactos;
        this.listener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_contacto, parent, false);

        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contacto contacto = contactos[position];
        holder.bindContacto(contacto);
    }

    public int getItemCount() {
        if (contactos == null) {
            return 0;
        } else {
            return contactos.length;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvNombre;
        private final TextView tvMovil1;
        private final IContactosListener listener;

        public ViewHolder(@NonNull View itemView, IContactosListener listener) {
            super(itemView);
            this.tvNombre = itemView.findViewById(R.id.tvNombre);
            this.tvMovil1 = itemView.findViewById(R.id.tvMovil1);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindContacto(Contacto contacto) {
            tvNombre.setText(contacto.getNombre());
            tvMovil1.setText(contacto.getMovil1());
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onContactoSeleccionado(getBindingAdapterPosition());
            }
        }
    }
}
