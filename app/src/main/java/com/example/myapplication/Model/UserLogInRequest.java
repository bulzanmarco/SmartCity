package com.example.myapplication.Model;

public class UserLogInRequest {

    public String GetEmail()
    {
        return email;
    }
    public String GetPassword()
    {
        return password;
    }
    public void SetEmail(String email)
    {
        this.email=email;
    }
    public void SetPassword(String password)
    {
        this.password=password;
    }
    public UserLogInRequest(String email,String password)
    {
        this.email=email;
        this.password=password;
    }
    private String email;
    private String password;
}
