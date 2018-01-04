package capstone.msd.conestoga.instantsalenotifier.coupons;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import capstone.msd.conestoga.instantsalenotifier.R;
import capstone.msd.conestoga.instantsalenotifier.category.SaleCategoryRecyclerViewAdapter;
import capstone.msd.conestoga.instantsalenotifier.model.Discount;
import capstone.msd.conestoga.instantsalenotifier.model.StoreCategory;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscountInfoFragment extends Fragment {


    public DiscountInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discount_info, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.discount_listView);

        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();
             recyclerView.setLayoutManager(new LinearLayoutManager(context));

            List<Discount> listDiscount =  getStoreDiscountList();
            recyclerView.setAdapter(new DiscounViewAdapter(listDiscount));

        }
        return view;

    }
    private List<Discount> getStoreDiscountList(){
        String[] discountTitleList = new String[]{"Arts & Cultures","Beauty","Beer, Wine & Alchol","Books","Children & Baby","Clothing & Accessories", "Eye Care","Fitness & Sports","Gifts, Stationary & Flowers","Grocery & Specialty Food","Home Furnishing & Interior","Jewellers","Music","Variety & Specialty Shops"};

        List<Discount> discountList = new ArrayList<Discount>();

        for (int i = 0; i < discountList.size(); i++) {
            Discount discount = new Discount(discountTitleList[i] );
            discountList.add(discount);
        }

        return discountList;
    }


}
