package com.lucas.actividad_2_formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker  datePicker;
    EditText    editTextNombre;
    EditText    editTextTelefono;
    EditText    editTextEmail;
    EditText    editTextDescripcion;
    Button      botonSiguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        botonSiguiente = (Button) findViewById(R.id.btnSiguiente);

        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                datePicker          = (DatePicker) findViewById(R.id.dpFechaNac);
                editTextNombre      = (EditText) findViewById(R.id.etNombreCompleto);
                editTextEmail       = (EditText) findViewById(R.id.etEmail);
                editTextTelefono    = (EditText) findViewById(R.id.etTelefono);
                editTextDescripcion = (EditText) findViewById(R.id.etDescripcion);

                Intent intent = new Intent(MainActivity.this, ConfirmarDatos.class);
                intent.putExtra("NOMBRE",   editTextNombre.getText().toString());
                intent.putExtra("TELEFONO", editTextTelefono.getText().toString());
                intent.putExtra("EMAIL",    editTextEmail.getText().toString());
                intent.putExtra("DESC",     editTextDescripcion.getText().toString());
                intent.putExtra("YYYY",     Integer.toString(datePicker.getYear()));
                intent.putExtra("MM",       Integer.toString(datePicker.getMonth() + 1));
                intent.putExtra("DD",       Integer.toString(datePicker.getDayOfMonth()));
                startActivity(intent);
                finish();
            }
        });

    }

    public void setData() {
        Intent intent = getIntent();
        if (((Intent) intent).hasExtra("NOMBRE")) {
            editTextNombre = (EditText) findViewById(R.id.etNombreCompleto);
            editTextNombre.setText(intent.getStringExtra("NOMBRE"));
        }
        if (intent.hasExtra("TELEFONO")) {
            editTextTelefono = (EditText) findViewById(R.id.etTelefono);
            editTextTelefono.setText(intent.getStringExtra("TELEFONO"));
        }
        if (intent.hasExtra("EMAIL")) {
            editTextEmail = (EditText) findViewById(R.id.etEmail);
            editTextEmail.setText(intent.getStringExtra("EMAIL"));
        }
        if (intent.hasExtra("DESC")) {
            editTextDescripcion = (EditText) findViewById(R.id.etDescripcion);
            editTextDescripcion.setText(intent.getStringExtra("DESC"));
        }
        showPicker(intent);
    }

    public void showPicker(Intent intent) {
        datePicker = (DatePicker) findViewById(R.id.dpFechaNac);
        if (intent.hasExtra("YYYY") && intent.hasExtra("MM") && intent.hasExtra("DD")) {
            int year  = Integer.parseInt(intent.getStringExtra("YYYY"));
            int month = Integer.parseInt(intent.getStringExtra("MM")) - 1;
            int day   = Integer.parseInt(intent.getStringExtra("DD"));
            datePicker.init(year,month,day,null);
        } else {
            final Calendar c = Calendar.getInstance();
            int year         = c.get(Calendar.YEAR);
            int month        = c.get(Calendar.MONTH);
            int day          = c.get(Calendar.DAY_OF_MONTH);
            datePicker.init(year,month,day,null);
        }
    }
}