package capstone.msd.conestoga.instantsalenotifier.sales;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import capstone.msd.conestoga.instantsalenotifier.R;
import capstone.msd.conestoga.instantsalenotifier.SendMessagingFragment;
import capstone.msd.conestoga.instantsalenotifier.dummy.DummyContent.DummyItem;
import capstone.msd.conestoga.instantsalenotifier.model.SaleInfo;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class StoreSalesRecyclerViewAdapter extends RecyclerView.Adapter<StoreSalesRecyclerViewAdapter.ViewHolder> {
    private final List<SaleInfo> mValues;
  //  private final OnListFragmentInteractionListener mListener;


    //public SaleCategoryRecyclerViewAdapter(List<StoreCategory> items, OnListFragmentInteractionListener listener) {
    public StoreSalesRecyclerViewAdapter(List<SaleInfo> items ) {
        mValues = items;
      //  mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sales_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
      // holder.mItemImage.setImageResource(mValues.get(position).getResourceId());
        holder.mTxtSaleTitle.setText(mValues.get(position).getTitle());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                  //  mListener.onListFragmentInteraction(holder.mItem);
                }*/


            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{// implements View.OnClickListener {
        public final View mView;
        public final ImageView mItemImage;
        public final TextView  mTxtSaleTitle;
        public SaleInfo mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mItemImage  = (ImageView)itemView.findViewById(R.id.item_image);
            mTxtSaleTitle = (TextView) itemView.findViewById(R.id.txtSaleTitle);
        }
        public void bingView(int position){

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTxtSaleTitle.getText() + "'";
        }

        /*@Override
        public void onClick(View view) {

        }*/
    }
}
