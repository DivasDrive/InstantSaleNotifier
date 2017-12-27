package capstone.msd.conestoga.instantsalenotifier;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import capstone.msd.conestoga.instantsalenotifier.database.Constants;
import capstone.msd.conestoga.instantsalenotifier.database.DataBaseConnection;
import capstone.msd.conestoga.instantsalenotifier.database.RequestHandler;
import capstone.msd.conestoga.instantsalenotifier.location.PermissionUtils;
import capstone.msd.conestoga.instantsalenotifier.model.Store;

import static android.view.View.GONE;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstantSaleMapFragment extends Fragment implements OnMapReadyCallback, LocationListener {
    private String TAG = InstantSaleMapFragment.class.getSimpleName();
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient = null;
    private LocationManager locationManager = null;
    private boolean isPermissionGranted = false;
    private DataBaseConnection connectionUtil = null;
    private Connection conn =null;

    private ProgressBar progressBar;
    private LatLng latLng;
    private double current_latitude;
    private double current_longitude;

    public InstantSaleMapFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_map, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        PermissionUtils permissionUtils=new PermissionUtils(this.getContext());
        ArrayList<String> permissions=new ArrayList<>();
        permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionUtils.check_permission(permissions,"Need GPS permission for getting your location",1);

        locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //  return TODO;
        }
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in UPtown Shopping Center and move the camera
        LatLng waterlooTownSquare = new LatLng(43.464146,-80.523346);
        mMap.addMarker(new MarkerOptions().position(waterlooTownSquare).title("Waterloo Town Square"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(waterlooTownSquare,10.2f));

        //getDataFromDatabase();
        requestStoreInfomation();
    }


    @Override
    public void onLocationChanged(Location location) {
/*
        current_latitude = location.getLatitude();
        current_longitude = location.getLongitude();

        latLng= new LatLng(current_latitude, current_latitude);

        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(current_latitude,current_longitude,1);
            String str = addressList.get(0).getLocality()+",";
            str+=  addressList.get(0).getCountryName();
            mMap.addMarker(new MarkerOptions().position(latLng).title(str));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.5f));
            Toast.makeText(this.getContext(), String.valueOf(current_latitude), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } */

       // Toast.makeText(this.getContext(), "onLocationChanged", Toast.LENGTH_LONG).show();
    }
    private void addMarker(LatLng latlng, final String title){

        mMap.addMarker(new MarkerOptions().position(latLng).title(title));
       // Toast.makeText(this.getContext(), title, Toast.LENGTH_LONG).show();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16.5f));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(getApplicationContext(),marker.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    private void requestStoreInfomation(){
        PerformNetworkRequest request = new PerformNetworkRequest(Constants.URL_GET_STORES, null, CODE_GET_REQUEST);
        request.execute();
    }

    private void getDataFromDatabase(){

        Log.d (TAG, "getDataFromDatabase");
        connectionUtil = new DataBaseConnection();
        conn = connectionUtil.getConnection();
        String selectSQL ="SELECT NAME FROM STORES ";
        PreparedStatement pstmt =null;

        try {
            if (conn != null) {
                pstmt = conn.prepareStatement(selectSQL);
                ResultSet  rs =  pstmt.executeQuery();
                while(rs.next()){
                    Log.d(TAG, rs.getString(1));
                }
            }else {
                Toast.makeText(getApplicationContext(), "Connection Fail",Toast.LENGTH_LONG).show();
            }
        }catch(SQLException e){
            Log.e(TAG, e.getMessage());
        }finally {
            if(pstmt != null)
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    Log.e(TAG, e.getMessage() );
                }
            connectionUtil.close();
        }
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
            latLng = new LatLng(obj.getDouble("lantitude"), obj.getDouble("longtitude"));
            addMarker(latLng,obj.getString("name"));
        }
       /* for (int i = 0; i < storeArrayList.size(); i++) {
            Log.d(TAG, storeArrayList.get(i).getName());
            Log.d(TAG, String.valueOf(storeArrayList.get(i).getLantitude()));
        }*/

        //creating the adapter and setting it to the listview
        // HeroAdapter adapter = new HeroAdapter(heroList);
        // listView.setAdapter(adapter);
    }

}
