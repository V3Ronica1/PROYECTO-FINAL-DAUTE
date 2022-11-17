package com;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module {
    public ArrayList<String> garrList=new ArrayList<>();
    public ArrayAdapter<String> garrAdpater;
    public String gvalue_id;
    public String gvalue_correo;

    public String getGvalue_id() {
        return gvalue_id;
    }

    public void setGvalue_id(String gvalue_id) {
        this.gvalue_id = gvalue_id;
    }

    public String getGvalue_correo() {
        return gvalue_correo;
    }

    public void setGvalue_correo(String gvalue_correo) {
        this.gvalue_correo = gvalue_correo;
    }
}
