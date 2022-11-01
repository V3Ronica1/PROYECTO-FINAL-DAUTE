package com.vg.agenda_online;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Agregar_Notas extends AppCompatActivity {

    TextView Uid_usuario, correo_usuario, fecha_hora, date, result;
    EditText titulo, descripcion;
    Button calender;

    int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_notas);

        ActionBar actionBar = getSupportActionBar();
        //ActionBar.setTitle("");
        //ActionBar.setDisplayShowHomeEnabled(true);
        //ActionBar.setDisplayHomeAsUpEnabled(true);

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

    }

    private void obtener_fecha_hora_actual() {
    }

    private void obtenerDatos() {
    }

    private void Inicializarvariables() {
    }
}