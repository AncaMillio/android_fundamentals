package com.anca.tripshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MyTrips extends AppCompatActivity implements View.OnClickListener{

    String s[];
    ArrayList<String> list = new ArrayList<>();
    RecyclerView recyclerView;
    Button add, logout;
    EditText etTitle;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);

        recyclerView = findViewById(R.id.recyclerView);
        add = findViewById(R.id.addTrip);
        etTitle = findViewById(R.id.etTitle);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);


        add.setOnClickListener(this);

        s = getResources().getStringArray(R.array.trips);
        for(String title : s) {
            list.add(title);
        }

        MyAdapter myAdapter = new MyAdapter(this, list);
        adapter = myAdapter;
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addTrip:
                list.add(0, etTitle.getText().toString().trim());
                etTitle.getText().clear();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                adapter.notifyDataSetChanged();
                break;
            case R.id.logout:
                //getSharedPreferences()
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MyTrips.this, MainActivity.class));
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1000) {
            switch (permissions[0]) {
                case Manifest.permission.CAMERA:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //do something with the camera
                    }
                    break;
            }
        }
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            list.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
    };
}