package com.example.erp.dao.impl;

import com.example.erp.bean.*;
import com.example.erp.dao.WorkflowDAO;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class WorkflowDAOimpl implements WorkflowDAO
{
    @Override
    public void addworkflow(Workflow w)
    {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            session.save(w);
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addevent(Event e1)
    {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            session.save(e1);
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public Designer getdesi(String name)
    {
        Session session = SessionUtil.getSession();

        session.beginTransaction();
        Query query = session.createQuery("from Designer d1 where d1.name =:users");
        query.setParameter("users",name);
        Designer d = (Designer) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        System.out.println("designer name");
        System.out.println(d.getName());

        return d;
    }

    @Override
    public Workflow getwfo(int wfid) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Workflow w where w.id =:wfid");
        query.setParameter("wfid",wfid);
        Workflow wo = (Workflow) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return wo;
    }

    @Override
    public int geteventc() {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query queryx = session.createQuery("SELECT E.id FROM Event E");
        List countevents = ((org.hibernate.query.Query<?>) queryx).list();
        int x1 = countevents.size();
        return x1;
    }

    @Override
    public List<Workflow> getworkflows()
    {
        Session session = SessionUtil.getSession();

        session.beginTransaction();
        TypedQuery<Workflow> query = session.createQuery("from Workflow ");
        List<Workflow> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void addworkflowinstance(WorkflowInstance w)
    {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            session.save(w);
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public Event geteventids(String tname) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Event e where e.what =:tname");
        query.setParameter("tname",tname);
        Event e1 = (Event) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return e1;
    }

    @Override
    public WorkflowInstance getworkflowinstance(int wfiid) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from WorkflowInstance e where e.id =:wfiid");
        query.setParameter("wfiid",wfiid);
        WorkflowInstance e1 = (WorkflowInstance) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return e1;
    }

    @Override
    public List<User> getuserrole(String role)
    {
        Session session = SessionUtil.getSession();

        session.beginTransaction();
        TypedQuery<User> query = session.createQuery("from User u where u.role =:role");
        query.setParameter("role",role);
        List<User> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void addeventins(EventInstance ei)
    {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            session.save(ei);
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<EventInstance> gettasks(int id)
    {
        Session session = SessionUtil.getSession();

        session.beginTransaction();
        TypedQuery<EventInstance> query = session.createQuery("from EventInstance ei where ei.user.id =:id");
        query.setParameter("id",id);
        List<EventInstance> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public int getuserid(String name) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("select u.id from User u  where u.name =:name");
        query.setParameter("name",name);
        int id= (int)query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public Event geteventbyids(int eid) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Event e where e.id =:eid");
        query.setParameter("eid",eid);
        Event e1 = (Event) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return e1;
    }

    @Override
    public void updateeventstatus(int eiid)
    {
        try(Session session = SessionUtil.getSession())
        {
            session.beginTransaction();
            TypedQuery<EventInstance> query = session.createQuery("update EventInstance set status=:sta where id=:eiid");
            query.setParameter("sta", "completed");
            query.setParameter("eiid", eiid);
            int modifications=query.executeUpdate();
            System.out.println(modifications);
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getwfins(int einsid) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("select u.workflowInstance.id from EventInstance u  where u.id =:einsid");
        query.setParameter("einsid",einsid);
        int id= (int)query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public EventInstance geteventinstance(int evenid, int wfinsid) {
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        TypedQuery<EventInstance> query20;
        query20 = session.createQuery("FROM EventInstance ei  where ei.event.id =:evenid and ei.workflowInstance.id=:wfinsid");
        query20.setParameter("evenid", evenid);
        query20.setParameter("wfinsid", wfinsid);
        EventInstance evx = (EventInstance) query20.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return evx;
    }

}
