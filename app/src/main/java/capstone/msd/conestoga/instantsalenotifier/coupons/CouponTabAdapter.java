package capstone.msd.conestoga.instantsalenotifier.coupons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

import capstone.msd.conestoga.instantsalenotifier.Main2Activity;

/**
 * Created by CatherineChoi on 1/2/2018.
 */

public class CouponTabAdapter extends FragmentPagerAdapter {
    private String TAG = "CouponTabAdapter".getClass().getSimpleName();

    public CouponTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
         Log.d(TAG, position+"/");
        switch (position) {
            case 0:
                return new SaleInfoFragment();
            case 1:
                return new DiscountInfoFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAG, position+"/getPagerTitle");
        switch (position) {
            case 0:
                return "Sale";
            case 1:
                return "Discount";
        }
        return null;
    }
}

