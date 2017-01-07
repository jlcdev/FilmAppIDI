package com.example.pr_idi.mydatabaseexample.filmdatabase;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.AddFilm;
import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.EditRate;
import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.SearchByTitle;
import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.SearchByActor;
import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.ShowFilms;
import com.example.pr_idi.mydatabaseexample.filmdatabase.interfaces.OnFragmentInteractionListener;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.FilmData;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    private FilmData filmData;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;

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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onFragmentInteraction(SearchByTitle.TAG, new Bundle());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
        if (fragmentManager.getBackStackEntryCount() > 0) fragmentManager.popBackStack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.action_settings) return true;
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.nav_search_title:
                onFragmentInteraction(SearchByTitle.TAG, new Bundle());
                break;
            case R.id.nav_search_actor:
                onFragmentInteraction(SearchByActor.TAG, new Bundle());
                break;
            case R.id.nav_film_list:
                onFragmentInteraction(ShowFilms.TAG, new Bundle());
                break;
            case R.id.nav_add_film:
                onFragmentInteraction(AddFilm.TAG, new Bundle());
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String TAG, Bundle bundle) {
        fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment;
        switch(TAG){
            case SearchByActor.TAG:
                navigationView.getMenu().getItem(1).setChecked(true);
                fragment = SearchByActor.newInstance(bundle, filmData);
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(SearchByActor.TAG);
                break;
            case ShowFilms.TAG:
                navigationView.getMenu().getItem(2).setChecked(true);
                fragment = ShowFilms.newInstance(bundle, filmData);
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(ShowFilms.TAG);
                break;
            case AddFilm.TAG:
                navigationView.getMenu().getItem(3).setChecked(true);
                fragment = AddFilm.newInstance(bundle, filmData);
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(AddFilm.TAG);
                break;
            case EditRate.TAG:
                fragment = EditRate.newInstance(bundle, filmData);
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(EditRate.TAG);
                break;
            default:
                navigationView.getMenu().getItem(0).setChecked(true);
                fragment = SearchByTitle.newInstance(bundle, filmData);
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(SearchByTitle.TAG);
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        filmData.open();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        filmData.close();
    }
}
