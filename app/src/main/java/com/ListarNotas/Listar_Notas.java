package com.ListarNotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.Actualizar_Nota;
import com.Detalles.Detalles_Notas;
import com.Objetos.Dto_notas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vg.agenda_online.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Listar_Notas extends AppCompatActivity {

    private ArrayList<Dto_notas> dto_notasList = new ArrayList<Dto_notas>();
    ArrayAdapter<Dto_notas> dto_notasArrayAdapter;
    SearchView searchView;

    ListView listView;
    Dialog dialog;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Dto_notas notasSelected;
    TextView id_usuario,correo_user,fecha_hora;
    EditText titulo, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notas);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mis Notas");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        id_usuario=(TextView) findViewById(R.id.id_usuario);
        correo_user=(TextView)findViewById(R.id.correo_user);
        fecha_hora=(TextView)findViewById(R.id.fecha_hora);
        titulo=(EditText)findViewById(R.id.titulo);
        descripcion=(EditText)findViewById(R.id.descripcion);


        Listar_Notas listar_notas = new Listar_Notas() ;

        listView=findViewById(R.id.listview_notas);
        searchView=(SearchView)findViewById(R.id.SearchView);
        dto_notasArrayAdapter = new ArrayAdapter<Dto_notas>(Listar_Notas.this, android.R.layout.simple_list_item_1,dto_notasList);
        listView.setAdapter(dto_notasArrayAdapter);

        dialog = new Dialog(Listar_Notas.this);
        incializarFirebase();
        listarDatos();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text=s;
                dto_notasArrayAdapter.getFilter().filter(text);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String informacion="Id:" +dto_notasList.get(position).getId_nota() +"\n";
                String correo="";
                informacion+="Correo:" +dto_notasList.get(position).getCorreo_usario(correo) +"\n";
                String fecha="";
                informacion+="Fecha nota"+dto_notasList.get(position).getFecha_nota(fecha);
                informacion+="Titulo:"+dto_notasList.get(position).getTitulo();
                informacion+="Descripcion:"+dto_notasList.get(position).getDescripcion();

                String estado="";
                informacion+="Estado"+dto_notasList.get(position).getEstado(estado);


                Dto_notas notas = dto_notasList.get(position);
                Intent intent = new Intent(Listar_Notas.this,Detalles_Notas.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("notas",notas);
                intent.putExtras(bundle);
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

                    }
                });
                CD_Actualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String informacion = "Fecha:" + dto_notasList.get(position).getId_nota() + "\n";
                        String correo = "";
                        informacion += "Correo:" + dto_notasList.get(position).getCorreo_usario(correo) + "\n";
                        informacion += "Titulo:" + dto_notasList.get(position).getTitulo();
                        informacion += "Descripcion:" + dto_notasList.get(position).getDescripcion();
                        String estado = "";
                        informacion += "Estado" + dto_notasList.get(position).getEstado(estado);

                        // Toast.makeText(Listar_Notas.this, "Titulo" + informacion, Toast.LENGTH_SHORT).show();

                        Dto_notas notas = dto_notasList.get(position);
                        Intent intent = new Intent(Listar_Notas.this, Actualizar_Nota.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("notas", notas);
                        intent.putExtras(bundle);
                        startActivity(intent);




                    }

                });
                dialog.show();



                return true;
            }
        });
    }


    private String getDateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss a", Locale.getDefault());
        Date date=new Date();
        return dateFormat.format(date);
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