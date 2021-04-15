package com.example.erp.services;

import com.example.erp.dao.impl.UserDAOimpl1;

public class LoginService
{
    public String login(String username,String password)
    {
        System.out.println("service");
        String ans = new UserDAOimpl1().login(username,password);
        return ans;
    }
    public String login1(String username,String password)
    {
        String ans = new UserDAOimpl1().login1(username,password);
        return ans;
    }
}
