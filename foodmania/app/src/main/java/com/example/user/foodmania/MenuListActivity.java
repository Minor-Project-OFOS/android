package com.example.user.foodmania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MenuListActivity extends AppCompatActivity {
    String type;
    String username;
    Toolbar mToolbar;
    ArrayList<String> fnames=new ArrayList<>();
    Boolean isLoggedIn;
    RecyclerView recyclerView;
    RecyclerAdapter  adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        mToolbar=(Toolbar) findViewById(R.id.toolbarFi);

        Bundle bundle=getIntent().getExtras();
        type=bundle.getString("MenuType");
        isLoggedIn=bundle.getBoolean("LoginStatus");
        username=bundle.getString("Username");

        mToolbar.setTitle(type);


        loadArrayNames();


        setupRecyclerView();
    }


    public void loadArrayNames()
    {
        DatabaseHelperMenuList helperMenuList=new DatabaseHelperMenuList(MenuListActivity.this);


        try {
            fnames= helperMenuList.execute(type).get();

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


    public void setupRecyclerView()
    {
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new RecyclerAdapter(this,fnames,type,isLoggedIn,username);
        recyclerView.setAdapter(adapter);

    }
}
