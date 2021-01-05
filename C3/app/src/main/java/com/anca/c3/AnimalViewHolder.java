package com.anca.c3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalViewHolder extends RecyclerView.ViewHolder {

    private final TextView nameText;
    private final TextView typeText;
    private final TextView priceText;

    public AnimalViewHolder(@NonNull View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.name);
        typeText = itemView.findViewById(R.id.type);
        priceText = itemView.findViewById(R.id.price);

    }

    public TextView getNameText() {
        return nameText;
    }

    public TextView getTypeText() {
        return typeText;
    }

    public TextView getPriceText() {
        return priceText;
    }


}
