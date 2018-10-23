package com.example.mohsin.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    Button button, button1;
    FirebaseUser userr;


    @Override
    protected void onStart() {
        super.onStart();

        userr= FirebaseAuth.getInstance().getCurrentUser();
        if(userr !=null){
            Intent in=new Intent(StartActivity.this,MainActivity.class);
            startActivity(in);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);






    button=(Button)findViewById(R.id.but);
    button1=(Button)findViewById(R.id.but2);


    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in=new Intent(StartActivity.this,LoginActivity.class);
            startActivity(in);
            finish();
        }
    });

    button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intt=new Intent(StartActivity.this,Registration.class);
            startActivity(intt);
            finish();
        }
    });
    }
}
