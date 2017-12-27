package capstone.msd.conestoga.instantsalenotifier.messaging;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import capstone.msd.conestoga.instantsalenotifier.R;

/**
 * Created by CatherineChoi on 12/11/2017.
 */


public class MessagingActivity extends AppCompatActivity {
    private static final String TAG = MessagingActivity.class.getSimpleName();
    private TextView mTextview;
    private BroadcastReceiver mBroadcastReceiver ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        mTextview = (TextView)this.findViewById(R.id.textViewToken);
        mBroadcastReceiver  = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mTextview.setText(SharedPrepManager.getInstance(MessagingActivity.this).getToken());
            }
        };
        if(SharedPrepManager.getInstance(MessagingActivity.this).getToken() != null) {
            mTextview.setText(SharedPrepManager.getInstance(MessagingActivity.this).getToken());
            Log.d("getToken:", SharedPrepManager.getInstance(MessagingActivity.this).getToken());
        }
        this.registerReceiver(mBroadcastReceiver,new IntentFilter(FCM_IDService.TOKEN_BROADCAST));

    }
}
