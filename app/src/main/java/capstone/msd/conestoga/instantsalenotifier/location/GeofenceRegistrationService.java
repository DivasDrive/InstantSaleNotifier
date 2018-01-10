package capstone.msd.conestoga.instantsalenotifier.location;

/**
 * Created by CatherineChoi on 12/11/2017.
 */

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

import capstone.msd.conestoga.instantsalenotifier.R;
import capstone.msd.conestoga.instantsalenotifier.messaging.MessagingActivity;
import capstone.msd.conestoga.instantsalenotifier.messaging.SaleNotificationManager;
import capstone.msd.conestoga.instantsalenotifier.utils.Constants;

import static java.security.AccessController.getContext;

public class GeofenceRegistrationService extends IntentService {

    private static final String TAG = "GeoIntentService";

    public GeofenceRegistrationService() {
        super(TAG);
    }

   // @Override
    protected void onHandleIntent__(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent.hasError()) {
            Log.d(TAG, "GeofencingEvent error " + geofencingEvent.getErrorCode());
        } else {
            int transaction = geofencingEvent.getGeofenceTransition();
            List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            if (transaction == Geofence.GEOFENCE_TRANSITION_ENTER && geofence.getRequestId().equals(Constants.GEOFENCE_ID_HOME)) {
                Toast.makeText( getApplicationContext(),  "You are inside Home", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),  "You areoutside Home", Toast.LENGTH_LONG).show();
            }
        }
    }
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(getApplicationContext(), "GeofenceResistration" , Toast.LENGTH_SHORT).show();
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        Toast.makeText(getApplicationContext(), "geofencingEvent.hasError()" , Toast.LENGTH_SHORT).show();
        if (geofencingEvent.hasError()) {
            String errorMessage = getErrorString(this,geofencingEvent.getErrorCode());
            Log.e(TAG, errorMessage);
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        Toast.makeText(getApplicationContext(), "geofenceTransition" +geofenceTransition , Toast.LENGTH_SHORT).show();
        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            // Get the transition details as a String.
          /*  String geofenceTransitionDetails = getGeofenceTransitionDetails(
                    this,
                    geofenceTransition,
                    triggeringGeofences
            );*/
            String geofenceTransitionDetails="";
           if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER)
              geofenceTransitionDetails ="Welcome Home";
            else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT)
               geofenceTransitionDetails ="Leaving Home";

            // Send notification and log the transition details.
            SaleNotificationManager saleNotificationManager = new SaleNotificationManager(getApplicationContext());
            saleNotificationManager.showNotification("GeoFencing",geofenceTransitionDetails,new Intent(getApplicationContext(), MessagingActivity.class));
            Log.i(TAG, geofenceTransitionDetails);
        } else {
            // Log the error.
            Log.e(TAG, "Invalid Geofence Transition Type" );
        }
    }
    /**
     * Returns the error string for a geofencing error code.
     */
    public static String getErrorString( Context context, int errorCode) {
        Resources mResources = context.getResources();


        switch (errorCode) {
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return mResources.getString(R.string.geofence_not_available);
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return mResources.getString(R.string.geofence_too_many_geofences);
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return mResources.getString(R.string.geofence_too_many_pending_intents);
            default:
                return mResources.getString(R.string.unknown_geofence_error);
        }
    }

}