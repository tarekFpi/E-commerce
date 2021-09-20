package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.text_json_data);
        jsonClass it=new jsonClass();
       it.execute();
    }
    public class  jsonClass extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection httpsURLConnection=null;
            BufferedReader bufferedReader=null;
            String name;
            String roll;
            String address;
            try {
                URL url=new URL("https://utc-softwar.000webhostapp.com/json_data.txt");
                httpsURLConnection= (HttpsURLConnection) url.openConnection();
                InputStream inputStreamReader=httpsURLConnection.getInputStream();
              bufferedReader=new BufferedReader(new InputStreamReader(inputStreamReader));
              StringBuffer sb=new StringBuffer();
              StringBuffer sb2=new StringBuffer();
              String line;
              while((line=bufferedReader.readLine())!=null){
                sb.append(line);
              }
              String myfile=sb.toString();
                JSONObject jsonMain=new JSONObject(myfile);
                JSONArray jsonArray=jsonMain.getJSONArray("myData");

                for (int i=0; i<jsonArray.length();i++){

                    JSONObject json=jsonArray.getJSONObject(i);
                    name=json.getString("Name");
                    roll=json.getString("Roll");
                    address=json.getString("Address");
                    sb2.append("Name:"+name+"\nRoll:"+roll+"\nAddress:"+address+"\n-----------\n");
                }
                return sb2.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                httpsURLConnection.disconnect();;
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(""+s);
        }
    }
}