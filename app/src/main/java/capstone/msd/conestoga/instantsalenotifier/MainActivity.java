package capstone.msd.conestoga.instantsalenotifier;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import capstone.msd.conestoga.instantsalenotifier.database.Constants;
import capstone.msd.conestoga.instantsalenotifier.database.RequestHandler;
import capstone.msd.conestoga.instantsalenotifier.location.PermissionUtils;
import capstone.msd.conestoga.instantsalenotifier.messaging.MessagingActivity;
import capstone.msd.conestoga.instantsalenotifier.model.Store;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,PermissionUtils.PermissionResultCallback{
    private String TAG =MainActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Catherine", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        if (id == R.id.nav_home) {
                    AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("Do you want to get a NullPointerException, because that's how " +
                            "you get a NullPointerException :D")
                    .setPositiveButton("Why not?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            throw new NullPointerException("Test issue in Instabug Sample app");
                        }
                    }).show();

        } else if (id == R.id.nav_messaging) {
           startActivity(new Intent(this, MessagingActivity.class));
        } else if (id == R.id.nav_category) {
            //HashMap<String, String> params = new HashMap<>();

            //Calling the retrieve store API
           // PerformNetworkRequest request = new PerformNetworkRequest(Constants.URL_GET_STORES, null, CODE_GET_REQUEST);
           // request.execute();

        } else if (id == R.id.nav_coupons) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
        }
        else if (id == R.id.nav_map) {
            //startActivity(new Intent(this, MapsActivity.class));
           // SaleMapFragment mapFragment = new SaleMapFragment();
           // MapFragment mapFragment = new MapFragment();
            InstantSaleMapFragment mapFragment = new InstantSaleMapFragment();
            FragmentManager mgrFragment = this.getSupportFragmentManager();
            mgrFragment.beginTransaction().replace(R.id.mainLayout, mapFragment).commit();
        }else if (id== R.id.nav_geoFence){
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

    //inner class to perform network request extending an AsyncTask
    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        //the url where we need to send the request
        String url;

        //the parameters
        HashMap<String, String> params;

        //the request code to define whether it is a GET or POST
        int requestCode;

        //constructor to initialize values
        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        //when the task started displaying a progressbar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }


        //this method will give the response from the request
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    //refreshing the herolist after every operation
                    //so we get an updated list
                    //we will create this method right now it is commented
                    //because we haven't created it yet
                    //refreshHeroList(object.getJSONArray("heroes"));
                    refreshStoreList(object.getJSONArray("stores"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }
    private void refreshStoreList(JSONArray stores) throws JSONException {
        //clearing previous heroes
       // heroList.clear();
        ArrayList<Store> storeArrayList = new ArrayList<>();

        //traversing through all the items in the json array
        //the json we got from the response
        for (int i = 0; i < stores.length(); i++) {
            //getting each hero object
            JSONObject obj = stores.getJSONObject(i);

            //adding the hero to the list
            storeArrayList.add(new Store(
                    obj.getInt("id"),
                    obj.getString("name"),
                    obj.getString("address"),
                    obj.getDouble("lantitude"),
                    obj.getDouble("longtitude")
            ));
        }
        for (int i = 0; i < storeArrayList.size(); i++) {
            Log.d(TAG, storeArrayList.get(i).getName());
        }
        //creating the adapter and setting it to the listview
       // HeroAdapter adapter = new HeroAdapter(heroList);
       // listView.setAdapter(adapter);
    }
}
