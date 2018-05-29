package com.jesussantander.examenfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.DialogInterface;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetalleEquipoActivity extends AppCompatActivity {

    private ImageView fot;
    private TextView Marca;
    private TextView Color;
    private TextView Ram;
    private TextView Procesador;
    private TextView DiscoDuro;
    private TextView SistemaOperativo;
    private TextView Tipo;
    private FloatingActionButton delete;
    private DatabaseReference databaseReference;
    private static String db = "equipos";
    private String idDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_equipo);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        showToolbar(b.getString("marca") + " " + b.getString("modelo"),true);
        idDb = b.getString("idDb");
        fot = findViewById(R.id.img_computador);
        Marca = findViewById(R.id.tvDtMarca);
        Color = findViewById(R.id.tvDtColor);
        Ram = findViewById(R.id.tvDtRam);
        Procesador = findViewById(R.id.tvDtProcesador);
        DiscoDuro = findViewById(R.id.tvDtDiscoDuro);
        SistemaOperativo = findViewById(R.id.tvDtSistemaOperativo);
        Tipo = findViewById(R.id.tvDtTipo);
        delete = findViewById(R.id.fabDelete);

        String arrayram[] = this.getResources().getStringArray(R.array.ram);
        String arrayprocesador[] = this.getResources().getStringArray(R.array.procesador);
        String arraydiscoduro[] = this.getResources().getStringArray(R.array.discoduro);
        String arrayso[] = this.getResources().getStringArray(R.array.so);
        String arraytipo[] = this.getResources().getStringArray(R.array.tipo);

        fot.setImageResource(b.getInt("foto"));
        Marca.setText(b.getString("marca"));
        Color.setText(b.getString("modelo"));
        Ram.setText(arrayram[b.getInt("ram")]);
        Procesador.setText(arrayprocesador[b.getInt("procesador")]);
        DiscoDuro.setText(arraydiscoduro[b.getInt("discoduro")]);
        SistemaOperativo.setText(arrayso[b.getInt("sistemaoperativo")]);
        Tipo.setText(arraytipo[b.getInt("tipo")]);


        databaseReference = FirebaseDatabase.getInstance().getReference();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String positivo, negativo;
                AlertDialog.Builder builder = new AlertDialog.Builder(DetalleEquipoActivity.this);
                builder.setTitle(getResources().getString(R.string.eliminar));
                builder.setMessage(getResources().getString(R.string.pregunta_eliminacion));
                positivo = getResources().getString(R.string.positivo);
                negativo = getResources().getString(R.string.negativo);
                builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference.child(db).child(idDb).removeValue();
                        onBackPressed();
                    }
                });

                builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }

            public void onBackPressed() {
                finish();
                Intent i = new Intent(DetalleEquipoActivity.this,MainActivity.class);
                startActivity(i);
            }

        });

    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
