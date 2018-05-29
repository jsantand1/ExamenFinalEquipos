package com.jesussantander.examenfinal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorEquipos.OnEquipoClickListener{

    FloatingActionButton fabNew;
    private RecyclerView lstEquipos;
    private ArrayList<Equipos> equipos;
    private AdaptadorEquipos adapter;
    private LinearLayoutManager llm;
    private DatabaseReference databaseReference;
    private String bd = "equipos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToolbar(getResources().getString(R.string.toolbar_main),true);
        fabNew = findViewById(R.id.fabNew);

        fabNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreateActivity.class);
                startActivity(intent);
                MainActivity.this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        lstEquipos = findViewById(R.id.lstEquipos);
        equipos = new ArrayList<>();

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorEquipos(this,equipos,this);

        lstEquipos.setLayoutManager(llm);
        lstEquipos.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(bd).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                equipos.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Equipos e = snapshot.getValue(Equipos.class);
                        e.idDb = snapshot.getKey();
                        equipos.add(e);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
    }

    @Override
    public void onEquipoClick(Equipos e) {
        Intent intent = new Intent(MainActivity.this,DetalleEquipoActivity.class);
        Bundle b = new Bundle();
        b.putString("marca",e.marca);
        b.putString("modelo",e.modelo);
        b.putString("color",e.color);
        b.putInt("ram",e.ram);
        b.putInt("procesador",e.procesador);
        b.putInt("discoduro",e.discoduro);
        b.putInt("sistemaoperativo",e.sistemaoperativo);
        b.putInt("tipo",e.tipo);
        b.putInt("foto",e.foto);
        b.putString("idDb",e.idDb);
        intent.putExtras(b);
        startActivity(intent);
        MainActivity.this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
