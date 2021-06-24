package com.trizz.thaiapp.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.trizz.thaiapp.Home.HomeActivity;
import com.trizz.thaiapp.R;
import com.trizz.thaiapp.SplashAndLogin.SignInOrLoginActivity;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Lay Instance cua Firebase Auth de handle viec login hay sign in
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Lay ra nguoi dung hien tai. Neu nguoi dung chua logout thi van se con ton tai
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Intent intent;
        if (currentUser == null) {
            // Neu khong co user thi se sang man login
            intent = new Intent(this, SignInOrLoginActivity.class);
        } else {
            // Neu da co user thi vao thang man Home
            intent = new Intent(this, HomeActivity.class);
        }
        startActivity(intent);
        finish();
    }
}