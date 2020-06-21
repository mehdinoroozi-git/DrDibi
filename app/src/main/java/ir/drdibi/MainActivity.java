package ir.drdibi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import ir.drdibi.ui.DibikalaFragment;
import ir.drdibi.ui.MainFragment;
import ir.drdibi.ui.ProfileFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ViewCompat.setLayoutDirection(toolbar, ViewCompat.LAYOUT_DIRECTION_RTL);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new MainFragment()).commit();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }});
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ViewCompat.setLayoutDirection(navigationView,ViewCompat.LAYOUT_DIRECTION_RTL);
    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.END)){
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new ProfileFragment()).commit();

                break;
            case R.id.nav_store:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new DibikalaFragment()).commit();

                break;
        }
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}