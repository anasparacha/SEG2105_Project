package com.example.seg2105_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seg2105_project.data.Admin;
import com.example.seg2105_project.data.Employee;
import com.example.seg2105_project.data.Patient;
import com.example.seg2105_project.data.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;

public class Signup extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    private Button sign;
    private EditText usrEt;
    private EditText pass1Et;
    private EditText pass2Et;

    private UserProfile usr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance();

        Intent i = getIntent();
        usr= (UserProfile) i.getSerializableExtra("tmp");

        sign= (Button) findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpOnClick(v);
            }
        });

        usrEt= (EditText) findViewById(R.id.usr);
        pass1Et= (EditText) findViewById(R.id.pass1);
        pass2Et= (EditText) findViewById(R.id.pass2);

    }

    public void signUpOnClick(View v){

        String email=usrEt.getText().toString();
        String password= pass1Et.getText().toString();
        String hPass= hashPass(password);

        final UserProfile currUsr;

        if(usr.getRole()=="admin"){
            //create an admin object
            currUsr= new Admin(email,hPass);
        }else if(usr.getRole()=="employee"){
            //create a employee object
            currUsr= new Employee(email,hPass);
        }else{
            //create a patient object
            currUsr= new Patient(email,hPass);
        }

        //create the object in the database
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mDatabase.getReference("UserProfile").child(mAuth.getCurrentUser().getUid()).setValue(currUsr)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        finish();
                                        startActivity(new Intent(getApplicationContext(),Welcome.class));
                                    }else{
                                        //put in the toast
                                        Toast.makeText(getApplicationContext(),"Firebase Database Error",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }else{
                    //print the toast
                    Toast.makeText(getApplicationContext(),"Authentication error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public String hashPass(String s){
        try{
            MessageDigest digest= MessageDigest.getInstance("SHA-256");
            byte [] h= digest.digest(s.getBytes("UTF-8"));
            StringBuffer hs= new StringBuffer();
            for(int i=0; i<h.length; i++){
                String hex= Integer.toHexString(0xff & h[i]);
                if(hex.length()==1){
                    hs.append('0');
                }
                hs.append(hex);
            }
            return hs.toString();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
