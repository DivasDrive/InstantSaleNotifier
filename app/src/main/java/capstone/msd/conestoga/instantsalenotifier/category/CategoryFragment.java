package capstone.msd.conestoga.instantsalenotifier.category;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import capstone.msd.conestoga.instantsalenotifier.BaseFragment;
import capstone.msd.conestoga.instantsalenotifier.R;
import capstone.msd.conestoga.instantsalenotifier.model.StoreCategory;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p>
 * interface.
 */
public class CategoryFragment extends BaseFragment {
    private String TAG = "CategoryFragment".getClass().getSimpleName();
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CategoryFragment newInstance(int columnCount) {
        CategoryFragment fragment = new CategoryFragment();
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
        Log.d(TAG, "onCreate");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);

        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            List<StoreCategory> listCategory = getStoreCategoryList();
           // recyclerView.setAdapter(new SaleCategoryRecyclerViewAdapter(listCategory, mListener));
            recyclerView.setAdapter(new SaleCategoryRecyclerViewAdapter(listCategory));
        }
        return view;
    }

    private List<StoreCategory> getStoreCategoryList() {
        String[] categoryTitleList = new String[]{"Arts & Cultures", "Beauty", "Beer, Wine & Alchol", "Books", "Children & Baby", "Clothing & Accessories", "Eye Care", "Fitness & Sports", "Gifts, Stationary & Flowers", "Grocery & Specialty Food", "Home Furnishing & Interior", "Jewellers", "Music", "Variety & Specialty Shops"};
        int[] categoryImageList = new int[]{R.drawable.electronics, R.drawable.entertainment, R.drawable.fashion, R.drawable.finance_insurance, R.drawable.lifestyle, R.drawable.motoring, R.drawable.other, R.drawable.travel, R.drawable.lifestyle, R.drawable.motoring, R.drawable.finance_insurance, R.drawable.fashion, R.drawable.electronics, R.drawable.entertainment, R.drawable.electronics};

        List<StoreCategory> categoryList = new ArrayList<StoreCategory>();

        for (int i = 0; i < categoryTitleList.length; i++) {
            StoreCategory storeCategory = new StoreCategory(categoryTitleList[i], categoryImageList[i]);
            categoryList.add(storeCategory);
        }

        return categoryList;
    }

    /**
     * http://java-lang-programming.com/en/articles/26
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
