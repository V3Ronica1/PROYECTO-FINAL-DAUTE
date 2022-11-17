package com.Contacto;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.Adaptadores.ListViewNotasAdapter;
import com.Adaptadores.ListViewPersonasAdapter;
import com.Adpatadores.ListViewNotasAdapter;
import com.Models.Persona;
import com.Objetos.Dto_notas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vg.agenda_online.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class Notas1 extends AppCompatActivity {

    private ArrayList<Dto_notas> listNotas= new ArrayList<Dto_notas>();
    ArrayAdapter<Dto_notas> arrayAdapterPersona;
    ListViewNotasAdapter listViewPersonasAdapter;
    LinearLayout linearLayoutEditar;
    ListView listViewPersonas;

    EditText inputNombre, inputTelefono;
    Button btnCancelar;

    Dto_notas personaSeleccionada;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_notas_nuevo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mis Contactos");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        inputNombre = findViewById(R.id.inputNombre);
        inputTelefono = findViewById(R.id.inputTelefono);
        btnCancelar = findViewById(R.id.btnCancelar);

        listViewPersonas = findViewById(R.id.listViewPersonas);
        linearLayoutEditar = findViewById(R.id.linearLayoutEditar);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSeleccionada = (Dto_notas) parent.getItemAtPosition(position);
                inputNombre.setText(personaSeleccionada.getTitulo());
                inputTelefono.setText(personaSeleccionada.getDescripcion());
                //hacer visible el linearlayout
                linearLayoutEditar.setVisibility(View.VISIBLE);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutEditar.setVisibility(View.GONE);
                personaSeleccionada = null;
            }
        });

        inicializarFirebase();
        listarPersonas();
  }

    private void  inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarPersonas(){
        databaseReference.child("Notas Agregadas").orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listNotas.clear();
                for(DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Dto_notas notas = objSnaptshot.getValue(Dto_notas.class);
                    listNotas.add(notas);
                }
                //iniciar nuestro adaptador
                listViewPersonasAdapter = new ListViewPersonasAdapter(Notas1.this, listNotas);
                // arrayAdapterPersona = new ArrayAdapter<Persona>(MainActivity.this,android.R.layout.simple_list_item_1,listPersonas);
                listViewPersonas.setAdapter(listViewPersonasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.crud_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.buscar);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listViewPersonasAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String nombres = inputNombre.getText().toString();
        String telefono = inputTelefono.getText().toString();

        switch (item.getItemId()){

            case R.id.menu_agregar:
                insertar();
                break;
            case R.id.menu_guardar:
                if(personaSeleccionada != null){
                    if(validarInputs()==false){
                        Persona p = new Persona();
                        p.setIdpersona(personaSeleccionada.getId_nota());
                        p.setNombres(nombres);
                        p.setTelefono(telefono);
                        p.setFecharegistro(personaSeleccionada.getTitulo());
                        p.setTimestamp(personaSeleccionada.getTimestamp());
                        databaseReference.child("Personas").child(p.getIdpersona()).setValue(p);
                        Toast.makeText(this, "Actualizado Correctamente", Toast.LENGTH_LONG).show();
                        linearLayoutEditar.setVisibility(View.GONE);
                        personaSeleccionada = null;
                    }
                }else{
                    Toast.makeText(this, "Seleccione una Persona", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.menu_eliminar:
                if(personaSeleccionada!=null){
                    Persona p2 = new Persona();
                    p2.setIdpersona(personaSeleccionada.getId_nota());
                    databaseReference.child("Personas").child(p2.getIdpersona()).removeValue();
                    linearLayoutEditar.setVisibility(View.GONE);
                    personaSeleccionada = null;
                    Toast.makeText(this, "Eliminado Correctamente", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Seleccione una Persona para eliminar", Toast.LENGTH_LONG).show();

                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void insertar(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(
                Notas1.this
        );
        View mView = getLayoutInflater().inflate(R.layout.contactos, null);
        Button btnInsertar = (Button) mView.findViewById(R.id.btnInsertar);
        final EditText mInputNombres = (EditText) mView.findViewById(R.id.inputNombre);
        final EditText mInputTelefono = (EditText) mView.findViewById(R.id.inputTelefono);

        mBuilder.setView(mView);
        final  AlertDialog dialog = mBuilder.create();
        dialog.show();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombres = mInputNombres.getText().toString();
                String telefono = mInputTelefono.getText().toString();
                if(nombres.isEmpty() || nombres.length()<3){
                    showError(mInputNombres, "Nombre invalido (Min. 3 letras)");
                }else if(telefono.isEmpty() || telefono.length() >8 ){
                    showError(mInputTelefono, "Telefono invalido (Min. 8 números)");
                }else{
                    Persona p = new Persona();
                    p.setIdpersona(UUID.randomUUID().toString());
                    p.setNombres(nombres);
                    p.setTelefono(telefono);
                    p.setFecharegistro(getFechaNormal(getFechaMilisegundos()));
                    p.setTimestamp(getFechaMilisegundos() * -1);
                    databaseReference.child("Personas").child(p.getIdpersona()).setValue(p);
                    Toast.makeText(Notas1.this, "Registrado Correctamente", Toast.LENGTH_LONG).show();

                    dialog.dismiss();
                }
            }
        });


    }

    public void showError(EditText input, String s){
        input.requestFocus();
        input.setError(s);
    }

    public long getFechaMilisegundos(){
        Calendar calendar =Calendar.getInstance();
        long tiempounix = calendar.getTimeInMillis();

        return tiempounix;
    }
    public String getFechaNormal(long fechamilisegundos){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        String fecha = sdf.format(fechamilisegundos);
        return fecha;
    }
    public boolean validarInputs(){
        String nombre = inputNombre.getText().toString();
        String telefono = inputTelefono.getText().toString();
        if(nombre.isEmpty() || nombre.length() < 3){
            showError(inputNombre, "Nombre invalido. (Min 3 letras)");
            return true;
        }else if(telefono.isEmpty() || telefono.length() > 8){
            showError(inputTelefono, "Telefono invalido (Min 8 números)");
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}