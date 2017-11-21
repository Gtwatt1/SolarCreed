package com.gtwatt.solarcreed;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.gtwatt.solarcreed.model.Vaccine;

import java.util.List;

/**
 * Created by Gtwatt on 11/21/17.
 */

public class ProfilePenAdapter extends RecyclerView.Adapter<ProfilePenAdapter.MyViewHolder> {

    private Context mContext;
    private List<Vaccine> recordItems;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public View container;
        public TextView count, type, details, time;

        public MyViewHolder(View view) {
            super(view);
            count = (TextView) view.findViewById(R.id.vaccine_count);
            type = (TextView) view.findViewById(R.id.record_type);
            details = (TextView) view.findViewById(R.id.record_details);
            time = (TextView) view.findViewById(R.id.vaccine_time);
            checkBox = (CheckBox) view.findViewById(R.id.vac_box);
            container = (View)view.findViewById(R.id.vaccine_container);

        }
    }


    public ProfilePenAdapter(Context mContext, List<Vaccine> homeItems) {
        this.mContext = mContext;
        this.recordItems = homeItems;
    }

    public void setAdapterData(List<Vaccine> homeItems) {
        this.recordItems = homeItems;
        notifyDataSetChanged();
    }

    @Override
    public ProfilePenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vaccination_row, parent, false);

        return new ProfilePenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProfilePenAdapter.MyViewHolder holder, int position) {
        final Vaccine vaccine = recordItems.get(position);
//            Glide.with(mContext).load(getImage(homeItem.getHomePics())).into(holder.cardImg);
        holder.count.setText(vaccine.getNumberBirds() + " ShowAlert");
        holder.type.setText(vaccine.getType());
        holder.details.setText(vaccine.getDetail());
        holder.time.setText(vaccine.getDate());
        if (vaccine.isCompleted()) {
            holder.checkBox.setSelected(true);
        } else {
            holder.checkBox.setSelected(false);
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    holder.container.setBackgroundColor(Color.parseColor("#23e342"));
                }else{
                    holder.container.setBackgroundColor(Color.parseColor("#ffffff"));

                }

            }
        });

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

