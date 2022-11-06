package com.vg.agenda_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MenuPrincipal;
import com.Registro;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class Agregar_Notas extends AppCompatActivity {

    TextView Uid_usuario, correo_usuario, fecha_hora, date, result;
    EditText titulo, descripcion;
    Button calender;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    //
    String titu= " ", des=" ", fcha= " ", resulta= " ";

    int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_notas);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar nota");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Inicializarvariables();
        obtenerDatos();
        obtener_fecha_hora_actual();

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                anio = calendario.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Agregar_Notas.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int AnioSeleccionado, int MesSeleccionado, int DiaSeleccionado) {

                        String diaFormateado, mesFormateado;

                        //ObtenerDia
                        if (DiaSeleccionado < 10){
                            diaFormateado = "0"+String.valueOf(DiaSeleccionado);
                        }else {
                            diaFormateado = String.valueOf(DiaSeleccionado);
                        }

                        //Obtener Mes
                        int Mes = MesSeleccionado + 1;
                        if (Mes < 10){
                            mesFormateado = "0"+String.valueOf(Mes);
                        }else {
                            mesFormateado = String.valueOf(Mes);
                        }

                        //Setear Fecha
                        date.setText(diaFormateado + "/" + mesFormateado + "/" + AnioSeleccionado);


                    }
                }
                , anio, mes, dia);
                datePickerDialog.show();
            }
        });


        GuardarInformacion();
    }


    private void GuardarInformacion() {

        //Obtener la identificacion de usuario actual
        String uid = firebaseAuth.getUid();

        HashMap<String, String> Datos = new HashMap<>();
        Datos.put("uid", uid);
        Datos.put("titulo", titu);
        Datos.put("descripcion", des );
        Datos.put("fecha", fcha);
        Datos.put("resultado", resulta);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Notas");
        databaseReference.child(uid)
                .setValue(Datos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(Agregar_Notas.this, "Nota agregada exitosamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Agregar_Notas.this, MenuPrincipal.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Agregar_Notas.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void obtener_fecha_hora_actual() {
    }

    private void obtenerDatos() {

    }

    private void Inicializarvariables() {

    }
}