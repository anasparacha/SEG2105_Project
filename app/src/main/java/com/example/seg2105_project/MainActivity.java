package com.example.seg2105_project;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.seg2105_project.data.UserProfile;


public class MainActivity extends AppCompatActivity {

    private Button adm;
    private Button emp;
    private Button pat;
    private UserProfile usr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adm= (Button) findViewById(R.id.adm);
        adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginAdm();
            }
        });
        emp= (Button) findViewById(R.id.emp);
        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginEmp();
            }
        });
        pat= (Button) findViewById(R.id.pat);
        pat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginPat();
            }
        });

    }

    public void openLoginAdm(){
        Intent intent= new Intent(this, Login.class);
        usr= new UserProfile("admin");
        intent.putExtra("tmp",usr);
        startActivity(intent);
    }
    public void openLoginEmp(){
        Intent intent= new Intent(this, Login.class);
        usr= new UserProfile("employee");
        intent.putExtra("tmp",usr);
        startActivity(intent);

    }
    public void openLoginPat(){
        Intent intent= new Intent(this, Login.class);
        usr= new UserProfile("patient");
        intent.putExtra("tmp",usr);
        startActivity(intent);

    }

}
