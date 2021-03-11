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
import android.widget.Button;

import com.cheetah.setu.account.ProfileFragment;
import com.cheetah.setu.cart.CartActivity;
import com.cheetah.setu.home.Home;
import com.cheetah.setu.home.util.OrderByPrescctivity;
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

    Button check;

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

        navigationView.setNavigationItemSelectedListener(this);

        materialToolbar = findViewById(R.id.material_toobar);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        floatingActionButton = findViewById(R.id.fab);
        check = findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderByPrescctivity.class);
                startActivity(intent);
            }
        });
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

     @SuppressLint("NonConstantResourceId")
     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.n_order_medicine:
                Func.showToast(MainActivity.this, "Order Medicine");
                return true;
            case R.id.n_order_by_prescription:
                Func.showToast(MainActivity.this, "Order By Prescription");
                return true;
            case R.id.n_reorder_medicine:
                Func.showToast(MainActivity.this, "n_reorder_medicine");
                return true;
            case R.id.n_household_supplies:
                Func.showToast(MainActivity.this, "n_household_supplies");
                return true;
            case R.id.n_health_record:
                Func.showToast(MainActivity.this, "n_health_record");
                return true;
            case R.id.n_buy_again:
                Func.showToast(MainActivity.this, "n_buy_again");
                return true;
            case R.id.n_my_lab_booking:
                Func.showToast(MainActivity.this, "n_my_lab_booking");
                return true;
            case R.id.n_order:
                Func.showToast(MainActivity.this, "n_order");
                return true;
            case R.id.n_return:
                Func.showToast(MainActivity.this, "n_return");
                return true;
            case R.id.n_review_products:
                Func.showToast(MainActivity.this, "n_review_products");
                return true;
            case R.id.n_rate_us:
                Func.showToast(MainActivity.this, "n_rate_us");
                return true;
            case R.id.n_request_a_product:
                Func.showToast(MainActivity.this, "n_request_a_product");
                return true;
            case R.id.n_Feedback:
                Func.showToast(MainActivity.this, "n_Feedback");
                return true;
            case R.id.n_share_in:
                Func.showToast(MainActivity.this, "n_share_in");
                return true;
            case R.id.n_offer_products:
                Func.showToast(MainActivity.this, "n_offer_products");
                return true;
            case R.id.n_legal_about:
                Func.showToast(MainActivity.this, "n_legal_about");
                return true;
            case R.id.n_privacy_policy:
                Func.showToast(MainActivity.this, "n_privacy_policy");
                return true;
            case R.id.n_disclaimer:
                Func.showToast(MainActivity.this, "n_disclaimer");
                return true;
            case R.id.n_t_and_c:
                Func.showToast(MainActivity.this, "n_t_and_c");
                return true;
            case R.id.n_about_us:
                Func.showToast(MainActivity.this, "n_about_us");
                return true;
            case R.id.n_delivery:
                Func.showToast(MainActivity.this, "n_delivery");
                return true;
            case R.id.n_faq:
                Func.showToast(MainActivity.this, "n_faq");
                return true;
            case R.id.n_return_cancellation:
                Func.showToast(MainActivity.this, "n_return_cancellation");
                return true;
        }
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