package com.example.user.foodmania;

import android.content.Context;
import android.os.AsyncTask;

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
 * Created by User on 8/6/2018.
 */

public class DatabaseHelperOrder  extends AsyncTask<String,Void,String> {
    Context context;
    DatabaseHelperOrder(Context context)
    {
        this.context=context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];

        String register_order_url= "http://foodmania98765.000webhostapp.com/register_order.php";
        if(type.equals("order"))
        {
            try {
                String foodname=params[1];
                String user_name=params[2];
                String user_fname=params[3];
                String location=params[4];
                String number=params[5];
                String quantity=params[6];
                String total=params[7];


                URL url = new URL(register_order_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("foodname","UTF-8")+"="+URLEncoder.encode(foodname,"UTF-8")+"&"
                        +URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("user_fname","UTF-8")+"="+URLEncoder.encode(user_fname,"UTF-8")+"&"
                        +URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(location,"UTF-8")+"&"
                        +URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(number,"UTF-8")+"&"
                        +URLEncoder.encode("quantity","UTF-8")+"="+URLEncoder.encode(quantity,"UTF-8")+"&"
                        +URLEncoder.encode("total","UTF-8")+"="+URLEncoder.encode(total,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line =bufferedReader.readLine())!=null)
                {
                    result +=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

