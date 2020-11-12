package com.lucas.actividad_2_formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatos extends AppCompatActivity {

    private String strNombre;
    private String strYYYY;
    private String strMM;
    private String strDD;
    private String strEmail;
    private String strTelefono;
    private String strDesc;
    private Button botonEditarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);
        botonEditarDatos = (Button) findViewById(R.id.btnEditarDatos);
        getData();
        setData();

        botonEditarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmarDatos.this, MainActivity.class);
                intent.putExtra("NOMBRE",   strNombre);
                intent.putExtra("TELEFONO", strTelefono);
                intent.putExtra("EMAIL",    strEmail);
                intent.putExtra("DESC",     strDesc);
                intent.putExtra("YYYY",     strYYYY);
                intent.putExtra("MM",       strMM);
                intent.putExtra("DD",       strDD);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getData() {
        Intent intent = getIntent();
        strNombre   = intent.getStringExtra("NOMBRE");
        strTelefono = intent.getStringExtra("TELEFONO");
        strEmail    = intent.getStringExtra("EMAIL");
        strDesc     = intent.getStringExtra("DESC");
        strYYYY     = intent.getStringExtra("YYYY");
        strMM       = intent.getStringExtra("MM");
        strDD       = intent.getStringExtra("DD");
    }

    public void setData() {
        Intent intent = getIntent();
        TextView textViewNombre      = (TextView) findViewById(R.id.etConfNombre);
        TextView textViewFecha       = (TextView) findViewById(R.id.etConfFechaNac);
        TextView textViewTelefono    = (TextView) findViewById(R.id.etConfTelefono);
        TextView textViewEmail       = (TextView) findViewById(R.id.etConfEmail);
        TextView textViewDescripcion = (TextView) findViewById(R.id.etConfDescripcion);

        textViewNombre.setText(strNombre);
        textViewTelefono.setText(strTelefono);
        textViewEmail.setText(strEmail);
        textViewDescripcion.setText(strDesc);
        textViewFecha.setText(strDD +
                "/" + strMM +
                "/" + strYYYY);
    }

    @Override
    public boolean onKeyDown (int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}