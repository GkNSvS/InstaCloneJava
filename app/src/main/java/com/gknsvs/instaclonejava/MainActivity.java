package com.gknsvs.instaclonejava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gknsvs.instaclonejava.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        mAuth=FirebaseAuth.getInstance();
        if(currentUser()){
            gotoFeedActivity();
        }

    }

    private void gotoFeedActivity() {
        Intent intent=new Intent(MainActivity.this,FeedActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean currentUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
            return true;
        return false;
    }

    public void btn_SignInOnClick(View view){
        String eMail=binding.txtName.getText().toString();
        String pass=binding.txtPassword.getText().toString();
        if(eMail.isEmpty()||pass.isEmpty()){
            Toast.makeText(MainActivity.this,"Enter e-mail and password",Toast.LENGTH_LONG).show();
        }else{
            mAuth.signInWithEmailAndPassword(eMail,pass).addOnSuccessListener(MainActivity.this, new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    gotoFeedActivity();
                }
            }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    public void btn_SignUpOnClick(View view){
        String eMail=binding.txtName.getText().toString();
        String pass=binding.txtPassword.getText().toString();
        if(eMail.isEmpty()||pass.isEmpty()){
            Toast.makeText(MainActivity.this,"Enter e-mail and password",Toast.LENGTH_LONG).show();
        }else{
            mAuth.createUserWithEmailAndPassword(eMail,pass)
                    .addOnSuccessListener(MainActivity.this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            gotoFeedActivity();
                        }
                    }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });

        }

    }
}