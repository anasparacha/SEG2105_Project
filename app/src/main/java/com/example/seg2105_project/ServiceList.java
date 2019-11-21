package com.example.seg2105_project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.seg2105_project.R;
import com.example.seg2105_project.data.Service;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {
    private Activity context;
    List<Service> services;
    public ServiceList(Activity context, List<Service> services){
        super(context, R.layout.activity_services, services);
        this.context=context;
        this.services=services;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_services, null, true);
        TextView name = (TextView) listViewItem.findViewById(R.id.serviceName);
        TextView role = (TextView) listViewItem.findViewById(R.id.serviceRole);
        Service service = services.get(position);
        name.setText(service.getName());
        role.setText(String.valueOf(service.getRole()));
        return listViewItem;
    }
}
