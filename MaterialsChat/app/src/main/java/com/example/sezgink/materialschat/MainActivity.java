package com.example.sezgink.materialschat;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SocialFragment.OnFragmentInteractionListener,GreatFragment.OnFragmentInteractionListener{

    private android.support.v4.app.FragmentManager fragmentManager;
    private Toolbar toolbar;
    private CollapsingToolbarLayout colToolbar;

    private boolean isFABOpen = false;
    FloatingActionButton fab;
    FloatingActionButton fab2;
    FloatingActionButton fab3;

    private static final long MOVE_DEFAULT_TIME = 1000;
    private static final long FADE_DEFAULT_TIME = 300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        colToolbar = (CollapsingToolbarLayout) findViewById(R.id.colToolbar);
        //setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

        defineFABs(); // Define and assign views and functions to FABs

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener); // Assign listener

        fragmentManager = getSupportFragmentManager(); // Create fragment manager to get instant fragment
        fragmentManager.beginTransaction().add(R.id.frame_container,new MainActivityFragment(),"frameContainer").commit(); //Assign tag to container
        //loadFragment(new MainActivityFragment());

    }

    //Create listener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.Fragment fragment;
            switch (item.getItemId()) {
                case R.id.item1: //If item 1 selected
                    //toolbar.setTitle("Shop");
                    colToolbar.setTitle("Main side");
                    defineFABs();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fab.setImageDrawable(getDrawable(android.R.drawable.ic_menu_add)); // If version greater than fragment assign different icon
                    }

                    fragment = new MainActivityFragment();
                    loadFragment(fragment); //Call MainActivityFragment
                    return true;
                case R.id.item2:
                    //toolbar.setTitle("My Gifts");
                    colToolbar.setTitle("Social Side"); // Change title

                    defineFAB0(); //Change function of fab
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fab.setImageDrawable(getDrawable(android.R.drawable.ic_delete));
                    }


                    fragment = new SocialFragment();

                    loadFragment(fragment);
                    return true;
                case R.id.item3:

                    toolbar.setTitle("Cart");
                    //colToolbar.setTitle("Great Frag");
                    defineFABs();
                    fab.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fab.setImageDrawable(getDrawable(android.R.drawable.btn_star));

                    }

                    fragment = new GreatFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    //Load fragment in paramter to container
    private void loadFragment(android.support.v4.app.Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        android.support.transition.Fade exitFade = new android.support.transition.Fade(); // Create new support fade
        exitFade.setDuration(FADE_DEFAULT_TIME); //Assign fade time

        //android.support.v4.app.Fragment previousFragment = fragmentManager.findFragmentById(R.id.frame_container);
        android.support.v4.app.Fragment previousFragment = fragmentManager.findFragmentByTag("frameContainer"); // Get instant fragment

        //previousFragment.setExitTransition(exitFade);

        Fade enterFade = new Fade();
        enterFade.setDuration(FADE_DEFAULT_TIME);
        //fragment.setEnterTransition(enterFade);


        //Assign transitions
        previousFragment.setExitTransition(exitFade);
        fragment.setEnterTransition(enterFade);



        //Replace new fragment
        transaction.replace(R.id.frame_container, fragment);

        transaction.addToBackStack(null);
        transaction.commit();
    }

    void defineFAB0() {
        //Close FAB menu and change function of button
        closeFABMenu();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
                Snackbar snack = Snackbar.make(findViewById(R.id.corLayout),
                        "Your message", Snackbar.LENGTH_LONG);
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                        snack.getView().getLayoutParams();
                BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
                params.setMargins(20, 20, 20, bottomNavigationView.getHeight());
                snack.getView().setLayoutParams(params);
                snack.show();
            }
        });
    }
    void defineFABs() {
        //Define all buttons and assign functions
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFABOpen) {
                    closeFABMenu();
                } else
                showFABMenu();
            }
        });

        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
                Snackbar snack = Snackbar.make(findViewById(R.id.corLayout),
                        "Your message", Snackbar.LENGTH_LONG);
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                        snack.getView().getLayoutParams();
                BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
                params.setMargins(20, 20, 20, bottomNavigationView.getHeight());
                snack.getView().setLayoutParams(params);
                snack.show();
            }
        });

        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
                Snackbar snack = Snackbar.make(findViewById(R.id.corLayout),
                        "Your message", Snackbar.LENGTH_LONG);
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                        snack.getView().getLayoutParams();
                BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
                params.setMargins(20, 20, 20, bottomNavigationView.getHeight());
                snack.getView().setLayoutParams(params);
                snack.show();
            }
        });


    }
    //Show FAB menu with animate
    void showFABMenu(){
        isFABOpen=true;
        fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_105));

    }
    //Hide FAB menu with animation
    void closeFABMenu(){
        isFABOpen=false;
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
