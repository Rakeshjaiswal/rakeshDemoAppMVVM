package com.example.rakeshdemotestretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rakeshdemotestretrofit.R;
import com.example.rakeshdemotestretrofit.model.Transaction;

import java.util.List;

public class TransactionAdapter extends BaseAdapter {

    private final Context context;
    private final List<Transaction> transactionList;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public Object getItem(int position) {
        return transactionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false);
        }

        Transaction transaction = transactionList.get(position);
        TextView tvDate = convertView.findViewById(R.id.date);
        TextView tvCategory = convertView.findViewById(R.id.category);
        TextView tvDescription = convertView.findViewById(R.id.description);
        TextView tvAmount = convertView.findViewById(R.id.amount);

        if (transaction.getDate() != null)
            tvDate.setText(transaction.getDate());
        if (transaction.getCategory() != null)
            tvCategory.setText(transaction.getCategory());
        if (transaction.getDescription() != null)
            tvDescription.setText(transaction.getDescription());
        if (transaction.getAmount() != 0)
            tvAmount.setText(String.valueOf(transaction.getAmount()));

        return convertView;
    }
}

