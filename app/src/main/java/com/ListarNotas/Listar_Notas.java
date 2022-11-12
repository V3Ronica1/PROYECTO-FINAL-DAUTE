package com.ListarNotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.Detalles.Detalles_Notas;
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
    //Declarar las vistas
    Button CD_Eliminar, CD_Actualizar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notas);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mis Notas");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


       listView=findViewById(R.id.listview_notas);
       dialog = new Dialog(Listar_Notas.this);
       incializarFirebase();
       listarDatos();

       Listar_Notas listar_notas = new Listar_Notas() ;



       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(Listar_Notas.this, Detalles_Notas.class);
               startActivity(intent);
           }

       });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Listar_Notas.this, "on item long click", Toast.LENGTH_SHORT).show();
                //Declarar las vistas
                Button CD_Eliminar, CD_Actualizar;

                //Realizar la conexion con el diseño
                dialog.setContentView(R.layout.dialogo_opciones);


                CD_Eliminar = dialog.findViewById(R.id.CD_Eliminar);
                CD_Actualizar = dialog.findViewById(R.id.CD_Actualizar);

                CD_Eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Listar_Notas.this, "Nota eliminada", Toast.LENGTH_SHORT).show();
                    }
                });
                CD_Actualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Listar_Notas.this, "Actualizar nota", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();

                return true;
            }
        });


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