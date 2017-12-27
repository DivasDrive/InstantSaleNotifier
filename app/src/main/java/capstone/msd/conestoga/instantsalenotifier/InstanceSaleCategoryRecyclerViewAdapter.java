package capstone.msd.conestoga.instantsalenotifier;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import capstone.msd.conestoga.instantsalenotifier.CategoryFragment.OnListFragmentInteractionListener;
import capstone.msd.conestoga.instantsalenotifier.dummy.DummyContent.DummyItem;
import capstone.msd.conestoga.instantsalenotifier.model.StoreCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class InstanceSaleCategoryRecyclerViewAdapter extends RecyclerView.Adapter<InstanceSaleCategoryRecyclerViewAdapter.ViewHolder> {
    //private final List[StoreCategory] categoryList;

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public InstanceSaleCategoryRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        //categoryList = new ArrayList<StoreCategory>();

        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCateroyImage.setBackgroundResource(R.drawable.travel);
      //  holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mCateroyImage;
    //    public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCateroyImage = (ImageView)view.findViewById(R.id.imgCategory);
  //          mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
