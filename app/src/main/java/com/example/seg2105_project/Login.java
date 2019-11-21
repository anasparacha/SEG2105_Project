package com.example.seg2105_project;

import android.content.Intent;
import android.os.Bundle;

import com.example.seg2105_project.data.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    private Button log;
    private Button sign;
    private EditText emailEt;
    private EditText passEt;

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;

    private UserProfile usr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance();
        emailEt= (EditText) findViewById(R.id.email);
        passEt= (EditText) findViewById(R.id.pass);

        Intent i = getIntent();
        usr= (UserProfile) i.getSerializableExtra("tmp");

        log= (Button) findViewById(R.id.Login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginOnClick( emailEt.getText().toString(),passEt.getText().toString());
            }
        });


        sign= (Button) findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }


    public void openSignUp(){
        Intent intent= new Intent(this, Signup.class);
        intent.putExtra("tmp",usr);
        startActivity(intent);
    }
    public void loginOnClick(String email, String pass){
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //finish();
                    Intent i = new Intent (getApplicationContext(),Welcome.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
