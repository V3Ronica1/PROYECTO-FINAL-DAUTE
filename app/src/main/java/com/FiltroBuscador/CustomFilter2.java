package com.FiltroBuscador;

import android.widget.Filter;

import com.Adaptadores.ListViewPersonasAdapter;
import com.Model.Dto_notas;
import com.Models.Persona;
import com.Objetos.Dto_notas;

import java.util.ArrayList;

public class CustomFilter2 extends Filter {
    ArrayList<Dto_notas> filterList;
    ListViewPersonasAdapter adapter;

    public CustomFilter2(ArrayList<Dto_notas> filterList, ListViewPersonasAdapter adapter) {
        this.filterList = filterList;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constrain) {
       FilterResults results = new FilterResults();
       if(constrain!=null &&constrain.length()>0){
           constrain = constrain.toString().toUpperCase();
           ArrayList<Persona> modeloFiltrado = new ArrayList<>();
           for(int i=0;i<filterList.size();i++){
               if(filterList.get(i).getTitulo().toUpperCase().contains(constrain) || filterList.get(i).getDescripcion().toUpperCase().contains(constrain)) {
                   modeloFiltrado.add(filterList.get(i));
               }
           }
           results.count = modeloFiltrado.size();
           results.values = modeloFiltrado;
       }else{
           results.count = filterList.size();
           results.values = filterList;
       }
       return  results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.personaData = (ArrayList<Dto_notas>) filterResults.values;
        adapter.notifyDataSetChanged();
    }
}
