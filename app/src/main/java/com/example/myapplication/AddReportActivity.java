package com.example.myapplication;

import static com.example.myapplication.Domain.Streets.CITY_STREETS;
import static com.example.myapplication.Domain.Users.token;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Domain.Adress;
import com.example.myapplication.Domain.CityName;
import com.example.myapplication.Domain.ProblemType;
import com.example.myapplication.Domain.Streets;
import com.example.myapplication.Domain.Users;
import com.example.myapplication.Model.CreateReportRequest;
import com.example.myapplication.Model.GetReportsResponse;
import com.example.myapplication.Services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddReportActivity extends AppCompatActivity {
    private EditText description;
    private TextView emailTextView2;
    private Spinner problemSpinner,citySpinner,streetSpinner;
    private Button doneButton;
    private String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreports);
        description=findViewById(R.id.description);
        emailTextView2=findViewById(R.id.emailTextView2);
        emailTextView2.setText(Users.Email);
        problemSpinner=findViewById(R.id.problem_spinner);
        ArrayAdapter<ProblemType> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,ProblemType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        problemSpinner.setAdapter(adapter);
        streetSpinner=findViewById(R.id.street_spinner);
        streetSpinner.setEnabled(false);
        citySpinner= findViewById(R.id.city_spinner);
        ArrayAdapter<CityName> adapter2= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,CityName.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        citySpinner.setAdapter(adapter2);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CityName city=(CityName)parent.getSelectedItem();
                ArrayAdapter<Streets> adapter3= new ArrayAdapter<>(AddReportActivity.this, android.R.layout.simple_spinner_item,CITY_STREETS.get(city));
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);
                streetSpinner.setAdapter(adapter3);
                streetSpinner.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                streetSpinner.setEnabled(false);
            }
        });

        doneButton=findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityName cityName=(CityName)citySpinner.getSelectedItem();
                ProblemType problemType=(ProblemType) problemSpinner.getSelectedItem();

                Streets streets=(Streets)streetSpinner.getSelectedItem();
                Adress addresProblem=new Adress(cityName.ordinal(),streets.ordinal());
                Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcity.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                ApiService apiService=retrofit.create(ApiService.class);
                CreateReportRequest createReportRequest=new CreateReportRequest(emailTextView2.getText().toString(),description.getText().toString(),addresProblem,problemType.ordinal());
                Call<GetReportsResponse> call=apiService.reportProblem("Bearer "+token,createReportRequest);
                Log.d("321312",createReportRequest.toString());
                call.enqueue(new Callback<GetReportsResponse>() {
                    @Override
                    public void onResponse(Call<GetReportsResponse> call, Response<GetReportsResponse> response) {
                        if(response.isSuccessful()) {

                            Intent intent = new Intent(AddReportActivity.this, ReportActivity.class);
                            startActivity(intent);
                        }
                        else
                        {

                            Log.d("31251", String.valueOf(response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<GetReportsResponse> call, Throwable t) {
                    }
                });


            }
        });

    }

}
