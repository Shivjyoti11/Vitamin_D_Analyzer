package com.example.vitamindanalyser;

import android.R.id;
import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class nd extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Object v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nd);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {



            private View.OnClickListener getOnClickListener() {
                return this;
            }

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        init();
    }

    private void init(){
        NavController navController=Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
        navigationView.setNavigationItemSelectedListener(this);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    // @Override
    //public boolean onSupportNavigateUp() {
    //  NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    // return NavigationUI.navigateUp(navController, mAppBarConfiguration)
    //        || super.onSupportNavigateUp();



    //}

    @Override

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){



            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else {
                    return false;

                }

            }

            default:
                return super.onOptionsItemSelected(menuItem);









        }


    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem  menuItem) {

        /// MenuItem menuItem =null;
        switch(menuItem.getItemId()){

            case R.id.nav_gallery:{
                Navigation.findNavController(this,R.id.nav_host_fragment ).navigate(R.id.galleryScreen);
                break;
            }
            case R.id.nav_home:{

                Navigation.findNavController(this,R.id.nav_host_fragment ).navigate(R.id.homeScreen);

                break;

            }
            case R.id.nav_slideshow:{

                Navigation.findNavController(this,R.id.nav_host_fragment ).navigate(R.id.slideshowScreen);

                break;
            }
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp(){

        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment),drawerLayout);


    }

}
