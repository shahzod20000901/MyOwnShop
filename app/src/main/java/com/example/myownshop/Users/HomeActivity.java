package com.example.myownshop.Users;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myownshop.LoginActivity;
import com.example.myownshop.R;
import com.example.myownshop.databinding.ActivityHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Меню");
        setSupportActionBar(toolbar);

        // кнопочка карзины
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Здесь будет переход в корзину", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //Кнопочка карзины

        // Чтобы открыть баковое меню нам нужен этот код
       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerSlide(View drawerview, float slideOfset) {
                super.onDrawerSlide(drawerview, slideOfset);
            }
        };

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // Чтобы открыть баковое меню нам нужен этот код

        // обработчик кнопок в баковом меню
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // обработчик кнопок в баковом меню

    }

    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


}

