package com.example.myapplication;

import static com.example.myapplication.Domain.Token.token;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Domain.Adress;
import com.example.myapplication.Domain.CityName;
import com.example.myapplication.Domain.ProblemType;
import com.example.myapplication.Domain.Streets;
import com.example.myapplication.Model.CreateReportRequest;
import com.example.myapplication.Model.GetReportsResponse;
import com.example.myapplication.Services.ApiService;

import java.util.ArrayList;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddReportActivity extends AppCompatActivity {
    private EditText description,emailText;
    private Spinner problemSpinner,citySpinner,streetSpinner;
    private Button doneButton;
    private String x;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreports);
        description=findViewById(R.id.description);
        emailText=findViewById(R.id.emailEditText);
        problemSpinner=findViewById(R.id.problem_spinner);
        ArrayAdapter<ProblemType> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,ProblemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        problemSpinner.setAdapter(adapter);
        citySpinner= findViewById(R.id.city_spinner);
        ArrayAdapter<CityName> adapter2= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,CityName.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        citySpinner.setAdapter(adapter2);
        streetSpinner=findViewById(R.id.street_spinner);
        ArrayAdapter<CityName> adapter3= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,CityName.values());
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
        streetSpinner.setAdapter(adapter3);
        doneButton=findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityName cityName=(CityName)citySpinner.getSelectedItem();
                ProblemType problemType=(ProblemType) problemSpinner.getSelectedItem();
                Streets streets=(Streets)streetSpinner.getSelectedItem();
                Adress addresProblem=new Adress(cityName,streets);
                Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcity.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                ApiService apiService=retrofit.create(ApiService.class);
                CreateReportRequest createReportRequest=new CreateReportRequest(emailText.toString().toString(),description.getText().toString(),addresProblem,problemType.ordinal());
                Call<GetReportsResponse> call=apiService.ReportProblem(createReportRequest);
                Request update=call.request().newBuilder().addHeader("Authorization","Bearer "+ token).build();
                call=call.clone();
                call.enqueue(new Callback<GetReportsResponse>() {
                    @Override
                    public void onResponse(Call<GetReportsResponse> call, Response<GetReportsResponse> response) {

                        Intent intent = new Intent(AddReportActivity.this, ReportActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<GetReportsResponse> call, Throwable t) {

                    }
                });


            }
        });

    }

}
