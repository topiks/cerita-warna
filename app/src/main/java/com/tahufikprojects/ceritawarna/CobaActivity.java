package com.tahufikprojects.ceritawarna;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.core.Tag;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tahufikprojects.ceritawarna.cobacari.CobaCariMainActivity;
import com.tahufikprojects.ceritawarna.ui.artikel.ArtikelFragment;
import com.tahufikprojects.ceritawarna.ui.deteksi.DeteksiFragment;
import com.tahufikprojects.ceritawarna.ui.forum.ForumFragment;
import com.tahufikprojects.ceritawarna.ui.gallery.GalleryFragment;
import com.tahufikprojects.ceritawarna.ui.home.HomeFragment;
import com.tahufikprojects.ceritawarna.ui.jurusan.JurusanFragment;
import com.tahufikprojects.ceritawarna.ui.tes.TesFragment;
import com.tahufikprojects.ceritawarna.utils.Preferences;

public class CobaActivity extends AppCompatActivity implements HomeFragment.OnCallbackReceived, ArtikelFragment.OnCallbackReceived, JurusanFragment.OnCallbackReceived, TesFragment.OnCallbackReceived, ForumFragment.OnCallbackReceived, DeteksiFragment.OnCallbackReceived, GalleryFragment.OnCallbackReceived {

    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;
    Bundle bundle;
    String username;
    Preferences preferences;
    String dataStr;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    String posisiFragment;

    public void Update(String data) {
        posisiFragment = data;
        Log.d("demo", posisiFragment);

        Toolbar toolbarku = findViewById(R.id.toolbar);

        if(posisiFragment.equals("home") || posisiFragment.equals("tes"))
            toolbarku.setBackgroundColor(Color.rgb(221,14,122));
        else if(posisiFragment.equals("artikel"))
            toolbarku.setBackgroundColor(Color.rgb(90, 93, 138));
        else if(posisiFragment.equals("jurusan"))
            toolbarku.setBackgroundColor(Color.rgb(80, 128, 204));
        else if(posisiFragment.equals("forum"))
            toolbarku.setBackgroundColor(Color.rgb(251, 195, 118));
        else if(posisiFragment.equals("deteksi") || posisiFragment.equals("gallery"))
            toolbarku.setBackgroundColor(Color.rgb(119, 210, 168));
        // Write your logic here.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Toast.makeText(CobaActivity.this, posisiFragment, Toast.LENGTH_SHORT).show();

//        toolbar.setBackgroundColor(Color.rgb(235,233,233));

        preferences = new Preferences(this);

        Intent intent = getIntent();
        bundle = intent.getExtras();
        username = (String) bundle.get("USERNAME");
        dataStr = username;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.coba, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                preferences.setValues("onboarding", "");
                preferences.setValues("status", "");
                preferences.setValues("nama", "");
                preferences.setValues("email", "");
                preferences.setValues("username", "");
                preferences.setValues("score", "");
                finishAffinity();

                Intent intent = new Intent(CobaActivity.this, OnBoardingActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public String getMyData()
    {
        return dataStr;
    }


}