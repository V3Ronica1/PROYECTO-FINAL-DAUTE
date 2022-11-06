package com.ListarNotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.Objetos.Dto_notas;
import com.ViewHolder.NoteAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vg.agenda_online.R;

import java.util.ArrayList;
import java.util.Date;

public class Listar_notas extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Dto_notas> list;
    Button CD_Eliminar,CD_Actualizar;
    DatabaseReference databaseReference;
    NoteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notas2);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Mis Notas");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.recyclerviewNotas);
        databaseReference = FirebaseDatabase.getInstance().getReference("Notas");
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new NoteAdapter(this, list);
        recyclerView.setAdapter(adapter);
        CD_Eliminar=findViewById(R.id.)



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Dto_notas notas = dataSnapshot.getValue(Dto_notas.class);
                    list.add(notas);
                }
                adapter.notifyDataSetChanged();
            }






            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setupRecyclerView();


    }



    private void setupRecyclerView() {

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }



}

