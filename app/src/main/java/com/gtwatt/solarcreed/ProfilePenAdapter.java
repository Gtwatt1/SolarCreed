package com.gtwatt.solarcreed;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gtwatt.solarcreed.model.Pen;

import java.util.List;

/**
 * Created by Gtwatt on 11/21/17.
 */

public class ProfilePenAdapter extends RecyclerView.Adapter<ProfilePenAdapter.MyViewHolder> {

    private Context mContext;
    private List<Pen> pens;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView penNumber, penName;
        ImageView showEdit;
        LinearLayout container;

        public MyViewHolder(View view) {
            super(view);
            penNumber = (TextView) view.findViewById(R.id.pen_number);
            penName = (TextView) view.findViewById(R.id.pen_name);
            showEdit = (ImageView)view.findViewById(R.id.pen_click);
            container = (LinearLayout)view.findViewById(R.id.penContainer);

        }
    }


    public ProfilePenAdapter(Context mContext, List<Pen> pens) {
        this.mContext = mContext;
        this.pens = pens;


    }

    public void setAdapterData(List<Pen> pens) {
        this.pens = pens;
        notifyDataSetChanged();
    }

    @Override
    public ProfilePenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_row, parent, false);

        return new ProfilePenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProfilePenAdapter.MyViewHolder holder, int position) {
        final Pen pen = pens.get(position);
        holder.penNumber.setText("" +pen.getBirdsNumber() + "");
        holder.penName.setText(pen.getName());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPenDetails();
            }
        });


        holder.showEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowAlert.addPenDialog(mContext, pen);
            }
        });

    }

    public void showPenDetails(){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View mView = inflater.inflate(R.layout.show_pen_dialog, null);

        builder.setPositiveButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        TextView titleView = new TextView(mContext);
        titleView.setText("Details");
//        titleView.setText("Convert " + cryptoConver.getCryptoType().getName() + "  to  " + cryptoConver.getCurrency().getName());
        titleView.setTextSize(14);
        titleView.setTextColor(mContext.getResources().getColor( R.color.colorAccent));
        titleView.setGravity(Gravity.CENTER);
        builder.setCustomTitle(titleView);
        builder.setView(mView);
        builder.show();

    }


    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return pens.size();
    }


}

