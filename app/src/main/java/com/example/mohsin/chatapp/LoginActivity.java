package com.example.mohsin.chatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {
    MaterialEditText ppass,eemail;
    Button bbtn;

    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar tools=(Toolbar)findViewById(R.id.tool);
        setSupportActionBar(tools);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       ppass=(MaterialEditText)findViewById(R.id.pasword_l);
       eemail=(MaterialEditText)findViewById(R.id.email_l);
       bbtn=(Button)findViewById(R.id.btn_log);
       bbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String pp=ppass.getText().toString();
               String ee=eemail.getText().toString();
               if (TextUtils.isEmpty(ee) || TextUtils.isEmpty(pp) ){
                   Toast.makeText(getApplicationContext(),"All Fields Are Required",Toast.LENGTH_SHORT).show();
               }else {
                   fauth.signInWithEmailAndPassword(ee, pp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               Intent in=new Intent(LoginActivity.this,MainActivity.class);
                               in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);


                               startActivity(in);
                               finish();
                           }else {
                               Toast.makeText(getApplicationContext(),"Authentication Failed",Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
           }
       });

    }
}
