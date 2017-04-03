package com.example.aditya.realmexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by prathvi on 3/4/17.
 */

class DataList extends RecyclerView.Adapter<DataList.ViewHolder> {

    private List<DataObject> dataObjects;

    public DataList(List<DataObject> dataList) {
        this.dataObjects = dataList;
    }

    @Override
    public DataList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_items,parent,false);
        return new DataList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataList.ViewHolder holder, int position) {
        holder.tv_id.setText(String.valueOf(dataObjects.get(position).getId()));
        holder.tv_name.setText(dataObjects.get(position).getName());
        holder.tv_age.setText(dataObjects.get(position).getAge());
        holder.tv_number.setText(dataObjects.get(position).getNumber());
        holder.tv_email.setText(dataObjects.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return dataObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_id, tv_name, tv_age, tv_number, tv_email;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_age = (TextView) itemView.findViewById(R.id.tv_age);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_email = (TextView) itemView.findViewById(R.id.tv_email);
        }

    }
}
