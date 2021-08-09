package com.r2d2.doctorapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlainTextRecyclerAdapter extends RecyclerView.Adapter<PlainTextRecyclerAdapter.ViewHolder> {

    private final List<String> strings;

    public PlainTextRecyclerAdapter(List<String> strings) {
        this.strings = strings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.plain_text_recycler_item, parent, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView infoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            infoTextView = itemView.findViewById(R.id.plain_text_recycler_item_text);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.infoTextView.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}