package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Domain.CityName;
import com.example.myapplication.Model.GetReportsResponse;
import com.example.myapplication.Model.Report;
import com.example.myapplication.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeeReportsActivity extends AppCompatActivity {
    ItemsAdapter adapter1;
    private Spinner CitySpinner;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seereports);

        CitySpinner=findViewById(R.id.city_spinner);
        ArrayAdapter<CityName> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,CityName.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        CitySpinner.setAdapter(adapter);
        CityName cityName=(CityName)CitySpinner.getSelectedItem();
        recyclerView=findViewById(R.id.reports_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcity.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService=retrofit.create(ApiService.class);
        Report report=new Report();
        Call<List<GetReportsResponse>>call=apiService.getreports(cityName);
        //adapter = new ItemsAdapter(this,);
        //adapter.setClickListener(this);
        //recyclerView.setAdapter(adapter);


    }
}
