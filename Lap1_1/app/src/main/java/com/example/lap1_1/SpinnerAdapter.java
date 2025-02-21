package com.example.lap1_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lap1_1.model.SpinnerItem;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<SpinnerItem> items;
    private LayoutInflater inflater;

    public SpinnerAdapter(Context context, List<SpinnerItem> items) {
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_item, parent, false);
            holder = new ViewHolder();
            holder.checkBox = convertView.findViewById(R.id.spinnerCheckBox);
            holder.textView = convertView.findViewById(R.id.spinnerText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SpinnerItem item = items.get(position);
        holder.textView.setText(item.getName());
        holder.checkBox.setChecked(item.isChecked());

        // Xử lý khi CheckBox thay đổi
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setChecked(isChecked);
        });

        return convertView;
    }

    public List<SpinnerItem> getSelectedItems() {
        return items;
    }

    private static class ViewHolder {
        CheckBox checkBox;
        TextView textView;
    }
}
