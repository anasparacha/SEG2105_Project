package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.seg2105_project.data.Service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class EmpPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private EditText nameET;
    private EditText addressET;
    private EditText numberET;
    private EditText insuranceET;
    private EditText paymentET;
    private Button addBtn;
    private Button deleteBtn;
    private Button registerBtn;
    private ListView listViewServices;
    private List<Service> services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_page);

        //Initialize all Edit Text Values
        //Initialize all Button value
        //Initialize the list View
        //Initialize Database objects
        //Initialize Service list

    }
    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Service service= postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                ServiceList servicesAdapter= new ServiceList(services.this,services);
                listViewServices.setAdapter(servicesAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
