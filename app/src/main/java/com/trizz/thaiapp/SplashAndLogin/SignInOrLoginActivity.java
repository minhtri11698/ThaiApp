package com.trizz.thaiapp.SplashAndLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.trizz.thaiapp.Adapter.ViewPagerAdapter;
import com.trizz.thaiapp.R;
import com.trizz.thaiapp.SplashAndLogin.LoginFragment;
import com.trizz.thaiapp.SplashAndLogin.SignUpFragment;

import java.util.ArrayList;

public class SignInOrLoginActivity extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_or_login);
        viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        // Tao mot ViewPager de chua 2 fragment login va sign up
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), this.getLifecycle());
        viewPagerAdapter.addFragment(LoginFragment.newInstance());
        viewPagerAdapter.addFragment(SignUpFragment.newInstance());
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, this);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        // Ngan viec nguoi dung luot de doi sang fragment ben canh
        viewPager.setUserInputEnabled(false);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0, true);
        tabLayoutMediator.attach();
    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        // Set Text cho TabLayout
        ArrayList<String> array = new ArrayList<>();
        array.add("login");
        array.add("sign in");
        tab.setText(array.get(position));
        viewPager.setCurrentItem(position, true);
    }
}