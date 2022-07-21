package com.iteyes.placesproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
public class wether extends AppCompatActivity {
     Button btn_loc, btn_back;
     EditText city_edit;
     String city;
     TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wether);
    btn_loc = (Button) findViewById(R.id.btn_wether);
    btn_back = (Button) findViewById(R.id.btn_back_dashboard);
        city_edit = (EditText) findViewById(R.id.weather_textField);
        tv = (TextView) findViewById(R.id.weather_results);

    btn_loc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            city=city_edit.getText().toString();

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = "https://api.openweathermap.org/data/2.5/weather?q="+city.toLowerCase()+"&appid=5e28d65104c7faf4af9f4820c04835ef";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {


                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONObject array= (JSONObject) response.get("main");
                        JSONObject sys= (JSONObject) response.get("sys");
                        JSONArray weather_details_array = (JSONArray) response.get("weather");
                        JSONObject weather_json = (JSONObject) weather_details_array.get(0);
                        //JSONArray array=  response.getJSONArray("main");
                        WeatherPojo wp = new WeatherPojo();

                        String temp= array.getString("temp");
                        String temp_max= array.getString("temp_max");
                        String humidity = array.getString("humidity");
                        String country = sys.getString("country");
                        String weather_main = weather_json.getString("main");
                        String weather_desp = weather_json.getString("description");

                        //POJOs
                        wp.setTemp(temp);
                        wp.setTemp_max(temp_max);
                        wp.setHumidity(humidity);
                        wp.setCountry(country);
                        wp.setMain(weather_main);
                        wp.setDescription(weather_desp);

                        String res = "Temperature: " + temp + "F\n Temperature Max: "+temp_max+"F\n Humidity: "+humidity + "\n Country: "+ country+"\n Expected weather: "
                                +weather_main+"\n weather Description: "+weather_desp;
                        tv.setText(res);
                        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        tv.setText(e.getMessage());
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    tv.setText("NO City Found. Try again");
                    Toast.makeText(getApplicationContext(),"NO City Found. Try again",Toast.LENGTH_SHORT).show();
                }
            });

            queue.add(request);
        }

    });

    btn_back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(wether.this, dashboard.class));
        }
    });
    }

}