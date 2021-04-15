package com.example.erp.dao.impl;

import com.example.erp.bean.User;
import com.example.erp.dao.UserDAO;
import com.example.erp.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpl1 implements UserDAO
{
    @Override
    public String login(String user, String pass)
    {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("select d1.passwd from Designer d1 where d1.name =:users");
        query.setParameter("users",user);
        String ans = (String) query.getSingleResult();
        System.out.println(ans);
        session.getTransaction().commit();
        session.close();
        if(ans.equals(pass))
        {
            return "success";
        }
        else
            return "failure";


    }

    @Override
    public String login1(String user, String pass)
    {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("select u1.passwd from User u1 where u1.name =:users");
        query.setParameter("users",user);
        String ans = (String) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        if(ans.equals(pass))
        {
            return "success";
        }
        else
            return "failure";
    }
}
