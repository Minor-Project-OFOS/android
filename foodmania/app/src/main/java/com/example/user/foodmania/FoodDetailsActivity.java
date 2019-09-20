package com.example.user.foodmania;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class FoodDetailsActivity extends AppCompatActivity {
    String type;
    Toolbar mToolbar;
    String foodname;
    ImageView ivFood;
    TextView tvFoodname;
    TextView tvPrice;
    FoodDetails fd;
    Button btnOrder;
    int imageId;
    Boolean isLoggedIn;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        mToolbar=(Toolbar) findViewById(R.id.toolbarFd);
        ivFood=(ImageView)findViewById(R.id.ivFood);
        tvFoodname=(TextView)findViewById(R.id.tvfname);
        tvPrice=(TextView)findViewById(R.id.tvprice);
        btnOrder=(Button)findViewById(R.id.btnOrder);

        Bundle bundle=getIntent().getExtras();
        type=bundle.getString("MenuType");
        foodname=bundle.getString("Foodname");
        isLoggedIn=bundle.getBoolean("LoginStatus");
        username=bundle.getString("Username");
        mToolbar.setTitle(foodname);




        LoadInfo();





    }

    public  void LoadInfo()
    {

      DatabaseHelperFoodDetail databaseHelperFoodDetail=new DatabaseHelperFoodDetail(FoodDetailsActivity.this);

        try {
            fd=databaseHelperFoodDetail.execute(type,foodname).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

   tvFoodname.setText(fd.getName());
        tvPrice.setText(fd.getPrice());

        imageId=getResources().getIdentifier(fd.getPicture(),"drawable",getPackageName());

        ivFood.setImageResource(imageId);



    }

    public void perform_order(View view) {

        if(isLoggedIn)
        {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(FoodDetailsActivity.this);
            final View mView = getLayoutInflater().inflate(R.layout.dialog_order, null);
            mBuilder.setView(mView);
            final DatabaseHelperOrder databaseHelperOrder=new DatabaseHelperOrder(FoodDetailsActivity.this);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
            final TextView mFoodname=(TextView)mView.findViewById(R.id.tv_db_foodname);
            final TextView mUname=(TextView)mView.findViewById(R.id.tv_db_username);
            final EditText mFname = (EditText) mView.findViewById(R.id.et_dbFname);
            final EditText mLocation = (EditText) mView.findViewById(R.id.et_dbLocation);
            final EditText mNumber = (EditText) mView.findViewById(R.id.et_dbNumber);
            final EditText mQuantity = (EditText) mView.findViewById(R.id.et_Quantity);
            final TextView mTotal=(TextView)mView.findViewById(R.id.tvfinalprice);
            final Button mOrder = (Button) mView.findViewById(R.id.btndialogOrder);
            mFoodname.setText(foodname);
            mUname.setText(username);




            mQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(mQuantity.hasFocus()){
                        try{
                            if ("".equals(mQuantity.getText().toString())){
                                mQuantity.setText("");

                                return;
                            }
                            final int quantity=Integer.parseInt(mQuantity.getText().toString());
                            final  int price=Integer.parseInt(fd.getPrice());
                            final int total  =quantity*price;
                            mTotal.setText(String.valueOf(total));


                        }catch (Exception e){

                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });




            mOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {





                    mOrder.setClickable(false);
                    final ProgressDialog progressDialog = new ProgressDialog(FoodDetailsActivity.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Ordering...");
                    progressDialog.show();
                    if (!mFname.getText().toString().isEmpty() && !mQuantity.getText().toString().isEmpty() && !mLocation.getText().toString().isEmpty() && !mNumber.getText().toString().isEmpty())
                    {


                            type="order";
                            String res="";
                            try {
                                res= databaseHelperOrder.execute(type,foodname,username,mFname.getText().toString(),mLocation.getText().toString(),mNumber.getText().toString(),mQuantity.getText().toString(),mTotal.getText().toString()).get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            final String result=res;

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {

                                            if(result.equals("yes"))
                                                Toast.makeText(FoodDetailsActivity.this,"Order completed", Toast.LENGTH_SHORT).show();
                                            else
                                                Toast.makeText(FoodDetailsActivity.this,"Order not completed", Toast.LENGTH_SHORT).show();


                                            mOrder.setClickable(true);
                                            progressDialog.dismiss();
                                            dialog.hide();

                                        }
                                    }, 3000);










                    }
                    else
                    {
                        Toast.makeText(FoodDetailsActivity.this, "Please fill up the empty fields", Toast.LENGTH_SHORT).show();
                        mOrder.setClickable(true);
                        progressDialog.dismiss();

                    }




                }
            });


        }
        else
        {
            Toast.makeText(FoodDetailsActivity.this,"You need to login first to Order", Toast.LENGTH_SHORT).show();

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(FoodDetailsActivity.this);
            final View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
            mBuilder.setView(mView);
            final DatabaseHelperUser helperUser=new DatabaseHelperUser(FoodDetailsActivity.this);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
            final EditText mUname = (EditText) mView.findViewById(R.id.et_lUname);
            final EditText mPass = (EditText) mView.findViewById(R.id.et_lPass);
            final Button mLogin = (Button) mView.findViewById(R.id.btn_Login);

            final Spinner mSpinner=(Spinner)mView.findViewById(R.id.spinner);



            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=mSpinner.getSelectedItemPosition();
                    String accessLevel;

                    if(position==0)
                    {
                        accessLevel="user";
                    }
                    else
                    {
                        accessLevel="admin";
                    }
                    mLogin.setClickable(false);
                    final ProgressDialog progressDialog = new ProgressDialog(FoodDetailsActivity.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();

                    if (!mUname.getText().toString().isEmpty() && !mPass.getText().toString().isEmpty()) {
                        //login process

                        if(accessLevel.equals("user"))
                        {

                            String uuname=mUname.getText().toString();
                            String upass=mPass.getText().toString();
                            String type="login";
                            String res="";
                            try {
                                res= helperUser.execute(type,uuname,upass).get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }





                            if(res.equals("yes")){

                                new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {
                                                isLoggedIn=true;
                                                dialog.hide();
                                                username=mUname.getText().toString();


                                                Toast.makeText(FoodDetailsActivity.this,"Login Succesful", Toast.LENGTH_SHORT).show();



                                                mLogin.setClickable(true);
                                                progressDialog.dismiss();

                                            }
                                        }, 3000);




                            }
                            else{

                                new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {
                                                Toast.makeText(FoodDetailsActivity.this,"Wrong username / password", Toast.LENGTH_SHORT).show();
                                                mLogin.setClickable(true);
                                                progressDialog.dismiss();



                                            }
                                        }, 3000);

                            }

                        }

                        else
                        {
                            Toast.makeText(FoodDetailsActivity.this, "Please Login as User", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            mLogin.setClickable(true);


                        }



                    } else {
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        Toast.makeText(FoodDetailsActivity.this, "Please fill up the empty fields", Toast.LENGTH_SHORT).show();
                                        mLogin.setClickable(true);
                                        progressDialog.dismiss();


                                    }
                                }, 3000);

                    }
                }
            });



        }
    }

}
