package com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vg.agenda_online.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class pantalla_de_carga extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_carga);

        firebaseAuth = FirebaseAuth.getInstance();

        int Tiempo = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* startActivity(new Intent(pantalla_de_carga.this, MainActivity.class));
                finish();*/
                VerificarUsuario();
            }
        },Tiempo);

    }

    private void VerificarUsuario(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null){
            startActivity(new Intent(pantalla_de_carga.this,MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(pantalla_de_carga.this,MenuPrincipal.class));
            finish();
        }
    }

    public static String dateToString(Date date){
     return    new SimpleDateFormat("MM/dd/yyyy").format(date.toString());
    }

}