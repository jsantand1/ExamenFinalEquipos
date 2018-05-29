package com.jesussantander.examenfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class CreateActivity extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;

    Equipos equipos;

    EditText marca;
    EditText modelo;
    EditText color;


    Spinner cboRAM;
    Spinner cboProcesador;
    Spinner cboDiscoDuro;
    Spinner cboSistemaOperativo;
    Spinner cboTipo;


    Button btnRegistrar;

    private String elementos[];

    private String images[];

    private ArrayAdapter<String> adapter;

    private ArrayList<Integer> fotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        showToolbar(getResources().getString(R.string.toolbar_create),true);
        marca = findViewById(R.id.txtMarca);
        modelo = findViewById(R.id.txtModelo);
        color = findViewById(R.id.txtColor);
        cboRAM = findViewById(R.id.spnRam);
        cboProcesador = findViewById(R.id.spnProcesador);
        cboDiscoDuro = findViewById(R.id.spnDiscoDuro);
        cboSistemaOperativo = findViewById(R.id.spnSistemaOp);
        cboTipo = findViewById(R.id.spnTipo);

        fotos = new ArrayList<Integer>();
        fotos.add(R.drawable.allinone);
        fotos.add(R.drawable.escritorio);
        fotos.add(R.drawable.portatil);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        elementos = this.getResources().getStringArray(R.array.ram);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,elementos);
        cboRAM.setAdapter(adapter);

        elementos = this.getResources().getStringArray(R.array.procesador);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,elementos);
        cboProcesador.setAdapter(adapter);

        elementos = this.getResources().getStringArray(R.array.discoduro);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,elementos);
        cboDiscoDuro.setAdapter(adapter);

        elementos = this.getResources().getStringArray(R.array.so);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,elementos);
        cboSistemaOperativo.setAdapter(adapter);

        elementos = this.getResources().getStringArray(R.array.tipo);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,elementos);
        cboTipo.setAdapter(adapter);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                equipos = new Equipos(marca.getText().toString(), modelo.getText().toString(), color.getText().toString(), cboRAM.getSelectedItemPosition(), cboProcesador.getSelectedItemPosition(), cboDiscoDuro.getSelectedItemPosition(), cboSistemaOperativo.getSelectedItemPosition(), cboTipo.getSelectedItemPosition(), fotoAleatoria(fotos));
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference();
                String key = myRef.child("equipos").push().getKey();

                myRef.child("equipos").push().setValue(equipos);
                Intent intent = new Intent(CreateActivity.this,MainActivity.class);
                startActivity(intent);
                CreateActivity.this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public static int fotoAleatoria(ArrayList<Integer> fotos){
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(fotos.size());
        return  fotos.get(fotoSeleccionada);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
