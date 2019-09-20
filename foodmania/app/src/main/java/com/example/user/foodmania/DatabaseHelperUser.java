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
 * Created by User on 8/5/2018.
 */

public class DatabaseHelperUser extends AsyncTask<String,Void,String> {
    Context context;
    DatabaseHelperUser(Context context)
    {
        this.context=context;
    }
    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url= "http://foodmania98765.000webhostapp.com/login_user.php";
        String register_url= "http://foodmania98765.000webhostapp.com/register_user.php";
        if(type.equals("login"))
        {
            try {
                String user_name=params[1];
                String user_pass=params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
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
        else if(type.equals("register"))
        {
            try {

                String user_name=params[1];
                String user_gender=params[2];
                String user_email=params[3];
                String user_uname=params[4];
                String user_pass=params[5];


                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("user_gender","UTF-8")+"="+URLEncoder.encode(user_gender,"UTF-8")+"&"
                        +URLEncoder.encode("user_email","UTF-8")+"="+URLEncoder.encode(user_email,"UTF-8")+"&"
                        +URLEncoder.encode("user_uname","UTF-8")+"="+URLEncoder.encode(user_uname,"UTF-8")+"&"
                        +URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
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
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}

