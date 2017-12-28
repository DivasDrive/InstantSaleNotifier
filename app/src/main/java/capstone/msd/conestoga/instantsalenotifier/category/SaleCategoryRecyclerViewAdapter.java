package capstone.msd.conestoga.instantsalenotifier.category;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import capstone.msd.conestoga.instantsalenotifier.R;
import capstone.msd.conestoga.instantsalenotifier.category.CategoryFragment.OnListFragmentInteractionListener;
import capstone.msd.conestoga.instantsalenotifier.dummy.DummyContent.DummyItem;
import capstone.msd.conestoga.instantsalenotifier.model.StoreCategory;
import capstone.msd.conestoga.instantsalenotifier.utils.Constants;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SaleCategoryRecyclerViewAdapter extends RecyclerView.Adapter<SaleCategoryRecyclerViewAdapter.ViewHolder> {
    //private final List[StoreCategory] categoryList;

    private final List<StoreCategory> mValues;
    private final OnListFragmentInteractionListener mListener;


    public SaleCategoryRecyclerViewAdapter(List<StoreCategory> items, OnListFragmentInteractionListener listener) {

        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_category_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCategoryImage.setImageResource(mValues.get(position).getResourceId());
        holder.mCategoryTitle.setText(mValues.get(position).getTitle());
     //   holder.mView.setBackgroundColor(Color.parseColor(Constants.gridColor[position]));
        holder.mCategoryTitle.setBackgroundColor(Color.parseColor(Constants.gridTitleColor[position]));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{// implements View.OnClickListener {
        public final View mView;
        public final ImageView mCategoryImage;
        public final TextView mCategoryTitle;
        public StoreCategory mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mCategoryImage = (ImageView)itemView.findViewById(R.id.categoryImage);
            mCategoryTitle = (TextView) itemView.findViewById(R.id.categoryTitle);
        }
        public void bingView(int position){

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCategoryTitle.getText() + "'";
        }

        /*@Override
        public void onClick(View view) {

        }*/
    }
}
