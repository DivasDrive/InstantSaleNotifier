package capstone.msd.conestoga.instantsalenotifier.location;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
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

import java.io.IOException;
import java.util.List;

import capstone.msd.conestoga.instantsalenotifier.MainActivity;
import capstone.msd.conestoga.instantsalenotifier.R;
import capstone.msd.conestoga.instantsalenotifier.location.GeofenceRegistrationService;
import capstone.msd.conestoga.instantsalenotifier.utils.Constants;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeoFencingMapFragment extends Fragment implements OnMapReadyCallback , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener , android.location.LocationListener {

    private static final String TAG = "GeoFencing";
    private static final int REQUEST_LOCATION_PERMISSION_CODE = 101;

    private GoogleMap googleMap;

    private GeofencingRequest geofencingRequest;
    private GoogleApiClient googleApiClient;
    private GeofencingClient mGeofencingClient;

    private boolean isMonitoring = false;
    private SupportMapFragment mapFragment;
    private MarkerOptions markerOptions;

    private Marker currentLocationMarker;
    private PendingIntent pendingIntent;
    private  double latitude;
    private double longitude;

    private TextView txtLatitude;
    private TextView txtLongtitude;
    private TextView txtStatus;

    public GeoFencingMapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_map, container, false);

        txtLatitude =(TextView) view.findViewById(R.id.txtLatitude);
        txtLongtitude = (TextView) view.findViewById(R.id.txtLongtitude);
        txtStatus = (TextView)view.findViewById(R.id.txtStatus);

        setHasOptionsMenu(true);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
         mapFragment.getMapAsync(this);

        // Connect to Google Play Service
        googleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();

        // Check and Request Permission
        if (ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION_CODE);
        }

        LocationManager locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }


    private void startLocationMonitor() {

        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(Constants.GEOFENCING_INTERVAL)
                .setFastestInterval(Constants.GEOFENCING_FASTEST_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    txtLatitude.setText(String.valueOf(location.getLatitude()));
                    txtLongtitude.setText(String.valueOf(location.getLongitude()));

                    if (currentLocationMarker != null) {
                        currentLocationMarker.remove();
                    }
                    markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(location.getLatitude(), location.getLongitude()));
                    markerOptions.title("Current Location");
                    currentLocationMarker = googleMap.addMarker(markerOptions);
                }
            });
        } catch (SecurityException e) {
            Log.d(TAG, e.getMessage());
        }

    }

    private void startGeofencing() {

        mGeofencingClient = LocationServices.getGeofencingClient(getContext());
        pendingIntent = getGeofencePendingIntent();
        geofencingRequest = getGeofencingRequest();

        if (!googleApiClient.isConnected()) {
            Log.d(TAG, "Google API client not connected");
        } else {
            try {
                LocationServices.GeofencingApi.addGeofences( googleApiClient,geofencingRequest, pendingIntent).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        txtStatus.setText("googleApiClient.isConnected() :" + status.isSuccess());

                        if (status.isSuccess()) {
                            Log.d(TAG, "Successfully Geofencing Connected");

                        } else {
                            Log.d(TAG, "Failed to add Geofencing " + status.getStatus());
                        }
                    }
                });
            } catch (SecurityException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        isMonitoring = true;

        getActivity().invalidateOptionsMenu();
    }

    /**
     * Create GeoFence that uses Location API
     */
    @NonNull
    private Geofence getGeofence() {
        LatLng latLng = new LatLng(Constants.GEOFENCE_HOME_LANTITUDE ,Constants.GEOFENCE_HOME_LONGTITUDE);

        return new Geofence.Builder()
                .setRequestId(Constants.GEOFENCE_ID_HOME)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setCircularRegion(latLng.latitude, latLng.longitude, Constants.GEOFENCE_RADIUS_IN_METERS)
                .setNotificationResponsiveness(1000)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();
    }

    private PendingIntent getGeofencePendingIntent() {
        if (pendingIntent != null) {
            return pendingIntent;
        }
        Intent intent = new Intent(getContext(), GeofenceRegistrationService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        pendingIntent = PendingIntent.getService(getContext(), 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    private void stopGeoFencing() {
        pendingIntent = getGeofencePendingIntent();
        LocationServices.GeofencingApi.removeGeofences(googleApiClient, pendingIntent)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess())
                            Log.d(TAG, "Stop geofencing");
                        else
                            Log.d(TAG, "Not stop geofencing");
                    }
                });
        isMonitoring = false;

         getActivity().invalidateOptionsMenu();

    }
    /*
     * Geofence triggers
     * GEOFENCE_TRANSITION_ENTER:Transition triggers when a device enters a geofence
     * GEOFENCE_TRANSITION_EXIT:Transition triggers when a device exits a geofence.
     * INITIAL_TRIGGER_ENTER: Tells Location services that  should be triggered if the the device is already inside the geofence.
     * INITIAL_TRIGGER_DWELL:Triggers events only when the user stops for a defined duration within a geofence. This approach can help reduce “alert spam” resulting from large numbers notifications.
     */
    private GeofencingRequest getGeofencingRequest() {

        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER); //Transition triggers when a device enters a geofence
        builder.addGeofence(getGeofence()) ;

        return builder.build();
    }

    @Override
    public void onResume() {
        super.onResume();
        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.getContext());
        if (response != ConnectionResult.SUCCESS) {
            Log.d(TAG, "Google Play Service Not Available");
            GoogleApiAvailability.getInstance().getErrorDialog((MainActivity)this.getActivity(), response, 1).show();
        } else {
            Log.d(TAG, "Google play service available");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.reconnect();
    }
    @Override
    public void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }
    private void stopLocationUpdates() {
        //LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,  this);
        LocationServices.getFusedLocationProviderClient(getContext()).removeLocationUpdates(getGeofencePendingIntent());
    }

     @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater) {
         super.onCreateOptionsMenu(menu, inflater);

        this.getActivity().getMenuInflater().inflate(R.menu.manu_map_activity, menu);
        if (isMonitoring) {
            menu.findItem(R.id.action_start_monitor).setVisible(false);
            menu.findItem(R.id.action_stop_monitor).setVisible(true);
        } else {
            menu.findItem(R.id.action_start_monitor).setVisible(true);
            menu.findItem(R.id.action_stop_monitor).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_start_monitor:
                startGeofencing();
                break;
            case R.id.action_stop_monitor:
                stopGeoFencing();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        this.googleMap = googleMap;

        Log.d(TAG, "onMapReady latitude :" + latitude +" ,longitude: "+longitude);
        LatLng latLng = new LatLng(latitude,longitude);
        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);
            if(addressList.size() >0) {
                String str = addressList.get(0).getLocality() + ",";
                str += addressList.get(0).getCountryName();
                googleMap.addMarker(new MarkerOptions().position(latLng).title(str));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f));
            }else{
                googleMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Circle circle = googleMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(Constants.GEOFENCE_RADIUS_IN_METERS)
                .strokeColor(Color.RED)
                .strokeWidth(4f));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "Google Api Client Connected");
        txtStatus.setText("Google Api Client Connected");

        isMonitoring = true;
        startGeofencing();
        startLocationMonitor();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Google Connection Suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        isMonitoring = false;
        Log.e(TAG, "Connection Failed:" + connectionResult.getErrorMessage());
    }

    @Override
    public void onLocationChanged(Location location) {
        boolean isChanged = false;
        if(location.getLatitude() !=latitude && location.getLongitude()!=longitude ){
            isChanged = true;
            latitude =  location.getLatitude()  ;
            longitude = location.getLongitude();
            mapFragment.getMapAsync(this);
        }
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
}
