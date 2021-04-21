package com.example.erp.services;

import com.example.erp.dao.impl.UserDAOimpl1;

public class RegistrationService {
    public String registerUser(String username,String password, String role)
    {
        System.out.println("service");
        String ans = new UserDAOimpl1().registerUser(username, password, role);
        return ans;
    }
    public String registerDesigner(String username,String password)
    {
        String ans = new UserDAOimpl1().registerDesigner(username,password);
        return ans;
    }
}
