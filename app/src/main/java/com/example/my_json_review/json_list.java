package com.example.my_json_review;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

public class json_list extends AppCompatActivity {
  private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_list);
        listView=(ListView)findViewById(R.id.json_listId);

        Testjson it=new Testjson();
        it.execute();
    }
    public  class Testjson extends AsyncTask<String,String,List<productModel>>{

        @Override
        protected List<productModel> doInBackground(String... strings) {
            HttpURLConnection httpURLConnection=null;
            BufferedReader bufferedReader=null;
            try {
                URL url=new URL("https://my-sotfwar.000webhostapp.com/myjsonlist.php");
                httpURLConnection= (HttpURLConnection) url.openConnection();
               InputStream inputStream=httpURLConnection.getInputStream();
               bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
               StringBuffer stringBuffer=new StringBuffer();
               String myData=null;
               while ((myData=bufferedReader.readLine())!=null){
                   stringBuffer.append(myData);
                }
               myData=stringBuffer.toString();
               // JSONObject mainjs=new JSONObject(myData);
                JSONArray jsonArray=new JSONArray(myData);
                List<productModel>productModelList=new ArrayList<>();
                for (int i = 0; i <jsonArray.length() ; i++) {
                    JSONObject job=jsonArray.getJSONObject(i);
                    productModel model=new productModel();
                    model.setId(job.getString("id"));
                    model.setName(job.getString("Name"));
                    model.setPrice(job.getString("price"));
                    model.setDetails(job.getString("Details"));
                    productModelList.add(model);
                }
                return productModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<productModel> s) {
            super.onPostExecute(s);
          //  Toast.makeText(json_list.this, ""+s.toString(), Toast.LENGTH_SHORT).show();
            CoustomAdapter adapter=new CoustomAdapter(getApplicationContext(),R.layout.simple_layout,s);
            listView.setAdapter(adapter);
        }
    }
}