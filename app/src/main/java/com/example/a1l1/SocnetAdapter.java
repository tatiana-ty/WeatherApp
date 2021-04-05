package com.example.a1l1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SocnetAdapter extends RecyclerView.Adapter<SocnetAdapter.ViewHolder> {

    ArrayList <String> cities;
    ArrayList <String> temperatures;

    public SocnetAdapter(ArrayList <String> cities, ArrayList <String> temperatures){
        this.cities = cities;
        this.temperatures = temperatures;
    }

    @NonNull
    @Override
    public SocnetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.city_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SocnetAdapter.ViewHolder viewHolder, int i) {
        String[] dataSource = new String[getItemCount()];
        int n = 0;
        for (int j = 0; j < dataSource.length; j++) {
            if (j % 2 == 0) {
                dataSource[j] = cities.get(n);
            }
            else {
                if (MainActivity.degrees.equals("°F")) {
                    int t = Integer.parseInt(temperatures.get(n)) * 9 / 5 + 32;
                    dataSource[j] = String.valueOf(t);
                } else {
                    dataSource[j] = temperatures.get(n);
                }
                n++;
            }
        }
        viewHolder.getTextView().setText(dataSource[i]);
    }

    @Override
    public int getItemCount() {
        return cities.size() + temperatures.size();
    }

    public void add(String city, String temperature) {
        cities.add(city);
        temperatures.add(temperature);
        notifyItemInserted(getItemCount() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
