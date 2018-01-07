package capstone.msd.conestoga.instantsalenotifier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import capstone.msd.conestoga.instantsalenotifier.utils.Constants;

/**
 * Created by CatherineChoi on 1/7/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private String TAG = "ViewPagerAdapter".getClass().getSimpleName();

    private Context mContext;
    private LayoutInflater layoutInflater;
    private int currentPage;
    private ViewPager vp;

    public ViewPagerAdapter(Context pContext) {
        this.mContext = pContext;
    }

    @Override
    public int getCount() {
        return Constants.uptownWaterlooImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.uptown_intro_layout, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.imgUptown);
        imageView.setImageResource(Constants.uptownWaterlooImages[position]);

        vp = (ViewPager)container;
        vp.addView(view,0);
        currentPage = position;

        Log.d(TAG, String.valueOf(position));

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
         View view  = (View)object;
         container.removeView(view);
    }
    public int getCurrentPage(){
        Log.d(TAG, String.valueOf("getCurrentPage()" + currentPage));

        return currentPage;
    }
    public void setCurrentPage(int position){
        this.currentPage  = position;
    }
    public void setCurrentItem(){
        Log.d(TAG, String.valueOf(currentPage));

        vp.setCurrentItem(currentPage, true);
    }
}
