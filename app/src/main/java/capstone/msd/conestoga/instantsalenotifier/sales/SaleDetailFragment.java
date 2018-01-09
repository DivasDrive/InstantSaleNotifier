package capstone.msd.conestoga.instantsalenotifier.sales;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import capstone.msd.conestoga.instantsalenotifier.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleDetailFragment extends Fragment {


    public SaleDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale_detail, container, false);
    }

}
