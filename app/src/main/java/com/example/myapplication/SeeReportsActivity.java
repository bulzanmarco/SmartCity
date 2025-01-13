package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Domain.CityName;
import com.example.myapplication.Model.GetReportsResponse;
import com.example.myapplication.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeeReportsActivity extends AppCompatActivity {

    private Spinner CitySpinner;
    private RecyclerView recyclerView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seereports);

        CitySpinner=findViewById(R.id.city_spinner);
        ArrayAdapter<CityName> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,CityName.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        CitySpinner.setAdapter(adapter);

        recyclerView=findViewById(R.id.reports_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CityName cityName=(CityName)CitySpinner.getSelectedItem();
                Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcity.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                ApiService apiService=retrofit.create(ApiService.class);
                Log.d("2-Cityname request",String.valueOf(cityName.ordinal()));
                Call<List<GetReportsResponse>> call=apiService.getReports(cityName.ordinal());
                call.enqueue(new Callback<List<GetReportsResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetReportsResponse>> call, Response<List<GetReportsResponse>> response) {
                        for (GetReportsResponse getReportsResponse:response.body()
                             ) {
                            Log.d("10",getReportsResponse.toString());
                        }
                        ItemsAdapter adapter1=new ItemsAdapter(response.body(),SeeReportsActivity.this);
                        recyclerView.setAdapter(adapter1);
                    }

                    @Override
                    public void onFailure(Call<List<GetReportsResponse>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
