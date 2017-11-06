package com.gtwatt.solarcreed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gtwatt on 11/2/17.
 */

public class VaccinationAdapter extends RecyclerView.Adapter<VaccinationAdapter.MyViewHolder> {

    private Context mContext;
    private List<RecordItem> recordItems;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView editImg;
        public TextView count, type, details;

        public MyViewHolder(View view) {
            super(view);
            count = (TextView) view.findViewById(R.id.record_count);
            type = (TextView) view.findViewById(R.id.record_type);
            details = (TextView) view.findViewById(R.id.record_details);
        }
    }


    public VaccinationAdapter(Context mContext, List<RecordItem> homeItems) {
        this.mContext = mContext;
        this.recordItems = homeItems;
    }

    @Override
    public VaccinationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vaccination_row, parent, false);

        return new VaccinationAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VaccinationAdapter.MyViewHolder holder, int position) {
        final RecordItem recordItem = recordItems.get(position);
//            Glide.with(mContext).load(getImage(homeItem.getHomePics())).into(holder.cardImg);
        holder.count.setText(recordItem.getCount());
        holder.type.setText(recordItem.getType());
        holder.details.setText(recordItem.getDetails());


    }


    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return recordItems.size();
    }

    public int getImage(String imageName) {

        int drawableResourceId = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
        return drawableResourceId;
    }
}

