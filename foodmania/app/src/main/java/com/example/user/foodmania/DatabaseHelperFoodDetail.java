package com.example.user.foodmania;

import android.content.Context;
import android.os.AsyncTask;

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

/**
 * Created by User on 8/5/2018.
 */

public class DatabaseHelperFoodDetail extends AsyncTask<String,Void,FoodDetails > {
    Context context;
    String JSON_STRING;
    String json_string;
    String ju_fdetailsfastfood;
    String ju_fdetailscolddrink;
    String ju_fdetailshotdrink;
    String ju_fdetailsdessert;

    JSONObject jsonObject;
    JSONArray jsonArray;
    FoodDetails fd;

    public DatabaseHelperFoodDetail(Context context)
    {
        this.context=context;
    }

    @Override
    protected FoodDetails doInBackground(String... params) {
        String type=params[0];
        ju_fdetailsfastfood="http://foodmania98765.000webhostapp.com/gjd_fdetailsfastfood.php";
        ju_fdetailscolddrink="http://foodmania98765.000webhostapp.com/gjd_fdetailscolddrink.php";
        ju_fdetailshotdrink="http://foodmania98765.000webhostapp.com/gjd_fdetailshotdrink.php";
        ju_fdetailsdessert="http://foodmania98765.000webhostapp.com/gjd_fdetailsdessert.php";

        if(type.equals("Fastfood"))
        {
            String foodname=params[1];
            try {

                URL url = new URL(ju_fdetailsfastfood);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(foodname,"UTF-8");
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


                String name;
                fd=new FoodDetails();

                JSONObject JO=jsonArray.getJSONObject(0);
                fd.setName(JO.getString("name"));

                fd.setPrice(JO.getString("price"));

                fd.setPicture(JO.getString("picture"));











                return fd;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if(type.equals("Colddrink"))
        {
            String foodname=params[1];
            try {

                URL url = new URL(ju_fdetailscolddrink);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(foodname,"UTF-8");
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


                String name;
                fd=new FoodDetails();

                JSONObject JO=jsonArray.getJSONObject(0);
                fd.setName(JO.getString("name"));

                fd.setPrice(JO.getString("price"));

                fd.setPicture(JO.getString("picture"));











                return fd;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if(type.equals("Hotdrink"))
        {
            String foodname=params[1];
            try {

                URL url = new URL(ju_fdetailshotdrink);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(foodname,"UTF-8");
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


                String name;
                fd=new FoodDetails();

                JSONObject JO=jsonArray.getJSONObject(0);
                fd.setName(JO.getString("name"));

                fd.setPrice(JO.getString("price"));

                fd.setPicture(JO.getString("picture"));











                return fd;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if(type.equals("Dessert"))
        {
            String foodname=params[1];
            try {

                URL url = new URL(ju_fdetailsdessert);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(foodname,"UTF-8");
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


                String name;
                fd=new FoodDetails();

                JSONObject JO=jsonArray.getJSONObject(0);
                fd.setName(JO.getString("name"));

                fd.setPrice(JO.getString("price"));

                fd.setPicture(JO.getString("picture"));











                return fd;
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
    protected void onPostExecute(FoodDetails result) {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

