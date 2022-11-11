package com;

import android.content.Intent;
import android.os.Bundle;
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
import com.vg.agenda_online.Agregar_Notas;
import com.vg.agenda_online.R;

public class MenuPrincipal extends AppCompatActivity {

    Button CerrarSesion;

    TextView NombrePrincipal, CorreoPrincipal;
    Button btnagregar, btnmisnotas, btnimportantes, btncontacto, btnacerca, btnsalir;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference BASE_DE_DATOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

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

        //firebaseDatabase = firebaseDatabase.getInstance();
        //Usuarios = firebaseDatabase.getReference("Usuarios");

        //
        NombrePrincipal=findViewById(R.id.NombrePrincipal);
        CorreoPrincipal=findViewById(R.id.CorreoPrincipal);

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
                startActivity(new Intent(MenuPrincipal.this, Acerca_de.class));
            }
        });
        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalirAplicacion();
            }
        });

    }

    //Metodo para verificar si el usuario a iniciado seccion

    //Metodo para recuperar datos del usuario
   // private void CargarDatos(){
     //   Query query = BASE_DE_DATOS.orderByChild("correo").equalTo(firebaseUser);
     //   query.addValueEventListener(new ValueEventListener() {
         //   @Override
          //  public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Recorremo, los usuarios en la base de datos
            //    for (DataSnapshot ds : snapshot.getChildren()){

                    //Obteniendo valores
                  //  String uid= ""+ds.child("uid").getValue();
                 //   String correo = ""+ds.child("correo").getValue();

                    //Seteamos los datos


                    //Declaramos los datos
           //     }
          //  }

       //     @Override
        //    public void onCancelled(@NonNull DatabaseError error) {

        //    }
       // });
    //}

    private void SalirAplicacion() {
        firebaseAuth.signOut();
        startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
        Toast.makeText(this, "Cerraste sesion exitosamente", Toast.LENGTH_SHORT).show();

    }


}
