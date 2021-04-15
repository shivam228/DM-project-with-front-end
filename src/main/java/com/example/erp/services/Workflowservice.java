package com.example.erp.services;

import com.example.erp.bean.*;
import com.example.erp.dao.WorkflowDAO;
import com.example.erp.dao.impl.WorkflowDAOimpl;

import java.util.List;

public class Workflowservice
{
    public void addworkflow(String name, Designer des,int nevent)
    {
        Workflow w1 = new Workflow();
        w1.setName(name);
        w1.setDesigner(des);
        w1.setNumber_of_events(nevent);
       new WorkflowDAOimpl().addworkflow(w1);
    }
    public void addevent(String name, String desc,Workflow w,int pre,int next,String action)
    {
        Event e1 = new Event();
        e1.setWhat(name);
        e1.setWorkflow(w);
        e1.setNext_event(next);
        e1.setPre_event(pre);
        e1.setAction(action);
        new WorkflowDAOimpl().addevent(e1);
    }
    public Designer getdes(String desname)
    {
       System.out.println("in designer");
        Designer d = new WorkflowDAOimpl().getdesi(desname);
        return d;

    }
    public Workflow getwf(int wfid)
    {

        Workflow w = new WorkflowDAOimpl().getwfo(wfid);
        return w;

    }
    public int geteventcount()
    {

        int x1 = new WorkflowDAOimpl().geteventc();
        return x1;
    }
    public List<Workflow> allworkflow()
    {

        List<Workflow> workflows ;

        workflows= new WorkflowDAOimpl().getworkflows();

        return workflows;

    }
    public void addworkflowinstnace(Workflow w)
    {
        WorkflowInstance w1 = new WorkflowInstance();
        w1.setWorkflow(w);
        new WorkflowDAOimpl().addworkflowinstance(w1);
    }
    public Event geteventid(String tname)
    {

        Event e = new WorkflowDAOimpl().geteventids(tname);
        return e;
    }
    public WorkflowInstance getworkflowins(int wfiid)
    {

        WorkflowInstance wi= new WorkflowDAOimpl().getworkflowinstance(wfiid);
        return wi;
    }
    public List<User> getuserrole(String role)
    {

        List<User> user ;

        user =  new WorkflowDAOimpl().getuserrole(role);

        return user;

    }
    public void addeventinstance(EventInstance ei)
    {

        new WorkflowDAOimpl().addeventins(ei);
    }
    public List<EventInstance> gettaksofuser(int id)
    {

        List<EventInstance> tasks ;

        tasks =  new WorkflowDAOimpl().gettasks(id);

        return tasks;

    }
    public int getuserid(String name)
    {

        int x1 = new WorkflowDAOimpl().getuserid(name);
        return x1;
    }
    public Event geteventbyid(int eid)
    {

        Event e= new WorkflowDAOimpl().geteventbyids(eid);
        return e;
    }
    public void updatestatus(int eiid)
    {

        new WorkflowDAOimpl().updateeventstatus(eiid);
    }
    public int getworkflowinstance(int einsid)
    {

        int x1 = new WorkflowDAOimpl().getwfins(einsid);
        return x1;
    }
    public EventInstance geteventstatus(int evenid,int wfinsid)
    {

        EventInstance e= new WorkflowDAOimpl().geteventinstance(evenid,wfinsid);
        return e;
    }
}
