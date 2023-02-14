package com.example.coinclubapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.coinclubapp.Adapters.MyClubsAdapter;
import com.example.coinclubapp.databinding.ActivityLoginBinding;
import com.example.coinclubapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerViewClubs.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.recyclerViewClubs.setAdapter(new MyClubsAdapter());
        binding.fab.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), HotClubActivity.class)));

        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.notification:
                    startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                    break;
                case R.id.Dashboard:
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    break;
                case R.id.money:
                    startActivity(new Intent(getApplicationContext(), MyBankActivity.class));
                    break;
            }
            return false;
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar
                , 0, 0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);

        binding.toolbar.setNavigationIcon(R.drawable.ic_menu);

        binding.drawernavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_Profile:
                        startActivity(new Intent(getApplicationContext(), ViewProfileActivity.class));
                        break;
                    case R.id.nav_ActiveClubs:

                        break;
                    case R.id.nav_HistoricalClubs:

                    case R.id.nav_AddBankDetails:
                        startActivity(new Intent(getApplicationContext(), BankDetailsActivity.class));
                        break;
                    case R.id.nav_MyBankAccounts:
                        startActivity (new Intent (getApplicationContext (), CustomerSupportActivity.class));
                        break;
                    case R.id.nav_TransactionHistory:

                        break;

                    case R.id.nav_ReferAndEarn:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                        String shareMessage = "\nLet me recommend you this application\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                        break;
                    case R.id.nav_Setting:
                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                        break;
                    case R.id.nav_logout:
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finishAffinity();
                        break;
                }

                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            finish();
            super.onBackPressed();
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}