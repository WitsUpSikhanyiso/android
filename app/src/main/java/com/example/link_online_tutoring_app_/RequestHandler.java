package com.example.link_online_tutoring_app_;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class RequestHandler extends AsyncTask<String,Void,String> {
    Context context;
    String task;
    String result;

    public RequestHandler(Context context, String task) {
        this.context = context;
        this.task = task;
        result="";
    }

    @Override
    protected String doInBackground(String... strings) {
        switch (task){
            case "login":
                try {
                    URL url=new URL("http://lamp.ms.wits.ac.za/~s1819369/login.php");
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    String name=strings[0];
                    String password=strings[1];
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);     //allows us to use input stream
                    httpURLConnection.setDoOutput(true);    //allows us to use output stream

                    OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

                    String data= URLEncoder.encode("studentNo","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&&"+URLEncoder.encode("password","UTF-8")+"="
                            +URLEncoder.encode(password,"UTF-8");   //note I did not encode '=' and '&&'

                    bufferedWriter.write(data); //send data to the php file
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    InputStream inputStream=httpURLConnection.getInputStream(); //used to get read data from the url
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    String line="";
                    while ((line=bufferedReader.readLine())!=null){
                        //reading the response we got from the php file
                        result+=line;
                    }
                    //the response is stored in result
                    //note that in the case that the response is in jason format you will have to JSON objects

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "register":
                //TODO

                try {
                    URL url=new URL("http://lamp.ms.wits.ac.za/~s1819369/registration.php");
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    String uname=strings[0];
                    String password=strings[1];
                    String email=strings[2];
                    String firstname=strings[3];
                    String lastname=strings[4];
                    String studentnum=strings[5];
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);     //allows us to use input stream
                    httpURLConnection.setDoOutput(true);    //allows us to use output stream

                    OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

                    String data= URLEncoder.encode("studentNo","UTF-8")+"="+URLEncoder.encode(studentnum,"UTF-8")
                            +"&&" +URLEncoder.encode("password","UTF-8")+"=" +URLEncoder.encode(password,"UTF-8")
                            +"&&" +URLEncoder.encode("email","UTF-8")+"=" +URLEncoder.encode(email,"UTF-8")
                            +"&&" +URLEncoder.encode("first_name","UTF-8")+"=" +URLEncoder.encode(firstname,"UTF-8")
                            +"&&" +URLEncoder.encode("last_name","UTF-8")+"=" +URLEncoder.encode(lastname,"UTF-8")
                            +"&&" +URLEncoder.encode("username","UTF-8")+"=" +URLEncoder.encode(uname,"UTF-8");

                    bufferedWriter.write(data); //send data to the php file
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    InputStream inputStream=httpURLConnection.getInputStream(); //used to get read data from the url
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    String line="";
                    while ((line=bufferedReader.readLine())!=null){
                        //reading the response we got from the php file
                        result+=line;
                    }
                    //the response is stored in result
                    //note that in the case that the response is in jason format you will have to JSON objects

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }
        //the result that is returned here is the one in onPostExecute signature
        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        //s is equal to result returned in do in the background method
        switch (s){
            case "data matched":
                //in case login successful
                Toast.makeText(context,"login successful",Toast.LENGTH_LONG).show();
                //TODO go to users home menu
                break;
            case "try again":
                Toast.makeText(context,"login attempt failed",Toast.LENGTH_SHORT).show();
                //TODO handle failed login request like not registered and wrong password
                break;
            case "Registered Successfully":
                Toast.makeText(context,"registration successful",Toast.LENGTH_SHORT).show();
                break;
            case "Something went wrong":
                Toast.makeText(context,"registration not successful",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}