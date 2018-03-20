package com.example.backend;

public class User {
    public String UserName;
    public String UAddress;
    public String Email;
    public String HashCode;

    public User(String UserName, String UAddress, String Email, String HashCode){
        this.UserName = UserName;
        this.UAddress = UAddress;
        this.Email = Email;
        this.HashCode = HashCode;
    }
}
