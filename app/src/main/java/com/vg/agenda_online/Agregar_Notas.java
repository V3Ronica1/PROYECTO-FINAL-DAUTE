package com.vg.agenda_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.MenuPrincipal;
import com.Objetos.Dto_notas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class Agregar_Notas extends AppCompatActivity {

    TextView Uid_usuario, correo_usuario, fecha_hora, date, result;
    EditText titulo, descripcion;
    Button calender;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

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

        Uid_usuario=findViewById(R.id.id_usuario);
        correo_usuario=findViewById(R.id.correo_user);
        fecha_hora=findViewById(R.id.fecha_hora);
        titulo=findViewById(R.id.titulo);
        descripcion=findViewById(R.id.descripcion);
        calender=findViewById(R.id.calender);
        result=findViewById(R.id.result);

        inicializarFirebase();

      //  Inicializarvariables();
        // obtenerDatos();
       // obtener_fecha_hora_actual();



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


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String titu = titulo.getText().toString();
        String des = descripcion.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_add:{
                if (titu.equals("")|| des.equals("")){
                    validacion();
                    startActivity(new Intent(Agregar_Notas.this, MenuPrincipal.class));
                }
                else {
                    Dto_notas notas = new Dto_notas();
                    notas.setId_nota(UUID.randomUUID().toString());
                    notas.setTitulo(titu);
                    notas.setDescripcion(des);
                    databaseReference.child("Notas Agregadas").child(notas.getId_nota()).setValue(notas);
                    Toast.makeText(this, "Agregar", Toast.LENGTH_SHORT).show();
                    limpiar();

                }
                break;
            }
            case R.id.icon_save:{
                Toast.makeText(this, "Guardar", Toast.LENGTH_SHORT).show();
                break;
            }
            default:break;
        }

        return true;
    }

    private void limpiar() {
        titulo.setText("");
        descripcion.setText("");
    }

    private void validacion() {
        String titu = titulo.getText().toString();
        String des = descripcion.getText().toString();


        if (titu.equals("")){
            titulo.setError("Required");
        }
        else if (des.equals("")){
            titulo.setError("Required");
        }

    }



    //private void obtener_fecha_hora_actual() {}

   // private void obtenerDatos() {}

    //private void Inicializarvariables() {}
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


}

