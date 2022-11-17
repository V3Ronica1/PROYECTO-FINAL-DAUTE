package com;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.Acerca.Acerca_de;
import com.Contacto.Contactos;
import com.Importantes.Importante_Notas;
import com.ListarNotas.Listar_Notas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.vg.agenda_online.Agregar_Notas;
import com.vg.agenda_online.Ayuda;
import com.vg.agenda_online.R;

public class MenuPrincipal extends AppCompatActivity {

    Button CerrarSesion;
    Button btnagregar, btnmisnotas, btnimportantes, btncontacto, btnacerca, btnsalir, btn_sobreapp;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseUser user;
    DatabaseReference BASE_DE_DATOS;
    DatabaseReference Usuarios;
    TextView idnombre, idcorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        LayoutInflater imagen_acerca_de = LayoutInflater.from(MenuPrincipal.this);
        final View vista = imagen_acerca_de.inflate(R.layout.activity_acerca_de, null);


        CerrarSesion=findViewById(R.id.btnsalir);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        BASE_DE_DATOS = firebaseDatabase.getReference("Usuarios");

        btnagregar=findViewById(R.id.btn_Agregar);
        btnmisnotas=findViewById(R.id.btn_Misnotas);
        btnimportantes=findViewById(R.id.btn_Importantes);
        btncontacto=findViewById(R.id.btn_Contacto);
        btnacerca=findViewById(R.id.btn_Acerca);
        btn_sobreapp=findViewById(R.id.btn_sobreapp);

        firebaseDatabase = firebaseDatabase.getInstance();
        Usuarios = firebaseDatabase.getReference("Usuarios");

        //firebaseDatabase = firebaseDatabase.getInstance();
        //Usuarios = firebaseDatabase.getReference("Usuarios");

        //
        idnombre =findViewById(R.id.idnombre);
        idcorreo =findViewById(R.id.idcorreo);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Agregar_Notas.class));

            }
        });
        btnmisnotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Listar_Notas.class));
            }
        });
        btnimportantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Importante_Notas.class));
            }
        });
        btncontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Contactos.class));
            }
        });

        btnacerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
                builder.setMessage("Veronica Yamilet Panameño              "+
                                  "Neftaly Eleazar Calderon                    "+
                                  "Cristina Francisca Argueta                  "+
                                  "Elias Ismael Panameño")
                        /*.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })*/
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                /*AlertDialog titulo = builder.create();
                titulo.setTitle("Autores");
                titulo.setView(vista);*/
                builder.show();
            }

        });
        btn_sobreapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Ayuda.class));
            }
        });


    }

    //Metodo para verificar si el usuario a iniciado seccion

    //Metodo para recuperar datos del usuario
   private void CargarDatos(){
        Query query = BASE_DE_DATOS.orderByChild("correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Recorremo, los usuarios en la base de datos
              for (DataSnapshot ds : snapshot.getChildren()){

                    //Obteniendo valores
                    String uid= ""+ds.child("uid").getValue();
                    String correo = ""+ds.child("correo").getValue();

                    //Seteamos los datos


                    //Declaramos los datos
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            //     @Override
        //    public void onCancelled(@NonNull DatabaseError error) {

        //    }
       // });
    //}

    private void SalirAplicacion() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
        builder.setMessage("¿Seguro que desea salir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
                        Toast.makeText(MenuPrincipal.this, "Sesion cerrada exitosamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();

        firebaseAuth.signOut();
        
    }


});
   }
}
