package com.example.user.foodmania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class OrderDetailActivity extends AppCompatActivity {
    String username;
    String accessType;
   Toolbar mtoolbar;
    RecyclerView recyclerView;
    RecyclerAdapterOrder adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<OrderDetail> od=new ArrayList<>();
    DatabaseHelperOrderDetail databaseHelperOrderDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Bundle bundle=getIntent().getExtras();
          mtoolbar=(Toolbar)findViewById(R.id.toolbarOrder);
          mtoolbar.setTitle("Order Details");

        username=bundle.getString("Username");
        accessType=bundle.getString("AccessType");



        if(accessType.equals("user"))
        {
            loadArrayUser();
        }
        else
        {
            loadArrayAdmin();

        }
        setupRecyclerView();

    }
    public void loadArrayUser()
    {
             databaseHelperOrderDetail=new DatabaseHelperOrderDetail(this);

        try {
            od=databaseHelperOrderDetail.execute(accessType,username).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    public void loadArrayAdmin()
    {
        databaseHelperOrderDetail=new DatabaseHelperOrderDetail(this);

        try {
            od=databaseHelperOrderDetail.execute(accessType).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }
    public void setupRecyclerView()
    {
        recyclerView=(RecyclerView) findViewById(R.id.recyclerviewOrder);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new RecyclerAdapterOrder(this,od);
        recyclerView.setAdapter(adapter);
    }


}
