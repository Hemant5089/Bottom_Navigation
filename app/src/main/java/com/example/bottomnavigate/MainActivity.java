package com.example.bottomnavigate;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        FrameLayout frameLayout = findViewById(R.id.frame_layout);

        // Load the default fragment (HomeFragment) when the activity is created
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment(), true);
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                loadFragment(new HomeFragment(), false);
            } else if (itemId == R.id.search) {
                loadFragment(new SearchFragment(), false);
            } else if (itemId == R.id.notification) {
                loadFragment(new NotificationFragment(), false);
            } else if (itemId == R.id.profile) {
                loadFragment(new ProfileFragment(), false);
            }
            return true;
        });
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frame_layout, fragment);
        } else {
            fragmentTransaction.replace(R.id.frame_layout, fragment);
        }
        fragmentTransaction.commit();
    }
}
