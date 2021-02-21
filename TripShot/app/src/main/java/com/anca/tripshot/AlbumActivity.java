package com.anca.tripshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    String data;
    String s[];
    String caps[];
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> captions = new ArrayList<>();

    int[] images = {R.drawable.art_museum, R.drawable.natural_park, R.drawable.plaza, R.drawable.beach, R.drawable.opera, R.drawable.water_park, R.drawable.city_tour, R.drawable.sunset, R.drawable.club, R.drawable.restaurant, R.drawable.old_town};
    ArrayList<Bitmap> bitImages = new ArrayList<>();
    Button addTripButton, logout_btn;

    RecyclerView recyclerView;
    AlbumAdapter adapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        addTripButton = findViewById(R.id.addTrip);
        addTripButton.setOnClickListener(this);
        logout_btn = findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(this);

        s = getResources().getStringArray(R.array.memories);
        for(String title : s) {
            titles.add(title);
        }

        caps = getResources().getStringArray(R.array.captions);
        for(String caption : caps) {
            captions.add(caption);
        }

        for(int image : images) {
            Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                    image);
            bitImages.add(icon);
        }

        recyclerView = findViewById(R.id.recyclerView);
        AlbumAdapter albumAdapter = new AlbumAdapter(this, titles, bitImages, captions);
        adapter = albumAdapter;
        recyclerView.setAdapter(albumAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        title = findViewById(R.id.albumTitle);
        getData();
        setData();

        getNewMemory();

    }

    private void getData() {
        if (getIntent().hasExtra("data")) {
            data = getIntent().getStringExtra("data");
        } else {
            Toast.makeText(this, "No title.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getNewMemory() {
        if (getIntent().hasExtra("path")) {
            String filePath=getIntent().getStringExtra("path");
            String title = getIntent().getStringExtra("Title");
            String caption = getIntent().getStringExtra("caption");
            File file = new File(filePath);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            bitImages.add(0, bitmap);
            titles.add(0, title);
            captions.add(0, caption);
            adapter.notifyDataSetChanged();
        } else {
            //Toast.makeText(this, "No path to file.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        title.setText(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addTrip:

                Intent intent = new Intent(this, AddMemory.class);
                intent.putExtra("album_title", title.getText().toString());
                this.startActivity(intent);
                finish();

                break;
            case R.id.logout_btn:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            titles.remove(viewHolder.getAdapterPosition());
            bitImages.remove(viewHolder.getAdapterPosition());
            captions.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
    };
}