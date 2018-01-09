package capstone.msd.conestoga.instantsalenotifier;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;

import capstone.msd.conestoga.instantsalenotifier.category.CategoryFragment;
import capstone.msd.conestoga.instantsalenotifier.sales.StoreSalesFragment;
import capstone.msd.conestoga.instantsalenotifier.location.PermissionUtils;
import capstone.msd.conestoga.instantsalenotifier.uptown.UptownIntroFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PermissionUtils.PermissionResultCallback, BaseFragment.OnFragmentInteractionListener {
    private String TAG = MainActivity.class.getSimpleName();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Google Map
        InstantSaleMapFragment mapFragment = new InstantSaleMapFragment();
        FragmentManager mgrFragment = this.getSupportFragmentManager();
        mgrFragment.beginTransaction().replace(R.id.mainLayout, mapFragment).commit();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Catherine to to this : Check for Google Play services : onCreate() , onResume()
        //GoogleApiAvailability.makeGooglePlayServicesAvailable()
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

        if (id == R.id.nav_uptownwaterloo) {
            UptownIntroFragment uptownIntroFragment = new UptownIntroFragment();
            FragmentManager mgrFragment = this.getSupportFragmentManager();
            mgrFragment.beginTransaction().replace(R.id.mainLayout, uptownIntroFragment).commit();
        }  else if (id == R.id.nav_map) {
            InstantSaleMapFragment mapFragment = new InstantSaleMapFragment();
            FragmentManager mgrFragment = this.getSupportFragmentManager();
            mgrFragment.beginTransaction().replace(R.id.mainLayout, mapFragment).commit();
        } else if (id == R.id.nav_sales) {
            StoreSalesFragment storeSalesFragment = new StoreSalesFragment();
            FragmentManager mgrFragment = this.getSupportFragmentManager();
            mgrFragment.beginTransaction().replace(R.id.mainLayout, storeSalesFragment).commit();
        } else if (id == R.id.nav_category) {
            CategoryFragment categoryFragment = new CategoryFragment();
            FragmentManager mgrFragment = this.getSupportFragmentManager();
            mgrFragment.beginTransaction().replace(R.id.mainLayout, categoryFragment).commit();
        }  else if (id == R.id.nav_messaging) {
            //startActivity(new Intent(this, MessagingActivity.class));
            SendMessagingFragment messagingFragment = new SendMessagingFragment();
            FragmentManager mgrFragment = this.getSupportFragmentManager();
            mgrFragment.beginTransaction().replace(R.id.mainLayout, messagingFragment).commit();

        } else if (id == R.id.nav_settings) {
            //startActivity(new Intent(this, MessagingActivity.class));
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager mgrFragment = this.getSupportFragmentManager();
            mgrFragment.beginTransaction().replace(R.id.mainLayout, settingsFragment).commit();

        } else if (id == R.id.nav_geoFence) {
            GeoFencingSalesMapFragment geoFencingFragment = new GeoFencingSalesMapFragment();
            FragmentManager mgrFragment = this.getSupportFragmentManager();
            mgrFragment.beginTransaction().replace(R.id.mainLayout, geoFencingFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean PermissionGranted(int request_code) {
        return true;
    }

    @Override
    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {

    }

    @Override
    public boolean PermissionDenied(int request_code) {
        return false;
    }

    @Override
    public void NeverAskAgain(int request_code) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
