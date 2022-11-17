package com.vg.agenda_online;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }

    public void comoCrear(View v)
        {
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle("Como puedes usar la opción Agregar Notas");
            String   []informacion={"En esta seccion de Agregar Nota es donde se va a poder insertar las notas que desees, esta opción le permitirá poder marcar la fecha en la que realizo la nota, como tambien el titulo de la nota y cuenta con un apartado donde va a poder describir todos los detalles de su nota, para luego se registrada en su app, y pueda visualizarla cuando desee."};
            builder.setItems(informacion, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setPositiveButton("Confirmar", null);
            builder.create().show();
        }

        public void misnotas(View v)
        {
            AlertDialog.Builder minotasagenda= new AlertDialog.Builder(this);
            minotasagenda.setTitle("Para que sirve la opción Mis Notas");
            String []misnotasA={"Esta sección de la app sirve para que el usuario pueda verificar el listado de sus notas que tiene registradas, es decir si quiere ver los detalles de una de sus notas registradas, solo debe de hacer clik y le llevara a la parte donde podra ver los detalles de su nota."};
            minotasagenda.setItems(misnotasA, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            minotasagenda.setPositiveButton("Confirmar", null);
            minotasagenda.create().show();
        }

        public void contacto(View v)
        {
            AlertDialog.Builder opccontacto= new AlertDialog.Builder(this);
            opccontacto.setTitle("Para que sirve la opción Mis Contactos");
            String []contactoop={"Esta sección de la app sirve para que el usuario pueda agregar contactos en la aplicación, ademas cuenta con las opciones de modificar, y eliminar un contacto ya existente. /n Tambien puede observar la lista de contactos que tiene registrados."};
            opccontacto.setItems(contactoop, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            opccontacto.setPositiveButton("Confirmar", null);
            opccontacto.create().show();
        }

        public void acercade(View v)
        {
            AlertDialog.Builder opcacerca= new AlertDialog.Builder(this);
            opcacerca.setTitle("Para que sirve la opción Acerca");
            String []acercaopc={"Esta sección de la app sirve para que el usuario pueda ver un pequeño cuadro de dialogo donde se mostrara los nombres de los desarrolladores de dicha aplicación."};
            opcacerca.setItems(acercaopc, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            opcacerca.setPositiveButton("Confirmar", null);
            opcacerca.create().show();
        }

        //public void salir(View v)
        //{
          //  AlertDialog.Builder opcsalir=new AlertDialog.Builder(this);
            //opcsalir.setTitle("Para que sirve la opción Salir");
            //String []saliropc={"Esta opcion sirve para que los usuarios puedan salir "}
        //}
}