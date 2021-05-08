package com.trizz.thaiapp.Home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.trizz.thaiapp.R;
import com.trizz.thaiapp.SplashAndLogin.SignInOrLoginActivity;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final int LOGOUT = R.id.log_out_app;
    private static final int EXIT = R.id.exit_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showFragment(TopicSelectFragment.newInstance(), false, R.id.container);
        mAuth = FirebaseAuth.getInstance();
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case LOGOUT:
                logOutApp();
                break;
            case EXIT:
                this.finish();
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logOutApp() {
        mAuth.signOut();
        Intent intent = new Intent(this, SignInOrLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void showFragment(Fragment fragment, Boolean addToBackStack , int layoutId) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction()
                .add(layoutId, fragment, fragment.getClass().getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (addToBackStack) transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }
}