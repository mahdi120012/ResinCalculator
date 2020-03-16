package com.e.resincalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<RecyclerModel> recyclerModels;
    Context c;
    String rowLayoutType,className;
    RecyclerAdapter RecyclerAdapter;
    ImageView imgSabad;

    public RecyclerAdapter(ArrayList<RecyclerModel> recyclerModels, String rowLayoutType, Context c,
                           RecyclerAdapter recyclerAdapterYouHaveKnow) {
        this.recyclerModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.RecyclerAdapter = recyclerAdapterYouHaveKnow;
    }


    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       if (rowLayoutType.contains("add_main")) {
           return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main, parent, false));
       }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {


        holder.txVideoName.setText(recyclerModels.get(position).getOnvan());


        if(recyclerModels.get(position).getPicture().isEmpty()){

            Picasso.get()
                    .load(R.drawable.no_image)
                    .fit()
                    .error(R.drawable.no_image)
                    .into(holder.imgVideoPicture);

        }else {
            Picasso.get()
                    .load(recyclerModels.get(position).getPicture())
                    .centerCrop()
                    .fit()
                    .error(R.drawable.no_image)
                    .into(holder.imgVideoPicture);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String onvan = recyclerModels.get(position).getOnvan();
                String matn = recyclerModels.get(position).getMatn();
                String link = recyclerModels.get(position).getLink();

                Intent intent = new Intent(c, Cat1.class);
                intent.putExtra("onvan", onvan);
                intent.putExtra("matn", matn);
                intent.putExtra("link", link);
                c.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txVideoName;
        ImageView imgVideoPicture;
        MyViewHolder(View view) {
            super(view);

            txVideoName = itemView.findViewById(R.id.txVideoNameInRowMain);
            imgVideoPicture = itemView.findViewById(R.id.imgVideoPictureInRowMain);

        }
    }
}