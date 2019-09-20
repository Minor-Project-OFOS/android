package com.example.user.foodmania;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 8/6/2018.
 */

public class RecyclerAdapterOrder   extends RecyclerView.Adapter<RecyclerAdapterOrder.MyViewHolder> {

    ArrayList<OrderDetail> od=new ArrayList<>();
    Context context;
    static final String TAG="RecyclerOrder";


    public RecyclerAdapterOrder(Context context, ArrayList<OrderDetail> od)
    {
        this.context=context;
        this.od=od;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_order_detail,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mFoodname.setText(od.get(position).getFoodname());
        holder.mUname.setText(od.get(position).getUname());
        holder.mFname.setText(od.get(position).getFname());
        holder.mLocation.setText(od.get(position).getLocation());
        holder.mnumber.setText(od.get(position).getNumber());
        holder.mQuantity.setText(od.get(position).getQuantity());
        holder.mTotal.setText(od.get(position).getTotal());



    }

    @Override
    public int getItemCount() {
        return od.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView mFoodname;
        TextView mUname;
        TextView mFname;
        TextView mLocation;
        TextView mnumber;
        TextView mQuantity;
        TextView mTotal;


        public MyViewHolder(View itemView) {
            super(itemView);
            mFoodname=(TextView) itemView.findViewById(R.id.tv_lo_foodname);
            mUname=(TextView) itemView.findViewById(R.id.tv_lo_username);
            mFname=(TextView) itemView.findViewById(R.id.tv_lo_fullname);
            mLocation=(TextView) itemView.findViewById(R.id.tv_lo_location);
            mnumber=(TextView) itemView.findViewById(R.id.tv_lo_number);
            mQuantity=(TextView) itemView.findViewById(R.id.tv_lo_quantity);
            mTotal=(TextView) itemView.findViewById(R.id.tv_lo_total);




        }
    }


}
