package com.gtwatt.solarcreed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gtwatt on 11/1/17.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {

    private Context mContext;
    private List<RecordItem> recordItems;
    int type;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView editImg;
        public TextView count, type, details, genType;

        public MyViewHolder(View view) {
            super(view);
            count = (TextView) view.findViewById(R.id.record_count);
            type = (TextView) view.findViewById(R.id.record_type);
            details = (TextView) view.findViewById(R.id.record_details);
            genType = (TextView) view.findViewById(R.id.genType);
        }
    }


    public RecordAdapter(Context mContext, List<RecordItem> homeItems, int type) {
        this.mContext = mContext;
        this.recordItems = homeItems;
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecordAdapter.MyViewHolder holder, int position) {
        final RecordItem recordItem = recordItems.get(position);
        holder.count.setText(recordItem.getCount());
        holder.type.setText(recordItem.getType());
        holder.details.setText(recordItem.getDetails());
        if (type == 0){
            if(position == 0 || position == 3 || position == 5){
                holder.genType.setVisibility(View.VISIBLE);
            }
        }

        if(position ==3 && type ==0){
            holder.genType.setText("Eggs");
        }else if(position ==5 && type ==0){
            holder.genType.setText("Feeds");

        }

        if(position ==0 && type ==1){
            holder.genType.setText("Feeds");
        }else if(position ==1 && type ==1){
            holder.genType.setText("ShowAlert");
        }else if(position ==2 && type ==1){
            holder.genType.setText("Miscellanous");
        }else if(position ==3 && type ==1){
            holder.genType.setText("Total");
        }

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
