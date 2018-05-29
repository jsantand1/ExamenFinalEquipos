package com.jesussantander.examenfinal;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.EquipoViewHolder> {
    private ArrayList<Equipos> equipos;
    private OnEquipoClickListener clickListener;
    private Activity activity;

    public AdaptadorEquipos(Activity activity, ArrayList<Equipos> equipos, OnEquipoClickListener clickListener){
        this.activity = activity;
        this.equipos=equipos;
        this.clickListener = clickListener;
    }

    @Override
    public EquipoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_computadores,parent,false);
        return new EquipoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EquipoViewHolder holder, int position) {
        final Equipos e = equipos.get(position);
        String ram[], proc[], dd[];
        ram = activity.getResources().getStringArray(R.array.ram);
        proc = activity.getResources().getStringArray(R.array.procesador);
        dd = activity.getResources().getStringArray(R.array.discoduro);
        holder.foto.setImageResource(e.foto);
        holder.foto.setScaleType(ImageView.ScaleType.FIT_CENTER);
        holder.modelo.setText(e.modelo);
        holder.procesador.setText(proc[e.procesador]);
        holder.memoria.setText(ram[e.ram]);
        holder.discoduro.setText(dd[e.discoduro]);
        holder.marca.setText(e.marca);

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onEquipoClick(e);
            }
        });
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public static class EquipoViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView modelo;
        private TextView procesador;
        private TextView memoria;
        private TextView discoduro;
        private TextView marca;
        private View v;

        public EquipoViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgCard);
            modelo = v.findViewById(R.id.tvModelo);
            procesador = v.findViewById(R.id.tvProcesador);
            memoria = v.findViewById(R.id.tvMemoria);
            discoduro = v.findViewById(R.id.tvDiscoDuro);
            marca = v.findViewById(R.id.tvMarca);
        }

    }

    public interface OnEquipoClickListener{
        void onEquipoClick(Equipos e);
    }
}
