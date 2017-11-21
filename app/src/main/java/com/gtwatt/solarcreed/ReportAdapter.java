package com.gtwatt.solarcreed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtwatt.solarcreed.model.Report;

import java.util.List;

/**
 * Created by Gtwatt on 11/5/17.
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {

    private Context mContext;
    private List<Report> reportList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView goodEgg, badEgg, mortality, sickBirds, usedFeed, newFeed,dateReport;

        public MyViewHolder(View view) {
            super(view);
            goodEgg = (TextView) view.findViewById(R.id.good_egg_value);
            badEgg = (TextView) view.findViewById(R.id.damaged_egg_value);
            mortality = (TextView) view.findViewById(R.id.mortality_value);
            sickBirds = (TextView) view.findViewById(R.id.sick_bird_value);
            usedFeed = (TextView) view.findViewById(R.id.used_feed_value);
            newFeed = (TextView) view.findViewById(R.id.new_feed_value);
            dateReport = (TextView)view.findViewById(R.id.date_value);
        }
    }


    public ReportAdapter(Context mContext, List<Report> reports) {
        this.mContext = mContext;
        this.reportList = reports;
    }

    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReportAdapter.MyViewHolder holder, int position) {
        final Report report = reportList.get(position);
//            Glide.with(mContext).load(getImage(homeItem.getHomePics())).into(holder.cardImg);
        holder.goodEgg.setText(report.getGoodEgg() + " eggs");
        holder.badEgg.setText(report.getBadEgg()+ " eggs");
        holder.mortality.setText(report.getMortality()+ " birds");
        holder.sickBirds.setText(report.getSickBirds()+" birds");
        holder.usedFeed.setText(report.getUsedFeed()+" kg");
        holder.newFeed.setText(report.getNewFeed()+ " kg");
        holder.dateReport.setText(report.getDate());
    }


    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public int getImage(String imageName) {

        int drawableResourceId = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
        return drawableResourceId;
    }
}

