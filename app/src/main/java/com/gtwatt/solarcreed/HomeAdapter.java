package com.gtwatt.solarcreed;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Gtwatt on 11/1/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context mContext;
    private List<HomeItem> homeItems;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView cardImg;
        public TextView count, type;
        public Button addString;
        public View cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = (View)view.findViewById(R.id.home_card_view);
            cardImg = (ImageView) view.findViewById(R.id.home_card_pic);
            count = (TextView) view.findViewById(R.id.home_card_count);
            type = (TextView) view.findViewById(R.id.home_card_type);
            addString = (Button) view.findViewById(R.id.home_card_add);
        }
    }


    public HomeAdapter(Context mContext, List<HomeItem> homeItems) {
        this.mContext = mContext;
        this.homeItems = homeItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homecard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final HomeItem homeItem = homeItems.get(position);
        if (position  == 1 || position == 2){
            holder.cardView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryShade));
        }
            Glide.with(mContext).load(getImage(homeItem.getHomePics())).into(holder.cardImg);
        holder.count.setText(homeItem.getHomeCount());
        holder.type.setText(homeItem.getHomeType());
        holder.addString.setText(homeItem.getAddString());


    }




    @Override
    public int getItemCount() {
        return homeItems.size();
    }

    public int getImage(String imageName) {

        int drawableResourceId = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
        return drawableResourceId;
    }
}
