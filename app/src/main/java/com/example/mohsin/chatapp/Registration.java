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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class Registration extends AppCompatActivity {


   MaterialEditText user,em,pass;
    Button register;
    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

      Toolbar toolbar=(Toolbar)findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user=(MaterialEditText)findViewById(R.id.username);
        em=(MaterialEditText)findViewById(R.id.email);
        pass=(MaterialEditText)findViewById(R.id.pasword);
        register=(Button)findViewById(R.id.btn);
        auth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String us=user.getText().toString();
                String email=em.getText().toString();
                String password=pass.getText().toString();
                if (TextUtils.isEmpty(us) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"All Fields Are Required",Toast.LENGTH_SHORT).show();
                }else if (password.length()< 6){
                    Toast.makeText(getApplicationContext(),"Password must be atleat 6 characters",Toast.LENGTH_SHORT).show();

                }else {
                    register(us, email, password);
                }
            }
        });
    }

    private void register(final String user, String em, String pass){

auth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        if (task.isSuccessful()){
            FirebaseUser fbuser=auth.getCurrentUser();
            String userId=fbuser.getUid();
            reference= FirebaseDatabase.getInstance().getReference("Users").child(userId);

            HashMap<String,String> hash=new HashMap<>();
            hash.put("id",userId);
            hash.put("username",user);
            hash.put("imageURL","default");
            reference.setValue(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Intent intent=new Intent(Registration.this,StartActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }else {
            Toast.makeText(getApplicationContext(),"You Can't Register with this Email and Password",Toast.LENGTH_SHORT).show();
        }
    }
});
    }
}
