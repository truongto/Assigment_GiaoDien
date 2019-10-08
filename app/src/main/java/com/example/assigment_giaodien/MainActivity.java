package com.example.assigment_giaodien;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
//vao app hien thi luon thufragment
        ThuFragment thuFragment = new ThuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frament, thuFragment).commit();
        //khai bao
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigation);
//ba vach mo menu
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

//        navigationView.setNavigationItemSelectedListener(this);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.khoanthu:
//                        ThuFragment thuFragment = new ThuFragment();
//                        fragmentManager.beginTransaction().add(R.id.frament, thuFragment).commit();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frament, new ThuFragment())
                                .commit();
                        break;
                    case R.id.khoanchi:
//                        KhoancChiFragment khoancChiFragment = new KhoancChiFragment();
//                        fragmentManager.beginTransaction().add(R.id.frament, khoancChiFragment).commit();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frament, new ChiFragment())
                                .commit();
                        break;
                    case R.id.thongke:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frament, new ThongKeFragment()).commit();
                        break;

                    case R.id.gioithieu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frament, new GioiThieuFragment()).commit();
                        break;
                    case R.id.thoat:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Bạn Có Chắc Chắn Muốn Thoát Không").setTitle("Thoát Ứng Dụng");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onBackPressed();
//                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                startActivity(intent);
//
//                                // Tao su kien ket thuc app
//                                Intent startMain = new Intent(Intent.ACTION_MAIN);
//                                startMain.addCategory(Intent.CATEGORY_HOME);
//                                startActivity(startMain);
//                                finish();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });

    }


}


