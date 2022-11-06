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
import com.vg.agenda_online.R;

import java.util.ArrayList;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder>{
    Context context;
    ArrayList<Dto_notas> list;


    public NoteAdapter(Context context, ArrayList<Dto_notas> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recycle_rview,parent,false);


        return new MyViewHolder(v);
    }





    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     Dto_notas notas = list.get(position);
     holder.titleTextview.setText(notas.getTitulo());
     holder.desTextview.setText(notas.getDescripcion());
     holder.dateTextview.setText((CharSequence) notas.getFecha_nota());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextview,desTextview,dateTextview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextview=itemView.findViewById(R.id.textitle);
            desTextview=itemView.findViewById(R.id.textdes);
            dateTextview=itemView.findViewById(R.id.textfecha);
        }
    }
}
