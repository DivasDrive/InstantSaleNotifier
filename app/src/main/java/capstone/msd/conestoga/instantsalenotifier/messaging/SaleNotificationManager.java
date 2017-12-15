package capstone.msd.conestoga.instantsalenotifier.messaging;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import capstone.msd.conestoga.instantsalenotifier.R;

/**
 * Created by CatherineChoi on 12/12/2017.
 */

public class SaleNotificationManager {
    private static final String TAG = "SaleNotificationManager";
    private Context mContext;
    public static int NOTIFICATION_ID =11;


    public SaleNotificationManager(Context pContext){
        this.mContext = pContext;
    }
    public void showNotification(String from, String notification, Intent intent){
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,NOTIFICATION_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT );
        NotificationCompat.Builder  builder = new NotificationCompat.Builder(mContext);
        Notification mNotification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(from)
                .setContentText(notification)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .build();
        mNotification.flags  |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,mNotification);

    }
}
