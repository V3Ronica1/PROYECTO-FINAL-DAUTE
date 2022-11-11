package com.ListarNotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.Objetos.Dto_notas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vg.agenda_online.R;

import java.util.ArrayList;
import java.util.List;

public class Listar_Notas extends AppCompatActivity {

    private List<Dto_notas> dto_notasList = new ArrayList<Dto_notas>();
    ArrayAdapter<Dto_notas> dto_notasArrayAdapter;

    ListView listView;
    Dialog dialog;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notas);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar notas");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

       listView=findViewById(R.id.listview_notas);
       dialog = new Dialog(Listar_Notas.this);
       incializarFirebase();
       listarDatos();



    }

    private void listarDatos() {
        databaseReference.child("Notas Agregadas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot ) {
                dto_notasList.clear();
               for (DataSnapshot objSnaptshot: dataSnapshot.getChildren()){
                   Dto_notas notas = objSnaptshot.getValue(Dto_notas.class);
                   dto_notasList.add(notas);

                   dto_notasArrayAdapter = new ArrayAdapter<Dto_notas>(Listar_Notas.this, android.R.layout.simple_list_item_1,dto_notasList);
                   listView.setAdapter(dto_notasArrayAdapter);
               }
            }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

            }
      });

    }

    private void incializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}