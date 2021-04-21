package com.example.erp.dao;

public interface UserDAO
{
    public abstract String login(String user,String pass);
    public abstract String login1(String user,String pass);

    public abstract String registerUser(String user, String pass, String role);
    public abstract String registerDesigner(String user, String pass);
}
