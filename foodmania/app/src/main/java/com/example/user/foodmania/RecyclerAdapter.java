package com.example.user.foodmania;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 8/5/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    ArrayList<String> fnames=new ArrayList<>();
    private Context context;
    private String type;
    private Boolean isLoggedIn;
    private String username;


    public RecyclerAdapter(Context context,ArrayList<String> fnames,String type,Boolean isLoggedIn,String username) {
        this.context=context;
        this.fnames=fnames;
        this.type=type;
        this.isLoggedIn=isLoggedIn;
        this.username=username;
        }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final String name_food=fnames.get(position);
        holder.f_name.setText(name_food);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,FoodDetailsActivity.class);
                intent.putExtra("MenuType",type);
                intent.putExtra("Foodname",name_food);
                intent.putExtra("LoginStatus",isLoggedIn);
                intent.putExtra("Username",username);

                context.startActivity(intent);
               /* Toast.makeText(context,username+","+isLoggedIn.toString(),Toast.LENGTH_SHORT).show();*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return fnames.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView f_name;

        public MyViewHolder(View itemView) {
            super(itemView);

            f_name=(TextView) itemView.findViewById(R.id.tv_fname);
        }
    }


  /*  public void setFilter(ArrayList<String> newList,Boolean newLoggedIn,String newUsername,String newAccesstype)
    {
        hnames=new ArrayList<>();
        hnames.addAll(newList);
        isLoggedIn=newLoggedIn;
        username=newUsername;
        accessType=newAccesstype;

        notifyDataSetChanged();

    }*/
}

