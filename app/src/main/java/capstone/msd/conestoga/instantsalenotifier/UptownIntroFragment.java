package capstone.msd.conestoga.instantsalenotifier;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import capstone.msd.conestoga.instantsalenotifier.category.SaleCategoryRecyclerViewAdapter;
import capstone.msd.conestoga.instantsalenotifier.model.StoreCategory;
import capstone.msd.conestoga.instantsalenotifier.utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UptownIntroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UptownIntroFragment extends Fragment {
     private String TAG = "UptownIntroFragment".getClass().getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;
    private  ViewPagerAdapter viewPagerAdapter;
    private  Handler handler = new Handler();


    public UptownIntroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UptownIntroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UptownIntroFragment newInstance(String param1, String param2) {
        UptownIntroFragment fragment = new UptownIntroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_uptown_intro, container, false);
        viewPager =(ViewPager) view.findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(   new Runnable() {
                    public void run() {
                        if (viewPagerAdapter.getCurrentPage() == Constants.uptownWaterlooImages.length- 1) {
                            viewPagerAdapter.setCurrentPage(0);
                        }
                        viewPagerAdapter.setCurrentItem();
                    }
                } );
            }
        }, 1000, 4000);

        return view ;
    }
}
