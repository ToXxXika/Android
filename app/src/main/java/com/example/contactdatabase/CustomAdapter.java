package com.example.contactdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactdatabase.Models.Utilisateur;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Utilisateur> data;
    private OnUpdateClickListener onUpdateClickListener;

    public void setOnUpdateClickListener(OnUpdateClickListener onUpdateClickListener) {
        this.onUpdateClickListener = onUpdateClickListener;
    }

    public CustomAdapter(List<Utilisateur> data,OnUpdateClickListener onUpdateClickListener) {
        this.onUpdateClickListener = onUpdateClickListener;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Utilisateur item = data.get(position);
        holder.user.setText(item.getEmail());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onUpdateClickListener.onUpdateClick(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView user;
        TextView Email;
        Button update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.user_name);
            update = itemView.findViewById(R.id.update_button);
         }
    }
    public interface OnUpdateClickListener {
        void onUpdateClick(int position);
    }

}


