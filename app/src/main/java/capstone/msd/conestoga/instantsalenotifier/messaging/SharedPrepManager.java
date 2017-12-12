package capstone.msd.conestoga.instantsalenotifier.messaging;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CatherineChoi on 12/12/2017.
 */

public class SharedPrepManager {
    private static final String FCM_TOKEN_NAME="FCM_TOKEN_NAME";
    private static final String KEY_ACCESS_TOKEN="TOKEN";

    private static Context mContext;
    private static SharedPrepManager mInstance;

    private SharedPrepManager(Context pContext){
        this.mContext = pContext;
    }
    public static synchronized  SharedPrepManager getInstance(Context pContext){
        if(mInstance == null) {
            mInstance = new SharedPrepManager(pContext);
        }
        return mInstance;
    }
    public  boolean storeToken(String token){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FCM_TOKEN_NAME ,mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, token);
        editor.apply();
        return true;
    }
    public  String getToken( ){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FCM_TOKEN_NAME ,mContext.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ACCESS_TOKEN,null);


    }

}
