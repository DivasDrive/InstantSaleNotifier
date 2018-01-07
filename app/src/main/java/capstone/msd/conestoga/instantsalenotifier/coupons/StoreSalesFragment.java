package capstone.msd.conestoga.instantsalenotifier.coupons;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import capstone.msd.conestoga.instantsalenotifier.R;
import capstone.msd.conestoga.instantsalenotifier.model.SaleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class StoreSalesFragment extends Fragment {
    private String TAG = StoreSalesFragment.class.getSimpleName();
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StoreSalesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static StoreSalesFragment newInstance(int columnCount) {
        StoreSalesFragment fragment = new StoreSalesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storesales_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            List<SaleInfo> listSaleInfoList = getSaleInfoList();
            // recyclerView.setAdapter(new SaleCategoryRecyclerViewAdapter(listCategory, mListener));
            recyclerView.setAdapter(new StoreSalesRecyclerViewAdapter(listSaleInfoList));
        }
        return view;
    }
    private List<SaleInfo> getSaleInfoList() {
        String[] saleTile = new String[]{"Arts & Cultures", "Beauty", "Beer, Wine & Alchol", "Books", "Children & Baby", "Clothing & Accessories", "Eye Care", "Fitness & Sports", "Gifts, Stationary & Flowers", "Grocery & Specialty Food", "Home Furnishing & Interior", "Jewellers", "Music", "Variety & Specialty Shops"};
        int[] saleImageList = new int[]{R.drawable.electronics, R.drawable.entertainment, R.drawable.fashion, R.drawable.finance_insurance, R.drawable.lifestyle, R.drawable.motoring, R.drawable.other, R.drawable.travel, R.drawable.lifestyle, R.drawable.motoring, R.drawable.finance_insurance, R.drawable.fashion, R.drawable.electronics, R.drawable.entertainment, R.drawable.electronics};

        List<SaleInfo> saleInfoList = new ArrayList<SaleInfo>();

        for (int i = 0; i < saleTile.length; i++) {
            SaleInfo storeSale = new SaleInfo(saleTile[i]);
            saleInfoList.add(storeSale);
        }
        Log.d(TAG, String.valueOf(saleInfoList.size()));

        return saleInfoList;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction( );
    }
}
