package com.example.erp.dao;

import com.example.erp.bean.*;

import java.util.List;

public interface WorkflowDAO
{
    public abstract void addworkflow(Workflow w);
    public abstract void addevent(Event e);
    public abstract Designer getdesi(String name);
    public abstract Workflow getwfo(int wfid);
    public abstract int geteventc();
    public abstract List<Workflow> getworkflows();
    public abstract void addworkflowinstance(WorkflowInstance w);
    public abstract Event geteventids(String tname);
    public abstract WorkflowInstance getworkflowinstance(int wfiid);
    public abstract List<User> getuserrole(String role);
    public abstract void addeventins(EventInstance ei);
    public abstract List<EventInstance> gettasks(int id);
    public abstract int getuserid(String name);
    public abstract Event geteventbyids(int eid);
    public abstract void updateeventstatus(int eiid);
    public abstract int getwfins(int einsid);
    public abstract EventInstance geteventinstance(int evenid,int wfinsid);
}
