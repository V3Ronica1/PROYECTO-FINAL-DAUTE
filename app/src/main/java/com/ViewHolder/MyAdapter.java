package com.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Objetos.Dto_notas;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.vg.agenda_online.R;


public class MyAdapter extends FirestoreRecyclerAdapter<Dto_notas, MyAdapter.MyviewHolder> {
    Context context;

    public MyAdapter(@NonNull FirestoreRecyclerOptions<Dto_notas> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyviewHolder holder, int position, @NonNull Dto_notas notas) {


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
