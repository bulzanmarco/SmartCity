package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Domain.CityName;
import com.example.myapplication.Domain.Users;
import com.example.myapplication.Model.UserLogInResponse;
import com.example.myapplication.Model.UserSignUpRequest;
import com.example.myapplication.Services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private EditText firstNameEditText, lastNameEditText, emailEditText, phoneNumberEditText, passwordEditText;
    private Spinner residenceCitySpinner;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        residenceCitySpinner = findViewById(R.id.city_spinner);
        ArrayAdapter<CityName> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,CityName.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        residenceCitySpinner.setAdapter(adapter);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityName cityName=(CityName)residenceCitySpinner.getSelectedItem();
                Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcity.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                ApiService apiService=retrofit.create(ApiService.class);
                UserSignUpRequest userSignUpRequest=new UserSignUpRequest(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(),emailEditText.getText().toString(),phoneNumberEditText.getText().toString(),passwordEditText.getText().toString(),cityName.ordinal());
                Call<UserLogInResponse> call= apiService.signup(userSignUpRequest);
                call.enqueue(new Callback<UserLogInResponse>() {
                    @Override
                    public void onResponse(Call<UserLogInResponse> call, Response<UserLogInResponse> response) {
                        if(response.isSuccessful())
                        {
                            Users.token=response.body().getToken().toString();
                            Users.Email=response.body().getEmail().toString();
                            Intent intent = new Intent(SignUpActivity.this, ReportActivity.class);
                            startActivity(intent);

                        }
                        else
                        {

                        }
                    }

                    @Override
                    public void onFailure(Call<UserLogInResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

}
