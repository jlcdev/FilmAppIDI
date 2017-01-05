package com.example.pr_idi.mydatabaseexample.filmdatabase;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.AddFilm;
import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.EditRate;
import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.SearchByTitle;
import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.SearchByActor;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    private FilmData filmData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filmData = new FilmData(this);
        filmData.open();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onFragmentInteraction(0, new Bundle());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search_title) {
            onFragmentInteraction(0, new Bundle());
        } else if (id == R.id.nav_search_actor) {
            onFragmentInteraction(1, new Bundle());
        } else if (id == R.id.nav_film_list) {
            onFragmentInteraction(2, new Bundle());
        } else if (id == R.id.nav_add_film) {
            onFragmentInteraction(3, new Bundle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(int fragCode, Bundle bundle) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        switch(fragCode){
            case SearchByTitle.TAG:
                fragment = SearchByTitle.newInstance(bundle, filmData);
                break;
            case SearchByActor.TAG:
                fragment = SearchByActor.newInstance(bundle, filmData);
                break;
            case 2:
                fragment = SearchByTitle.newInstance(bundle, filmData);
                break;
            case AddFilm.TAG:
                fragment = AddFilm.newInstance(bundle, filmData);
                break;
            case EditRate.TAG:
                fragment = EditRate.newInstance(bundle, filmData);
                break;
        }
        if(fragment == null) {
            fragment = SearchByTitle.newInstance(bundle, filmData);
        }
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        filmData.open();
    }

    @Override
    protected void onStop() {
        super.onStop();
        filmData.close();
    }
}
