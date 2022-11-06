package com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Acerca.Acerca_de;
import com.Contacto.Contactos;
import com.Importantes.Importante_Notas;
import com.ListarNotas.Listar_Notas;
import com.vg.agenda_online.Agregar_Notas;
import com.vg.agenda_online.R;

public class MainActivity extends AppCompatActivity {

    Button Btn_Login, Btn_Registro;
    Button btnagregar, btnmisnotas, btnimportantes, btncontacto, btnacerca, btnsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_Login=findViewById(R.id.Btn_Login);
        Btn_Registro=findViewById(R.id.Btn_Registro);
        btnagregar=findViewById(R.id.btnagregar);
        btnmisnotas=findViewById(R.id.btnmisnotas);
        btnimportantes=findViewById(R.id.btnimportantes);
        btncontacto=findViewById(R.id.btncontacto);
        btnacerca=findViewById(R.id.btnacerca);

        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });

        Btn_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Registro.class));
            }
        });

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Agregar_Notas.class));
            }
        });
        btnmisnotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Listar_Notas.class));
            }
        });
        btnimportantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Importante_Notas.class));
            }
        });
        btncontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Contactos.class));
            }
        });
        btnacerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Acerca_de.class));
            }
        });


    }
}