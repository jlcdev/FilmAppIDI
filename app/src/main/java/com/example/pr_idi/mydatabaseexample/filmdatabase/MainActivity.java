package com.example.pr_idi.mydatabaseexample.filmdatabase;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

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
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filmData = new FilmData(this);
        filmData.open();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerStateChanged(int newState) {
                if(newState == DrawerLayout.STATE_SETTLING){
                    hideSoftKeyBoard();
                    super.onDrawerStateChanged(newState);
                }
            }
        };

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onFragmentInteraction(SearchByTitle.TAG, new Bundle());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void hideSoftKeyBoard(){
        InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        int backCount = fragmentManager.getBackStackEntryCount();

        if(backCount > 1) {
            fragmentManager.popBackStack();
        } else {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Sortir?");
            adb.setMessage("Estàs segur de voler sortir de l'aplicació");
            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    finish();
                }
            });
            adb.show();
        }
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
    public void onFragmentInteraction(String TAG, Bundle bundle){
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
