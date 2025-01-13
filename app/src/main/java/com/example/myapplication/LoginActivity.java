package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.Domain.Token;
import com.example.myapplication.Model.UserLogInRequest;
import com.example.myapplication.Model.UserLogInResponse;
import com.example.myapplication.Services.ApiService;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailEditText.getText().toString().isEmpty() ||passwordEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"Eroare, completati email si parola",Toast.LENGTH_LONG).show();
                    return;
                }
                Retrofit retrofit=new Retrofit.Builder().baseUrl("https://smartcity.azurewebsites.net/").addConverterFactory(GsonConverterFactory.create()).build();
                ApiService apiService=retrofit.create(ApiService.class);
                UserLogInRequest userLogInRequest=new UserLogInRequest(emailEditText.getText().toString(),passwordEditText.getText().toString());
                Call<UserLogInResponse> call= apiService.login(userLogInRequest);
                call.enqueue(new Callback<UserLogInResponse>() {
                    @Override
                    public void onResponse(Call<UserLogInResponse> call, Response<UserLogInResponse> response) {
                        if(response.isSuccessful())
                        {
                            //Log.d("fdsafs",response.body().getToken().toString());
                            Token.token=response.body().getToken().toString();
                            Intent intent = new Intent(LoginActivity.this, ReportActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Eroare, email sau parola gresita",Toast.LENGTH_LONG).show();
                            return;
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
