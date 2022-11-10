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
import com.vg.agenda_online.Agregar_Notas;
import com.vg.agenda_online.R;

public class MainActivity extends AppCompatActivity {

    Button Btn_Login, Btn_Registro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_Login=findViewById(R.id.Btn_Login);
        Btn_Registro=findViewById(R.id.Btn_Registro);


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




    }
}