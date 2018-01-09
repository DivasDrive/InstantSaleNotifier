package capstone.msd.conestoga.instantsalenotifier.utils;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

import capstone.msd.conestoga.instantsalenotifier.R;

/**
 * Created by CatherineChoi on 12/11/2017.
 */

public class Constants {
    //Database Information
    public static final String BASE_URL = "http://instantsalenotifier.store/Final project/";
    public static final String ROOT_URL =  BASE_URL +"Api.php?apicall=";
    public static final String URL_GET_STORES= ROOT_URL + "getstores";
    public static final String URL_GET_STORES_INFO= ROOT_URL + "getStoreInfo";
    public static final String URL_POST_TOKEN= ROOT_URL + "insertFcmToken";
    //public static final String URL_SEND_NOFITICATION =  BASE_URL+"Send_notification.html";
    public static final String URL_SEND_NOFITICATION =  BASE_URL+"push.php";

    public static final int CODE_GET_REQUEST = 1024;
    public static final int CODE_POST_REQUEST = 1025;

    // Google Map
    public static final String GEOFENCE_ID_CONESTOGA_COLLEGE = "Conestoga College";
    public static final float GEOFENCE_RADIUS_IN_METERS = 100;

    //Store Key
    public static final String STORE_TITLE = "StoreName";


    public static final int[] gridTitleColor_ID ={
            R.color.colorAccent, R.color.categoryLightBlue,
            R.color.categoryMaterialYellow, R.color.categoryDeepBlue,
            R.color.lightYellow, R.color.categoryDeepPink,
            R.color.categoryDeepRed, R.color.categoryLightPink,
            R.color.categoryLightGreen, R.color.categoryMaterialGreen,
            R.color.categoryDeepPurple, R.color.darkGreen,
            R.color.categoryLightOrange, R.color.colorPrimaryDark,
            R.color.colorAccent, R.color.categoryLightBlue
    };
    public static final String[] gridTitleColor ={
            "#FF4081", "#7C4DFF",
            "#FFb300", "#0049E6",
            "#FFF200", "#EE499B",
            "#EE3A4A", "#FDE7D4",
            "#CDDEB5", "#00BFA5",
            "#8C3283", "#22741C",
            "#FFA000", "#303F9F"
    };
    public static final int[] uptownWaterlooImages={   R.drawable.uptownwaterloo_01, R.drawable.uptownwaterloo_03,
            R.drawable.uptownwaterloo_06, R.drawable.uptownwaterloo_07,
            R.drawable.uptownwaterloo_08, R.drawable.uptownwaterloo_11,
            R.drawable.uptownwaterloo_17
    };
    public static final String[] uptownWaterlooTitle={   "Culture","Life", "Style", "Beauty","Taste","Innovation", "Knowledge"    };

}