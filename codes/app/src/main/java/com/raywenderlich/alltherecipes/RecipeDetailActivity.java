package com.raywenderlich.alltherecipes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeDetailActivity extends AppCompatActivity {
    private WebView mWebView;
    private Button button;
    private ProgressDialog pDialog;
    private static String url = "http://learnsecurity.club/restaurant/5/menu/540/JSON";
    public TextView text1;
    public TextView text2;
    public TextView text3;
    ArrayList<HashMap<String, String>> contactList;
    String id ;
    String name ;
    String price ;
    String description ;
    String gender ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        final Context context = this;
        contactList = new ArrayList<>();

        //lv = (ListView) findViewById(R.id.list);

        String title = this.getIntent().getExtras().getString("title");
        text1=(TextView)findViewById(R.id.tv_house_name);
        text1.setText(title);
        text2=(TextView)findViewById(R.id.tv_owner_name);
        text3=(TextView)findViewById(R.id.tv_house_addr);

        //String url = this.getIntent().getExtras().getString("url");

// 2
        setTitle(title);

// 3
        //mWebView = (WebView) findViewById(R.id.detail_web_view);

// 4
        //mWebView.loadUrl(url);
        button = (Button) findViewById(R.id.button);
        new GetContacts().execute();

    }

    public void getDirections(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=" + 33.773692 + "," + -84.397800));
        startActivity(intent);
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);



            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject json1= jsonObj.getJSONObject("Menu_Item");
                        id = json1.getString("id");
                        name = json1.getString("name");
                        price = json1.getString("price");
                        description = json1.getString("description");
                        gender = json1.getString("course");
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(RecipeDetailActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            text2.setText(description);
            text3.setText(price);

        }
    }
}
