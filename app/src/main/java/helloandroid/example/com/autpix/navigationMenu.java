package helloandroid.example.com.autpix;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class navigationMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fmabm = getFragmentManager();
        FragmentTransaction fmtabm = fmabm.beginTransaction();


        FragmentMenu fragmentMenu = new FragmentMenu();

        fmtabm.replace(R.id.fragment_container,fragmentMenu);
        fmtabm.commit();
    }

boolean mainmenu = false;

    @Override
    public void onBackPressed() {
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        } */

if(mainmenu){
    super.onBackPressed();
}else
        {

            FragmentManager fmabm = getFragmentManager();
            FragmentTransaction fmtabm = fmabm.beginTransaction();

            FragmentMenu fragmentMenu = new FragmentMenu();

            fmtabm.replace(R.id.fragment_container, fragmentMenu);
            fmtabm.commit();
            mainmenu = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
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



        if (id == R.id.aboutme) {
            // Handle the aboutme page action

            FragmentManager fmabm = getFragmentManager();
            FragmentTransaction fmtabm = fmabm.beginTransaction();

            FragmentAboutMe fragmentAboutMe = new FragmentAboutMe();

            fmtabm.replace(R.id.fragment_container,fragmentAboutMe);
            fmtabm.commit();
            mainmenu = false;


        } else if (id == R.id.morning) {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            FragmentMorning fragmentMorning = new FragmentMorning();

            ft.replace(R.id.fragment_container, fragmentMorning);
            ft.commit();
            mainmenu = false;


        } else if (id == R.id.afternoon) {

            FragmentManager fman = getFragmentManager();
            FragmentTransaction fmtan = fman.beginTransaction();

            FragmentAfternoon fragmentAfternoon = new FragmentAfternoon();

            fmtan.replace(R.id.fragment_container,fragmentAfternoon);
            fmtan.commit();
            mainmenu = false;



        } else if (id == R.id.evening) {

            FragmentManager fmen = getFragmentManager();
            FragmentTransaction fmten = fmen.beginTransaction();

            FragmentEvening fragmentEvening = new FragmentEvening();

            fmten.replace(R.id.fragment_container,fragmentEvening);
            fmten.commit();
            mainmenu = false;


        } else if (id == R.id.home) {

            FragmentManager fmh = getFragmentManager();
            FragmentTransaction fmth = fmh.beginTransaction();

            FragmentHome fragmentHome = new FragmentHome();

            fmth.replace(R.id.fragment_container,fragmentHome);
            fmth.commit();
            mainmenu = false;

        } else if (id == R.id.playground) {

            FragmentManager fmp = getFragmentManager();
            FragmentTransaction fmtp = fmp.beginTransaction();

            FragmentPlayGround fragmentPlayGround = new FragmentPlayGround();

            fmtp.replace(R.id.fragment_container,fragmentPlayGround);
            fmtp.commit();
            mainmenu = false;
        } else if (id == R.id.grammer) {

            FragmentManager fmg = getFragmentManager();
            FragmentTransaction fmtg = fmg.beginTransaction();

            FragmentGrammer fragmentGrammer = new FragmentGrammer();

            fmtg.replace(R.id.fragment_container,fragmentGrammer);
            fmtg.commit();
            mainmenu = false;

        } else if (id == R.id.playgame) {

            FragmentManager fmp = getFragmentManager();
            FragmentTransaction fmtpg = fmp.beginTransaction();

            FragmentPlayGame fragmentPlayGame = new FragmentPlayGame();

            fmtpg.replace(R.id.fragment_container,fragmentPlayGame);
            fmtpg.commit();
            mainmenu = false;

        } else if (id == R.id.carerlogin) {

            FragmentManager fmc = getFragmentManager();
            FragmentTransaction fmtcl = fmc.beginTransaction();

            FragmentCarerLogin fragmentCarerLogin = new FragmentCarerLogin();

            fmtcl.replace(R.id.fragment_container,fragmentCarerLogin);
            fmtcl.commit();
            mainmenu = false;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
