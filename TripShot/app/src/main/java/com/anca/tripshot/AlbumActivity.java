package com.anca.tripshot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AlbumActivity extends AppCompatActivity {

    TextView title;

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        title = findViewById(R.id.albumTitle);

        getData();
        
        setData();

    }

    private void getData() {
        if (getIntent().hasExtra("data")) {
            data = getIntent().getStringExtra("data");
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        title.setText(data);
    }
}