package com.gtwatt.solarcreed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtwatt.solarcreed.model.Expense;

import java.util.List;

/**
 * Created by Gtwatt on 11/6/17.
 */

public class ExpenseFragmentAdapter extends RecyclerView.Adapter<ExpenseFragmentAdapter.MyViewHolder> {

    private Context mContext;
    private List<Expense> expenseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  eggExpense, birdExpense, feedExpense, expenseDate;

        public MyViewHolder(View view) {
            super(view);
            eggExpense = (TextView) view.findViewById(R.id.expense_egg_value);
            birdExpense = (TextView) view.findViewById(R.id.bird_expense_value);
            feedExpense = (TextView) view.findViewById(R.id.feed_expense_value);
            expenseDate = (TextView)view.findViewById(R.id.expense_date_value);

        }
    }


    public ExpenseFragmentAdapter(Context mContext, List<Expense> expenses) {
        this.mContext = mContext;
        this.expenseList = expenses;
    }

    @Override
    public ExpenseFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_report_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ExpenseFragmentAdapter.MyViewHolder holder, int position) {
        final Expense expense = expenseList.get(position);
        holder.eggExpense.setText(expense.getMiscExpense());
        holder.birdExpense.setText(expense.getBirdExpense());
        holder.feedExpense.setText(expense.getFeedExpense());
        holder.expenseDate.setText(expense.getDate());

    }


    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public int getImage(String imageName) {

        int drawableResourceId = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
        return drawableResourceId;
    }
}

