package com.example.seg2105_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.seg2105_project.data.Admin;
import com.example.seg2105_project.data.Employee;
import com.example.seg2105_project.data.Patient;
import com.example.seg2105_project.data.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Welcome extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private TextView msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuth= FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mDatabase=  FirebaseDatabase.getInstance().getReference();
        //initialize the edit text variable with the id
        msg= (TextView) findViewById(R.id.message);

        if(mUser==null){
            finish();
        }else{
            mDatabase.child("UserProfile").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserProfile usr= dataSnapshot.getValue(UserProfile.class);
                    String name;
                    String role= usr.getRole();

                    if(role=="admin"){
                        Admin a;
                        a= (Admin) usr;
                        name= a.getUserName();
                    }else if(role=="employee"){
                        Employee e;
                        e= (Employee) usr;
                        name= e.getUserName();
                    }else{
                        Patient p;
                        p= (Patient) usr;
                        name=p.getUserName();
                    }
                    msg.setText("Welcome" + name + "you have signed in as a " + role);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

}
