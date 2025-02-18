package com.example.listphone;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapter extends android.widget.ArrayAdapter<Phone> {
  private   Activity context;
   private int IdLayout;
   private ArrayList<Phone> phoneList;


    public ArrayAdapter( Activity context, int IdLayout , ArrayList<Phone> List) {
        super(context, IdLayout ,List);
        this.context = context;
        this.IdLayout = IdLayout;
        this.phoneList = List;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflater = context.getLayoutInflater();
        convertView = myInflater.inflate(IdLayout , null);

        Phone myPhone = phoneList.get(position);
        ImageView img_phone = convertView.findViewById(R.id.img_phone);
        img_phone.setImageResource(myPhone.getImage());

        TextView txt_phone = convertView.findViewById(R.id.txt_phone);
        txt_phone.setText(myPhone.getName());

        return convertView;
    }
}
