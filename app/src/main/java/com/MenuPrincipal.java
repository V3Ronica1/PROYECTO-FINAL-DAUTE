package com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Acerca.Acerca_de;
import com.Contacto.Contactos;
import com.Importantes.Importante_Notas;
import com.ListarNotas.Listar_notas;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vg.agenda_online.Agregar_Notas;
import com.vg.agenda_online.R;

public class MenuPrincipal extends AppCompatActivity {

    Button CerrarSesion,btnagregar, btnmisnotas, btnimportantes, btncontacto, btnacerca;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        CerrarSesion=findViewById(R.id.btnsalir);
        btnagregar=findViewById(R.id.btn_Agregar);
        btnmisnotas=findViewById(R.id.btn_Misnotas);
        btnimportantes=findViewById(R.id.btn_Importantes);
        btncontacto=findViewById(R.id.btn_Contacto);
        btnacerca=findViewById(R.id.btn_Acerca);

        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Agregar_Notas.class));

            }
        });
        btnmisnotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuPrincipal.this, Listar_notas.class));
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

    private void SalirAplicacion() {
        firebaseAuth.signOut();
        startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
        Toast.makeText(this, "Cerraste sesion exitosamente", Toast.LENGTH_SHORT).show();
    }
}