package capstone.msd.conestoga.instantsalenotifier.sales;

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
import android.widget.TextView;

import capstone.msd.conestoga.instantsalenotifier.BaseFragment;
import capstone.msd.conestoga.instantsalenotifier.R;
import capstone.msd.conestoga.instantsalenotifier.model.SaleInfo;
import capstone.msd.conestoga.instantsalenotifier.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class StoreSalesFragment extends BaseFragment {
    private String TAG = StoreSalesFragment.class.getSimpleName();
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private String strStoreTitle = "Instant SALES";

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
        View view = inflater.inflate(R.layout.fragment_sale_list, container, false);
        View listView  = (RecyclerView)view.findViewById(R.id.listRecyclerView);
        TextView txtStoreTitle = (TextView)view.findViewById(R.id.storeTitle);
        strStoreTitle =getArguments().getString(Constants.STORE_TITLE,"SALES");
        txtStoreTitle.setText(strStoreTitle  );

        // Set the adapter
        if (listView instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) listView;
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
        String[] saleTile = new String[]{"Arts & Cultures", "Beauty", "Beer, Wine & Alchol", "Books", "Children & Baby", "Clothing & Accessories", "Eye Care", "Fitness & Sports", "Gifts, Stationary & Flowers", "Grocery & Specialty Food", "Home Furnishing & Interior", "Jewellers", "Music", "Variety & Specialty Shops","Accommodations","Advertising & Marketing Services"};
        int[] saleImageList = new int[]{R.drawable.electronics, R.drawable.entertainment, R.drawable.fashion, R.drawable.finance_insurance, R.drawable.lifestyle, R.drawable.motoring, R.drawable.other, R.drawable.travel, R.drawable.lifestyle, R.drawable.motoring, R.drawable.finance_insurance, R.drawable.fashion, R.drawable.electronics, R.drawable.entertainment, R.drawable.electronics,R.drawable.entertainment, R.drawable.fashion};

        List<SaleInfo> saleInfoList = new ArrayList<SaleInfo>();

        for (int i = 0; i < saleTile.length; i++) {
            SaleInfo storeSale = new SaleInfo(saleTile[i]);
            saleInfoList.add(storeSale);
        }
        Log.d(TAG, String.valueOf(saleInfoList.size()));

        return saleInfoList;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }
}
