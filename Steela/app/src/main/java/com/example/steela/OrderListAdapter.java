package com.example.steela;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderListAdapter extends ArrayAdapter<Order_Class> {

    private Context myContext;
    int mResource;

    public OrderListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Order_Class> objects) {
        super(context, resource, objects);
        myContext=context;
        mResource=resource;

    }

    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String product_name=getItem(position).getProduct_name();
        String customer_name=getItem(position).getCustomer_name();
        String amount=getItem(position).getProduct_amount();

        Order_Class order=new Order_Class(product_name,customer_name,amount);

        LayoutInflater inflater=LayoutInflater.from(myContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView one=convertView.findViewById(R.id.selocan);
        TextView two=convertView.findViewById(R.id.selocan2);
        TextView three=convertView.findViewById(R.id.selocan3);

        one.setText(product_name);
        two.setText(customer_name);
        three.setText(amount);


        return convertView;
    }

}
