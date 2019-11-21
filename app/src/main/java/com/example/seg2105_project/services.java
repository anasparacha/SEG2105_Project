package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.seg2105_project.data.Service;
import com.example.seg2105_project.data.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class services extends AppCompatActivity {

    private Button add;
    private Button edit;
    private Button delete;
    private EditText name;
    private EditText role;
    private ListView listViewServices;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private Boolean isAdmin;
    private List<Service> services;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        mAuth= FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mDatabase=  FirebaseDatabase.getInstance().getReference();

        isAdmin= false;

        //Checking if the user is an admin
        if(mUser==null){
            finish();
        }else{
            mDatabase.child("UserProfile").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserProfile usr= dataSnapshot.getValue(UserProfile.class);
                    String role= usr.getRole();
                    if(role=="admin"){
                        isAdmin=true;
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        //Initialize Edit text fields
        name = (EditText) findViewById(R.id.serviceName);
        role = (EditText) findViewById(R.id.serviceRole);

        //Initialize list of services
        listViewServices = (ListView) findViewById(R.id.serviceList);
        services = new ArrayList<Service>();
        mDatabase= FirebaseDatabase.getInstance().getReference("services");

        //Button Actions
        add= (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdd();
            }
        });
        //EDIT AND DELETE SERVICES
        edit= (Button) findViewById(R.id.edit);
        delete= (Button) findViewById(R.id.delete);
        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Service s= services.get(position);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onEdit();
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDelete();
                    }
                });
                return true;
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
                ServiceList servicesAdapter= new ServiceList(services.this,services);
                listViewServices.setAdapter(servicesAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onAdd(){
        if(isAdmin){
            //add the service
            String n=name.getText().toString().trim().toLowerCase();
            String r=role.getText().toString().trim().toLowerCase();
            if(n!=null && (r!="doctor" || r!="nurse" || r!="staff") ){
                Service toAdd= new Service(n,r);
                String id = mDatabase.push().getKey();
                mDatabase.child(id).setValue(toAdd);
                name.setText("");
                role.setText("");
                Toast.makeText(this, "Service Added", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"The required fields are incorrect or empty",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Action failed. Only admins can add services",Toast.LENGTH_SHORT).show();
        }
    }
    public void onEdit(){
        if(isAdmin){
            String n=name.getText().toString().trim().toLowerCase();
            String r=role.getText().toString().trim().toLowerCase();
            DatabaseReference dR= FirebaseDatabase.getInstance().getReference("services").child(n);
            Service s= new Service(n,r);
            dR.setValue(s);
            Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Action failed. Only admins can edit services",Toast.LENGTH_SHORT).show();
        }

    }
    public void onDelete(){
        if(isAdmin){
            //delete the service
            String n=name.getText().toString().trim().toLowerCase();
            DatabaseReference dR= FirebaseDatabase.getInstance().getReference("products").child(n);
            dR.removeValue();
            Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Action failed. Only admins can delete services",Toast.LENGTH_SHORT).show();
        }

    }
}

