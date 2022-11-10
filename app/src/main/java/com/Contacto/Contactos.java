package com.Contacto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vg.agenda_online.R;

public class Contactos extends AppCompatActivity {

    EditText edtMensaje, edtNDT;
    FloatingActionButton btnSelec;
    Button btnSMS, btnW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        edtMensaje = findViewById(R.id.editTextPersonName);
        edtNDT = findViewById(R.id.editTextPersonNumber);
        btnSelec = findViewById(R.id.btnSelec);

        btnSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent,1);
            }
        });

        btnSMS =findViewById(R.id.button);
        btnW = findViewById(R.id.btnW);

        if(ActivityCompat.checkSelfPermission(Contactos.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestDragAndDropPermissions(Contactos.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        btnSMS.setOnClickListener((v -> {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(edtNDT.getText().toString(), null, edtMensaje.getText().toString(), null,);;

            Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
        });
    }
}