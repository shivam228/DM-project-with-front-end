package com.example.erp.dao.impl;

import com.example.erp.bean.Designer;
import com.example.erp.bean.User;
import com.example.erp.dao.UserDAO;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;



public class UserDAOimpl1 implements UserDAO
{
    @Override
    public String registerDesigner(String user, String pass){
        Designer designer = new Designer();
        designer.setName(user);
        designer.setPasswd(pass);
        try{
            Session session = SessionUtil.getSession();
            session.beginTransaction();

            session.persist(designer);
            session.getTransaction().commit();
            session.close();

            return "Success";
        }
        catch (Exception e){
            return "Failure";
        }

    }
    @Override
    public String registerUser(String user, String pass, String role){
        User user1 = new User();
        user1.setPasswd(pass);
        user1.setName(user);
        user1.setRole(role);
        try
        {
            Session session = SessionUtil.getSession();
            session.beginTransaction();

            session.persist(user1);
            session.getTransaction().commit();
            session.close();

            return "Success";
        }
        catch (Exception e){
            return "Failure";
        }

    }
    @Override
    public String login(String user, String pass)
    {
        String ans = null;
        try{
            Session session = SessionUtil.getSession();
            session.beginTransaction();
            Query query = session.createQuery("select d1.passwd from Designer d1 where d1.name =:users");
            query.setParameter("users", user);
            int siz = query.list().size();
            if(siz==0)
             ans = "failure";
            else
             ans = (String) query.getSingleResult();
            System.out.println(ans);
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e) {
            System.out.println("no user found");
        }
     finally {
            if (ans.equals(pass)) {
                return "success";
            } else
                return "failure";
        }

    }

    @Override
    public String login1(String user, String pass)
    {
        String ans;
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("select u1.passwd from User u1 where u1.name =:users");
        query.setParameter("users",user);
        int siz = query.list().size();
        if(siz==0)
            ans = "failure";
        else
        ans = (String) query.getSingleResult();
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
