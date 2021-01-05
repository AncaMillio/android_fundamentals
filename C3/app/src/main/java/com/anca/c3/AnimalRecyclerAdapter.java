package com.anca.c3;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalRecyclerAdapter extends RecyclerView.Adapter<AnimalViewHolder> {

    private final List<Animal> animals;

    public AnimalRecyclerAdapter(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = View.inflate(viewGroup.getContext(), R.layout.animal_row, null);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder animalViewHolder, int position) {
        Animal animal = animals.get(position);
        animalViewHolder.getNameText().setText(animal.getName());
        animalViewHolder.getTypeText().setText(animal.getType());
        animalViewHolder.getPriceText().setText(String.valueOf(animal.getPrice()));
    }


}
