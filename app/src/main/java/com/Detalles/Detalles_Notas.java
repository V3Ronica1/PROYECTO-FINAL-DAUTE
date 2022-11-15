package com.Detalles;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.Objetos.Dto_notas;
import com.vg.agenda_online.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Detalles_Notas extends AppCompatActivity {

    ListView listView;
    EditText txt_id,txt_correo,txt_titulo,txt_des, txt_fechanota;
    Dto_notas notasSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_notas);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Detalles  Notas");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        txt_id=(EditText) findViewById(R.id.txt_id);
        txt_correo=(EditText)findViewById(R.id.txt_correo);
        txt_titulo=(EditText)findViewById(R.id.txt_titulo);
        txt_des=(EditText)findViewById(R.id.txt_des);
        txt_fechanota=(EditText)findViewById(R.id.txt_fechare);



        Bundle objeto = getIntent().getExtras();
        Dto_notas notas =null;
        if (objeto !=null){
            notas=(Dto_notas) objeto.getSerializable("notas");
            txt_id.setText(""+notas.getId_nota());
            String correo="";
            txt_correo.setText(notas.getCorreo_usario(correo));
            txt_titulo.setText(notas.getTitulo());
            txt_des.setText(notas.getDescripcion());
            txt_fechanota.setText(""+getDateTime());


        }

    }


    private String getDateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss a", Locale.getDefault());
        Date date=new Date();
        return dateFormat.format(date);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}