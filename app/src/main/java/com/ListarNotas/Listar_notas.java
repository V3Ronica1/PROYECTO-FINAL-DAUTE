package com.ListarNotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.MenuPrincipal;
import com.Objetos.Dto_notas;
import com.Utility;
import com.ViewHolder.MyAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.Query;
import com.vg.agenda_online.R;

import java.util.ArrayList;
import java.util.List;

public class Listar_notas extends AppCompatActivity {

    private List<Dto_notas> listNotas=new ArrayList<Dto_notas>();
    ArrayAdapter<Dto_notas> dto_notasArrayAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notas2);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Mis Notas");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


}
}