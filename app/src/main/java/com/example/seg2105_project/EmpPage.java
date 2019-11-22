package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.seg2105_project.data.EmployeeProfile;
import com.example.seg2105_project.data.Service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
    private List<Service> employeeServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_page);

        //Initialize all Edit Text Values
        nameET = (EditText) findViewById(R.id.nameET);
        addressET = (EditText) findViewById(R.id.addressET);
        numberET = (EditText) findViewById(R.id.numberET);
        insuranceET = (EditText) findViewById(R.id.insuranceET);
        paymentET= (EditText) findViewById(R.id.paymentET);

        //Initialize all Button value
        addBtn= (Button) findViewById(R.id.addBtn);
        deleteBtn= (Button) findViewById(R.id.deleteBtn);
        registerBtn= (Button) findViewById(R.id.registerBtn);

        //Initialize Database objects
        mAuth= FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mDatabase=  FirebaseDatabase.getInstance().getReference();

        //Initialize Service list
        listViewServices = (ListView) findViewById(R.id.serviceList);
        services = new ArrayList<Service>();
        mDatabase= FirebaseDatabase.getInstance().getReference("services");
        employeeServices= new ArrayList<Service>();

        //Initialize the list View
        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Service s= services.get(position);
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onAdd(s);
                    }
                });
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDelete(s);
                    }
                });
                return true;
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegistration();
            }
        });

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
                ServiceList servicesAdapter= new ServiceList(EmpPage.this, services);
                listViewServices.setAdapter(servicesAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onAdd(Service s){
        employeeServices.add(s);
    }

    public void onDelete(Service s){
        employeeServices.remove(s);
    }

    public void onRegistration(){
        String n= nameET.getText().toString().trim();
        String a= addressET.getText().toString().trim();
        String num= numberET.getText().toString();
        String in = insuranceET.getText().toString().trim();
        String p= paymentET.getText().toString().trim();
        EmployeeProfile tmp= new EmployeeProfile(n,a,num,in, p, employeeServices);
        DatabaseReference dR= FirebaseDatabase.getInstance().getReference("EmployeeProfile");
        dR.setValue(tmp);
    }
}
