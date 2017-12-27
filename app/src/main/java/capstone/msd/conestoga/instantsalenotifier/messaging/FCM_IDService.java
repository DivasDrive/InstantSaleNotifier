package capstone.msd.conestoga.instantsalenotifier.messaging;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by CatherineChoi on 12/11/2017.
 */

public class FCM_IDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    public static final String TOKEN_BROADCAST="FCM_TOKEN_BROADCAST";

    //Access the device registration token
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        this.getApplication().sendBroadcast(new Intent(TOKEN_BROADCAST));
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
      //  sendRegistrationToServer(refreshedToken);
        storeToken(refreshedToken);
    }


    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Save token in the local device
     * * @param token
     */
    private void storeToken(String token){
        SharedPrepManager.getInstance(this.getApplicationContext()).storeToken(token);
    }
}
