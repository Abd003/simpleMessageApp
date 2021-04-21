package com.abdulrehman.i170357;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> {
    List<Contact> contacts;
    Context c;
    private OnItemListner itListner;

    public MyRvAdapter(List<Contact> contacts, Context c, OnItemListner itListner) {
        this.contacts = contacts;
        this.c = c;
        this.itListner = itListner;
    }

    @NonNull
    @Override
    public MyRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new MyViewHolder(itemView, itListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRvAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(contacts.get(position).getName());
        holder.address.setText(contacts.get(position).getAddress());
        holder.phone.setText(contacts.get(position).getPhno());
        if (contacts.get(position).getImage()!=null) {
            byte[] decodedString = Base64.decode(contacts.get(position).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.image.setImageBitmap(decodedByte);
        }

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, address, phone;
        CircleImageView image;
        LinearLayout sin_row;
        OnItemListner onItemListner;
        public MyViewHolder(@NonNull View itemView, OnItemListner onItemListner) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.number);
            image=itemView.findViewById(R.id.image);
            sin_row = itemView.findViewById(R.id.sin_row);
            this.onItemListner = onItemListner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListner.ONItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListner{
        void ONItemClick(int position);
    }
}
