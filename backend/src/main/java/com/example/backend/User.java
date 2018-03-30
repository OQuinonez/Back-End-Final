package com.example.backend;

public class User {
    public Integer UserId;
    public String UserName;
    public String UAddress;
    public String Email;
    public String pass_word;
    public String session_key;
    public User() {}
    public User(Integer UserId, String UserName, String UAddress, String Email, String pass_word, String session_key){
        this.UserId = UserId;
        this.UserName = UserName;
        this.UAddress = UAddress;
        this.Email = Email;
        this.pass_word = pass_word;
        this.session_key = session_key;
    }
}
