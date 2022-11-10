package com.Contacto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.ActionCodeUrl;
import com.vg.agenda_online.R;

public class Contactos extends AppCompatActivity {

    EditText edtMensaje, edtNDT;
    FloatingActionButton btnSelec;
    Button btnSMS, btnW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        edtMensaje = findViewById(R.id.edtMensaje);
        edtNDT = findViewById(R.id.edtNDT);
        btnSelec = findViewById(R.id.btnSelec);

        btnSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent,1);
            }
        });

        btnSMS =findViewById(R.id.btnSMS);
        btnW = findViewById(R.id.btnW);

        if(ActivityCompat.checkSelfPermission(Contactos.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestDragAndDropPermissions(Contactos.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        btnSMS.setOnClickListener((v -> {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(edtNDT.getText().toString(), null, edtMensaje.getText().toString(), null);

            Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
        }));

        btnW.setOnClickListener((view -> {
            if(edtNDT.getText().toString().isEmpty()){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, edtMensaje.getText().toString());
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }else{

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                String url = "whatsapp://send?phone="+edtNDT.getText().toString()+"&text="+edtMensaje.getText().toString();
                sendIntent.setData(Url.parse(url));
                startActivity(sendIntent);
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){

            Url url = data.getData();
            Cursor cursor = getContentResolver().query(url, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()){

                int indiceName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int indiceNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String nombre = cursor.getString(indiceName);
                String numero = cursor.getString(indiceNumber);

                edtMensaje.setText(nombre);
                edtNDT.setText(numero);
            }
        }

    }
}