package com.example.atividade1.utils;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade1.R;

import java.util.List;

public class CustomAdapterDetalhes extends RecyclerView.Adapter<CustomAdapterDetalhes.ViewHolder> {
    private List<? extends Objects> listaObj;
    private int layout = 0;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        final View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

    public CustomAdapterDetalhes(Context context, List<? extends Objects> listaObj, int layout) {
        this.listaObj = listaObj;
        this.layout = layout;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapterDetalhes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.recycler_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        ViewHolder holder = (ViewHolder) viewHolder;
        String dadosObj = listaObj.get(position).toString();
        TextView textView = holder.view.findViewById(R.id.textViewRecycler);
        textView.setText(dadosObj);
    }

    @Override
    public int getItemCount() {
        return this.listaObj.size();
    }
}