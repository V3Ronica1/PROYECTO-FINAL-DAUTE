package com.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ListarNotas.Listar_notas;
import com.Objetos.Dto_notas;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.pantalla_de_carga;
import com.vg.agenda_online.R;

import java.util.ArrayList;


public class MyAdapter extends FirestoreRecyclerAdapter<Dto_notas, MyAdapter.MyviewHolder> {
    Context context;

    public MyAdapter(@NonNull FirestoreRecyclerOptions<Dto_notas> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyviewHolder holder, int position, @NonNull Dto_notas notas) {
        holder.titleTextView.setText(notas.getTitulo());
        holder.contentTextView.setText(notas.getDescripcion());
        holder.timestampTextView.setText(notas.getFecha_nota().toString());

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_rview,parent,false);
        return new  MyviewHolder(view);
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView,contentTextView,timestampTextView;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView =itemView.findViewById(R.id.textitle);
            contentTextView=itemView.findViewById(R.id.textdes);
            timestampTextView =itemView.findViewById(R.id.textfecha);
        }
    }

}
