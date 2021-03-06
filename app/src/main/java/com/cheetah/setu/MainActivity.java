package com.cheetah.setu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.cheetah.setu.account.ProfileFragment;
import com.cheetah.setu.cart.CartActivity;
import com.cheetah.setu.home.Home;
import com.cheetah.setu.main.Func;
import com.cheetah.setu.notify.NotifyActivity;
import com.cheetah.setu.order.OrderFragment;
import com.cheetah.setu.service.ServiceFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    MaterialToolbar materialToolbar;
    BottomNavigationView bottomNavigationView;

    FloatingActionButton floatingActionButton;

    int CALLREQUEST = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Home()).commit();
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        materialToolbar = findViewById(R.id.material_toobar);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.call:
                        doCall();
                        //Func.showToast(MainActivity.this, "Call");
                        return true;
                    case R.id.notification:
                        gotoNotification();
                        //Func.showToast(MainActivity.this, "Notification");
                        return true;
                    case R.id.cart:
                        gotoCart();
                        //Func.showToast(MainActivity.this, "Cart");
                        return true;

                }
                return false;
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Home()).commit();
                        //Func.showToast(MainActivity.this, "Home");
                        return true;
                    case R.id.you:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ProfileFragment()).commit();
                        //Func.showToast(MainActivity.this, "You");
                        return true;
                    case R.id.order:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new OrderFragment()).commit();
                        //Func.showToast(MainActivity.this, "Order");
                        return true;
                    case R.id.service:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ServiceFragment()).commit();
                        //Func.showToast(MainActivity.this, "Services");
                        return true;
                }
                return false;
            }
        });

    }

    private void doCall() {
        int checkPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (checkPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALLREQUEST);
        } else {
            String uri = "tel:"+"+g";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        }

    }

    private void gotoCart() {
        startActivity(new Intent(this, CartActivity.class));
    }

    private void gotoNotification() {
        startActivity(new Intent(this, NotifyActivity.class));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 12: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    String uri = "tel:"+"Num";
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                } else {

                }
                return;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    } else {
                        super.onBackPressed();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {

         return false;
     }

    @Override
    public void onClick(View view) {


    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_appbar, menu);
        return true;
    }*/
}