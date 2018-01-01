package capstone.msd.conestoga.instantsalenotifier.utils;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

import capstone.msd.conestoga.instantsalenotifier.R;

/**
 * Created by CatherineChoi on 12/11/2017.
 */

public class Constants {
    //Database Information
    private static final String ROOT_URL = "http://aashritha.000webhostapp.com/InstantSaleNotifier/Api.php?apicall=";
    public static final String URL_GET_STORES= ROOT_URL + "getstores";

    // Google Map
    public static final String GEOFENCE_ID_CONESTOGA_COLLEGE = "Conestoga College";
    public static final float GEOFENCE_RADIUS_IN_METERS = 100;

    public static final int[] gridTitleColor_ID ={
            R.color.colorAccent, R.color.categoryLightBlue,
            R.color.categoryMaterialYellow, R.color.categoryDeepBlue,
            R.color.lightYellow, R.color.categoryDeepPink,
            R.color.categoryDeepRed, R.color.categoryLightPink,
            R.color.categoryLightGreen, R.color.categoryMaterialGreen,
            R.color.categoryDeepPurple, R.color.darkGreen,
            R.color.categoryLightOrange, R.color.colorPrimaryDark
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

}