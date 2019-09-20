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
import java.util.ArrayList;

/**
 * Created by User on 8/5/2018.
 */

public class DatabaseHelperMenuList  extends AsyncTask<String,Void,ArrayList<String>> {
    Context context;
    String JSON_STRING;
    String json_string;
    String ju_fnamesfastfood;
    String ju_fnamescolddrink;
    String ju_fnameshotdrink;
    String ju_fnamesdessert;

    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<String> fnames;


    public DatabaseHelperMenuList(Context context)
    {
        this.context=context;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        String type=params[0];
        ju_fnamesfastfood="http://foodmania98765.000webhostapp.com/gjd_fname.php";
        ju_fnamescolddrink="http://foodmania98765.000webhostapp.com/gjd_fnamecolddrink.php";
        ju_fnameshotdrink="http://foodmania98765.000webhostapp.com/gjd_fnamehotdrink.php";
        ju_fnamesdessert="http://foodmania98765.000webhostapp.com/gjd_fnamedessert.php";



        if(type.equals("Fastfood"))
        {
            try {
                URL url = new URL(ju_fnamesfastfood);

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


                String name;
                fnames=new ArrayList<>();
                for(int count=0;count<jsonArray.length();count++)
                {
                    JSONObject JO=jsonArray.getJSONObject(count);
                    name=JO.getString("name");
                    fnames.add(name);







                }

                return fnames;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }







        } else  if(type.equals("Hotdrink"))
        {
            try {
                URL url = new URL(ju_fnameshotdrink);

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


                String name;
                fnames=new ArrayList<>();
                for(int count=0;count<jsonArray.length();count++)
                {
                    JSONObject JO=jsonArray.getJSONObject(count);
                    name=JO.getString("name");
                    fnames.add(name);






                }

                return fnames;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }







        }  else  if(type.equals("Colddrink"))
        {
            try {
                URL url = new URL(ju_fnamescolddrink);

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


                String name;
                fnames=new ArrayList<>();
                for(int count=0;count<jsonArray.length();count++)
                {
                    JSONObject JO=jsonArray.getJSONObject(count);
                    name=JO.getString("name");
                    fnames.add(name);






                }

                return fnames;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }







        }  else  if(type.equals("Dessert"))
        {
            try {
                URL url = new URL(ju_fnamesdessert);

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


                String name;
                fnames=new ArrayList<>();
                for(int count=0;count<jsonArray.length();count++)
                {
                    JSONObject JO=jsonArray.getJSONObject(count);
                    name=JO.getString("name");
                    fnames.add(name);






                }

                return fnames;
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
    protected void onPostExecute(ArrayList<String > result) {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
