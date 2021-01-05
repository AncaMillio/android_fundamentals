package com.anca.c3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://developer.android.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Animal> animals = getAnimals();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        AnimalRecyclerAdapter adapter = new AnimalRecyclerAdapter(animals);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private List<Animal> getAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();

        animals.add(new Animal("Fluffy", "CAT", 1500));
        animals.add(new Animal("Sddd", "Pig", 1000));
        animals.add(new Animal("Ion", "CAT", 1060));
        animals.add(new Animal("Mari", "CAT", 1000));
        animals.add(new Animal("Dodo", "Dog", 4000));
        animals.add(new Animal("Bubu", "Parrot", 1500));
        animals.add(new Animal("COco", "Rat", 1000));
        animals.add(new Animal("Dudu", "Cat", 1770));
        animals.add(new Animal("Luli", "Dog", 1000));
        animals.add(new Animal("Cori", "CAT", 1440));
        animals.add(new Animal("Didi", "CAT", 4000));
        animals.add(new Animal("Bobi", "Dog", 1060));
        animals.add(new Animal("Mimi", "Parrot", 3000));
        animals.add(new Animal("Nori", "Rabbit", 1060));

        return animals;
    }
}