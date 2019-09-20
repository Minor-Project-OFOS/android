package com.example.user.foodmania;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by User on 8/6/2018.
 */

public class DatabaseHelperOrderDetail extends AsyncTask<String,Void,ArrayList<OrderDetail> > {
    Context context;
    String JSON_STRING;
    String json_string;
    String ju_odetailsuser;
    String ju_odetailsadmin;
    String ju_fdetailshotdrink;
    String ju_fdetailsdessert;

    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<OrderDetail> odd;
    OrderDetail orderDetail;
     static final String TAG="DatabaseHelper";

    public DatabaseHelperOrderDetail(Context context)
    {
        this.context=context;
    }

    @Override
    protected ArrayList<OrderDetail> doInBackground(String... params) {
        String type=params[0];
        ju_odetailsuser="http://foodmania98765.000webhostapp.com/gjd_odetailsuser.php";
        ju_odetailsadmin="http://foodmania98765.000webhostapp.com/gjd_odetailsadmin.php";



        if(type.equals("user"))
        {
            String uname=params[1];
            try {

                URL url = new URL(ju_odetailsuser);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("uname","UTF-8")+"="+URLEncoder.encode(uname,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((JSON_STRING=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING+"\n");

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                json_string=stringBuilder.toString().trim();

                jsonObject=new JSONObject(json_string);
                jsonArray=jsonObject.getJSONArray("server_response");

                odd=new ArrayList<>();








                for(int count=0;count<jsonArray.length();count++)
                {


                    orderDetail=new OrderDetail();
                    JSONObject JO=jsonArray.getJSONObject(count);




                    orderDetail.setFoodname(JO.getString("foodname"));
                    orderDetail.setUname(JO.getString("username"));
                    orderDetail.setFname(JO.getString("fullname"));
                    orderDetail.setLocation(JO.getString("location"));
                    orderDetail.setNumber(JO.getString("number"));
                    orderDetail.setQuantity(JO.getString("quantity"));
                    orderDetail.setTotal(JO.getString("total"));
                    odd.add(orderDetail);






                }












                return odd;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if(type.equals("admin"))
        {

            try {

                URL url = new URL(ju_odetailsadmin);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();



                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((JSON_STRING=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING+"\n");

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                json_string=stringBuilder.toString().trim();



                jsonObject=new JSONObject(json_string);
                jsonArray=jsonObject.getJSONArray("server_response");

                odd=new ArrayList<>();


                for(int count=0;count<jsonArray.length();count++)
                { orderDetail=new OrderDetail();
                    JSONObject JO=jsonArray.getJSONObject(count);
                    orderDetail.setFoodname(JO.getString("foodname"));
                    orderDetail.setUname(JO.getString("username"));
                    orderDetail.setFname(JO.getString("fullname"));
                    orderDetail.setLocation(JO.getString("location"));
                    orderDetail.setNumber(JO.getString("number"));
                    orderDetail.setQuantity(JO.getString("quantity"));
                    orderDetail.setTotal(JO.getString("total"));

                    odd.add(orderDetail);






                }











                return odd;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<OrderDetail> result) {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}


