package com.anca.tripshot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<Bitmap> images = new ArrayList<>();
    private ArrayList<String> captions = new ArrayList<>();
    Context context;

    ScrollView scroll;

    public AlbumAdapter(Context context, ArrayList<String> list, ArrayList<Bitmap> images, ArrayList<String> captions) {
        this.context = context;
        data = list;
        this.images = images;
        this.captions = captions;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.memory_layout, parent, false);

        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.AlbumViewHolder holder, int position) {
        holder.text.setText(data.get(position));
        holder.caption.setText(captions.get(position));
        Bitmap image = images.get(position);
        holder.memory_image.setImageBitmap(image);

//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, AlbumActivity.class);
//                intent.putExtra("title", data.get(position));
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        TextView text, caption;
        ImageView memory_image;
        ConstraintLayout mainLayout;


        @SuppressLint({"ClickableViewAccessibility", "WrongConstant"})
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.memory_title);
            mainLayout = itemView.findViewById(R.id.memory_mainLayout);
            scroll = itemView.findViewById((R.id.childScroll));
            caption = itemView.findViewById(R.id.caption);
            memory_image = itemView.findViewById(R.id.itemImage);

            //caption.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);


            scroll.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    // Disallow the touch request for parent scroll on touch of child view
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });

        }


    }
}
