package com.example.user.foodmania;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView ivFastfood;
    ImageView ivHotDrink;
    ImageView ivColdDrink;
    ImageView ivDessert;
    Boolean isLoggedIn=false;
    String username="";
    String accessType="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        ivFastfood=(ImageView)findViewById(R.id.ivFastFood);
        ivHotDrink=(ImageView)findViewById(R.id.ivHotDrink);
        ivColdDrink=(ImageView)findViewById(R.id.ivColdDrink);
        ivDessert=(ImageView)findViewById(R.id.ivDessert);


        ivFastfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fastfoodfunction();
            }
        });

        ivHotDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotdrinkfunction();
            }
        });

        ivColdDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colddrinkfunction();
            }
        });

        ivDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dessertfunction();
            }
        });




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_login) {

            Login();


        } else if (id == R.id.nav_signup) {
            Signup();

        }else if (id == R.id.nav_order ){
            order();

        }else if (id == R.id.nav_logout) {
            logout();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void Login()
    {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
        mBuilder.setView(mView);
        final DatabaseHelperUser helperUser=new DatabaseHelperUser(MainActivity.this);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        final EditText mUname = (EditText) mView.findViewById(R.id.et_lUname);
        final EditText mPass = (EditText) mView.findViewById(R.id.et_lPass);
        final Button mLogin = (Button) mView.findViewById(R.id.btn_Login);
        final TextView mProfileDisplay =(TextView)findViewById(R.id.username_display);
        final Spinner mSpinner=(Spinner)mView.findViewById(R.id.spinner);
        final MenuItem menuItemLogin =navigationView.getMenu().getItem(1);
        final MenuItem menuItemSignup =navigationView.getMenu().getItem(2);
        final MenuItem menuItemOrder =navigationView.getMenu().getItem(3);
        final MenuItem menuItemLogout =navigationView.getMenu().getItem(4);




        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=mSpinner.getSelectedItemPosition();
                final String accessLevel;

                if(position==0)
                {
                    accessLevel="user";
                }
                else
                {
                    accessLevel="admin";

                }

                mLogin.setClickable(false);
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
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

                                            dialog.hide();
                                           /* isLoggedIn=true;
                                            username=mUname.getText().toString();
                                            accessType="user";

                                            adapter.setFilter(hnames,isLoggedIn,username,accessType);*/

                                            Toast.makeText(MainActivity.this,"Login Succesful", Toast.LENGTH_SHORT).show();
                                            isLoggedIn=true;
                                            accessType="user";
                                            username=mUname.getText().toString();
                                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                                            drawer.closeDrawer(GravityCompat.START);
                                            menuItemLogin.setVisible(false);
                                           menuItemLogout.setVisible(true);
                                            menuItemSignup.setVisible(false);
                                            menuItemOrder.setVisible(true);

                                            mProfileDisplay.setText(mUname.getText().toString());
                                            mLogin.setClickable(true);
                                            progressDialog.dismiss();

                                        }
                                    }, 3000);




                        }
                        else{

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            Toast.makeText(MainActivity.this,"Wrong username / password", Toast.LENGTH_SHORT).show();
                                            mLogin.setClickable(true);
                                            progressDialog.dismiss();



                                        }
                                    }, 3000);

                        }

                    }


                    else
                    {


                        if(mUname.getText().toString().equals("admin") && mPass.getText().toString().equals("admin")){

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {

                                            dialog.hide();
                                            isLoggedIn=true;
                                            username=mUname.getText().toString();
                                            accessType="admin";



                                            Toast.makeText(MainActivity.this,"Login Succesful", Toast.LENGTH_SHORT).show();
                                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                                            drawer.closeDrawer(GravityCompat.START);
                                            menuItemLogin.setVisible(false);
                                            menuItemLogout.setVisible(true);
                                            menuItemSignup.setVisible(false);
                                           menuItemOrder.setVisible(true);
                                            mProfileDisplay.setText(mUname.getText().toString());
                                            mLogin.setClickable(true);
                                            progressDialog.dismiss();

                                        }
                                    }, 3000);




                        }
                        else{

                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {
                                            Toast.makeText(MainActivity.this,"Wrong username / password", Toast.LENGTH_SHORT).show();
                                            mLogin.setClickable(true);
                                            progressDialog.dismiss();



                                        }
                                    }, 3000);

                        }



                    }



                } else {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Please fill up the empty fields", Toast.LENGTH_SHORT).show();
                                    mLogin.setClickable(true);
                                    progressDialog.dismiss();


                                }
                            }, 3000);

                }
            }
        });

    }

    public void Signup() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_signup, null);
        mBuilder.setView(mView);
        final DatabaseHelperUser helperUser=new DatabaseHelperUser(MainActivity.this);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        final EditText fname = (EditText) mView.findViewById(R.id.et_sname);
        final EditText email = (EditText)mView.findViewById(R.id.et_email);
        final EditText uname = (EditText) mView.findViewById(R.id.et_suname);
        final EditText pass = (EditText) mView.findViewById(R.id.et_spass);
        final EditText cpass = (EditText) mView.findViewById(R.id.et_scpass);
        final RadioGroup rgGender=(RadioGroup) mView.findViewById(R.id.rg_gender);
        final Button mSignup = (Button) mView.findViewById(R.id.btn_signup);


        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=rgGender.getCheckedRadioButtonId();

                final RadioButton rdb_gender=(RadioButton)mView.findViewById(selectedId);
                mSignup.setClickable(false);
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Creating Account...");
                progressDialog.show();
                if (fname.getText().toString().isEmpty() || email.getText().toString().isEmpty() || uname.getText().toString().isEmpty() || pass.getText().toString().isEmpty() || cpass.getText().toString().isEmpty())
                {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {

                                    Toast.makeText(MainActivity.this, "Please fill up the empty fields", Toast.LENGTH_SHORT).show();
                                    mSignup.setClickable(true);
                                    progressDialog.dismiss();
                                    dialog.hide();

                                }
                            }, 3000);


                }
                else
                {
                    if(pass.getText().toString().equals(cpass.getText().toString()))
                    {
                        if(android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())

                        {
                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {



                                            String fullname=fname.getText().toString();
                                            String ugender=rdb_gender.getText().toString();
                                            String uemail=email.getText().toString();
                                            String uuname=uname.getText().toString();
                                            String upass=pass.getText().toString();
                                            String type="register";
                                            String res="";
                                            try {
                                                res= helperUser.execute(type,fullname,ugender,uemail,uuname,upass).get();
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            } catch (ExecutionException e) {
                                                e.printStackTrace();
                                            }
                                            if(res.equals("yes")) {
                                                Toast.makeText(MainActivity.this,"Account created", Toast.LENGTH_SHORT).show();

                                                mSignup.setClickable(true);
                                                progressDialog.dismiss();
                                                dialog.hide();
                                                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                                                drawer.closeDrawer(GravityCompat.START);
                                            }
                                            else
                                            {
                                                Toast.makeText(MainActivity.this,"Account not created", Toast.LENGTH_SHORT).show();

                                                mSignup.setClickable(true);
                                                progressDialog.dismiss();
                                                dialog.hide();
                                                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                                                drawer.closeDrawer(GravityCompat.START);
                                            }




                                        }
                                    }, 3000);

                        }
                        else
                        {
                            new android.os.Handler().postDelayed(
                                    new Runnable() {
                                        public void run() {

                                            Toast.makeText(MainActivity.this,"Enter a valid email address", Toast.LENGTH_SHORT).show();
                                            mSignup.setClickable(true);
                                            progressDialog.dismiss();

                                        }
                                    }, 3000);

                        }


                    }
                    else
                    {
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Passwords don't match..Re-enter the passwords", Toast.LENGTH_SHORT).show();
                                        mSignup.setClickable(true);
                                        progressDialog.dismiss();
                                    }
                                }, 3000);


                    }


                }



            }
        });

    }

    public void logout()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final MenuItem menuItemLogin=navigationView.getMenu().getItem(1);
        final MenuItem menuItemSignup=navigationView.getMenu().getItem(2);

        final MenuItem menuItemOrder =navigationView.getMenu().getItem(3);
        final MenuItem menuItemLogout =navigationView.getMenu().getItem(4);

        menuItemLogin.setVisible(true);
        menuItemSignup.setVisible(true);
        menuItemLogout.setVisible(false);
        menuItemOrder.setVisible(false);


        final TextView mProfileDisplay =(TextView)findViewById(R.id.username_display);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging Out...");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        mProfileDisplay.setText("");
                        isLoggedIn=false;
                        username="";
                        Toast.makeText(MainActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }
    public void fastfoodfunction()
    {
            String type="Fastfood";
        Intent intent=new Intent(MainActivity.this,MenuListActivity.class);
        intent.putExtra("MenuType",type);
        intent.putExtra("LoginStatus",isLoggedIn);
        intent.putExtra("Username",username);
        startActivity(intent);


    }
    public void hotdrinkfunction()
    {
        String type="Hotdrink";
        Intent intent=new Intent(MainActivity.this,MenuListActivity.class);
        intent.putExtra("MenuType",type);
        intent.putExtra("LoginStatus",isLoggedIn);
        intent.putExtra("Username",username);
        startActivity(intent);


    }
    public void colddrinkfunction()
    {
        String type="Colddrink";
        Intent intent=new Intent(MainActivity.this,MenuListActivity.class);
        intent.putExtra("MenuType",type);
        intent.putExtra("LoginStatus",isLoggedIn);
        intent.putExtra("Username",username);
        startActivity(intent);


    }
    public void dessertfunction()
    {
        String type="Dessert";
        Intent intent=new Intent(MainActivity.this,MenuListActivity.class);
        intent.putExtra("MenuType",type);
        intent.putExtra("LoginStatus",isLoggedIn);
        intent.putExtra("Username",username);
        startActivity(intent);


    }
    public void order()
    {

        Intent intent=new Intent(MainActivity.this,OrderDetailActivity.class);

        intent.putExtra("Username",username);
        intent.putExtra("AccessType",accessType);
        startActivity(intent);



    }

}
