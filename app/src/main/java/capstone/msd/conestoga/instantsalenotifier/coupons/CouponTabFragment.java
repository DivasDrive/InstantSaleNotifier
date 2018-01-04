package capstone.msd.conestoga.instantsalenotifier.coupons;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import capstone.msd.conestoga.instantsalenotifier.BaseFragment;
import capstone.msd.conestoga.instantsalenotifier.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CouponTabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CouponTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CouponTabFragment extends BaseFragment {
    private String TAG = "CouponTabFragment".getClass().getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private AppBarLayout appbarLayout;
    private TabLayout tabs;
    private ViewPager viewPager; ;

    public CouponTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CouponTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CouponTabFragment newInstance(String param1, String param2) {
        CouponTabFragment fragment = new CouponTabFragment();
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
        Log.d(TAG, "OnCreateView");
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_coupon_tab,container,false);
      //  View viewParent = (View)container.getParent();
       // appbarLayout =(AppBarLayout) viewParent.findViewById(R.id.appBar);

        tabs =(TabLayout) view.findViewById(R.id.coupon_tabs);
        //appbarLayout.addView(tabs);

        viewPager =(ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new CouponTabAdapter(getFragmentManager()));

        tabs.setupWithViewPager(viewPager);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        super.onButtonPressed(uri);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
