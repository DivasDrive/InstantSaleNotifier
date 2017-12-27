package capstone.msd.conestoga.instantsalenotifier.messaging;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by CatherineChoi on 12/12/2017.
 */

public class FCM_MessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM_MessagingService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        notifyUser(remoteMessage.getFrom(),remoteMessage.getNotification().getBody());
    }
    public void notifyUser(String from, String notification){
        SaleNotificationManager saleNotificationManager = new SaleNotificationManager(getApplicationContext());
        saleNotificationManager.showNotification(from,notification,new Intent(getApplicationContext(), MessagingActivity.class));
    }
}
