package com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ListarNotas.Listar_Notas;
import com.Objetos.Dto_notas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vg.agenda_online.Agregar_Notas;
import com.vg.agenda_online.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Actualizar_Nota extends AppCompatActivity {


    TextView correo_user,fecha_hora , date, result;
    EditText titulo, descripcion;
    Button icon_actualizar, icon_eliminar;

    Module module;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Dto_notas notasSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_nota);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Actualizar Nota");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        correo_user=(TextView)findViewById(R.id.correo_user);
        fecha_hora=(TextView)findViewById(R.id.fecha_hora);
        titulo=(EditText)findViewById(R.id.titulo);
        descripcion=(EditText)findViewById(R.id.descripcion);

       // btn_actualizar=(Button)findViewById(R.id.btn_actualizar);
     //   databaseReference = FirebaseDatabase.getInstance().getReference("Notas Agregadas");

       /* module = ((Module)getApplicationContext());
        final String str = module.getGvalue_id().substring(0,6);
        id_usuario.setText(str);
        id_usuario.setEnabled(false);*/

        Bundle objeto = getIntent().getExtras();
        Dto_notas notas =null;
        if (objeto !=null){
            notas=(Dto_notas) objeto.getSerializable("notas");

            String correo="";
            correo_user.setText(notas.getCorreo_usario(correo));
            fecha_hora.setText("Fecha " + getDateTime());
            titulo.setText(notas.getTitulo());
            descripcion.setText(notas.getDescripcion());

        }
       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Dto_notas notas1 = dataSnapshot.child(str).getValue(Dto_notas.class);
                titulo.setText(notas1.getTitulo());
                descripcion.setText(notas1.getDescripcion());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

       /* btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateArrayList();
                Cleartxt();
                Intent intent = new Intent(getApplicationContext(),Listar_Notas.class);
                        startActivity(intent);

            }
        });*/
    }

   /* private void Cleartxt(){
        id_usuario.setText("");
        correo_user.setText("");
        fecha_hora.setText("");
        titulo.setText("");
        descripcion.setText("");
    }*/

   /* private void updateArrayList(){
        final String id = id_usuario.getText().toString().trim();
        final String correo= correo_user.getText().toString().trim();
        final String fecha=fecha_hora.getText().toString().trim();
        final String titu=titulo.getText().toString().trim();
        final String descrip= descripcion.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            id_usuario.setError("Por favor ingrese su id");
        }else if (TextUtils.isEmpty(correo)){
            correo_user.setError("Por favor ingrese su nombre");
        } else if (TextUtils.isEmpty(fecha)){
           fecha_hora.setError("Por favor ingrese su fecha");
        }  else if (TextUtils.isEmpty(titu)){
           titulo.setError("Por favor ingrese  titulo");
        }  else if (TextUtils.isEmpty(descrip)){
        descripcion.setError("Por favor ingrese descripcion");
       }else {
            Dto_notas dtoNotas = new Dto_notas();
            databaseReference.child("Notas Agregadas").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Notas Agregadas").child(id).child("correo").setValue(correo);
                    databaseReference.child("Notas Agregadas").child(id).child("fecha").setValue(fecha);
                    databaseReference.child("Notas Agregadas").child(id).child("titulo").setValue(titu);
                    databaseReference.child("Notas Agregadas").child(id).child("titulo").setValue(descrip);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Toast.makeText(this, "Nota actualizada", Toast.LENGTH_SHORT).show();
            Cleartxt();
        }
    }*/



    private String getDateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat(
                "yyyy-MM-dd HH:mm: a", Locale.getDefault());
        Date date=new Date();
        return dateFormat.format(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actualizar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_actualizar: {


                 /* Dto_notas dtoNotas = new Dto_notas();
                  dtoNotas.setUid(notasSelected.getUid());
                  dtoNotas.setCorreo_usario(correo_user.getText().toString().trim());
                  dtoNotas.setFecha_nota(fecha_hora.getText().toString());
                  dtoNotas.setTitulo(titulo.getText().toString().trim());
                  dtoNotas.setDescripcion(descripcion.getText().toString().trim());
                  databaseReference.child("Notas Agregadas").child(dtoNotas.getUid()).setValue(dtoNotas);*/

                Dto_notas dtoNotas = new Dto_notas();
                dtoNotas.setUid(notasSelected.getUid());
                dtoNotas.setCorreo_usario(correo_user.getText().toString().trim());
                dtoNotas.setFecha_nota(fecha_hora.getText().toString());
                dtoNotas.setTitulo(titulo.getText().toString().trim());
                dtoNotas.setDescripcion(descripcion.getText().toString().trim());
                databaseReference.child("Notas Agregadas").child(dtoNotas.getUid()).setValue(dtoNotas);


                Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();

                break;
            }
            case R.id.icon_eliminar: {

                if (notasSelected != null) {
                    Dto_notas notas = new Dto_notas();
                    notas.setUid(notasSelected.getUid());
                    databaseReference.child("Notas Agregadas").child(notas.getUid()).removeValue();
                    notasSelected = null;

                    Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
            return super.onOptionsItemSelected(item);

    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}