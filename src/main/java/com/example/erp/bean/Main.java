package com.example.erp.bean;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {
    public static void main(String args[])
    {
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory=meta.getSessionFactoryBuilder().build();
        Session session=factory.openSession();
        Transaction t=session.beginTransaction();

        //Create designer.
        Designer designer1 = new Designer();
        designer1.setName("vaibhavi");
        designer1.setPasswd("abcd");


        //Create users.
        User user1 = new User();
        user1.setPasswd("abcd");
        user1.setName("user1");
        user1.setRole("admin");

        User user2 = new User();
        user2.setPasswd("abcd");
        user2.setName("user2");
        user2.setRole("manager");

        User user3 = new User();
        user3.setPasswd("abcd");
        user3.setName("user3");
        user3.setRole("developer");

        User user4 = new User();
        user4.setPasswd("abcd");
        user4.setName("user4");
        user4.setRole("tester");

        User user5 = new User();
        user5.setPasswd("abcd");
        user5.setName("user5");
        user5.setRole("ops");



        //Designer will design workflow.
        Workflow workflow1 = new Workflow();
        workflow1.setName("Loan Application");
        workflow1.setNumber_of_events(3);
        workflow1.setDesigner(designer1);

        //Create events.
        Event e1 = new Event();
        e1.setPre_event(-1);
        e1.setNext_event(2);
        e1.setAction("accept,reject");
        e1.setWhat("Review");
        e1.setWorkflow(workflow1);

        Event e2 = new Event();
        e2.setWhat("Approve/Reject");
        e2.setAction("accept,reject,hold");
        e2.setNext_event(-1);
        e2.setPre_event(1);
        e2.setWorkflow(workflow1);




        //User will instantiate the workflow.
        WorkflowInstance workflowInstance1 = new WorkflowInstance();
        workflowInstance1.setWorkflow(workflow1);

//        WorkflowInstance workflowInstance2= new WorkflowInstance();
//        workflowInstance1.setUsers(user1);
//        workflowInstance1.setWorkflow(workflow1);

        //Create event instance.
        EventInstance eventInstance1 = new EventInstance();
        eventInstance1.setWorkflowInstance(workflowInstance1);
        eventInstance1.setUser(user1);
        eventInstance1.setEvent(e1);
        eventInstance1.setstatus("pending");

        EventInstance eventInstance2 = new EventInstance();
        eventInstance2.setWorkflowInstance(workflowInstance1);
        eventInstance2.setUser(user1);
        eventInstance2.setEvent(e2);
        eventInstance2.setstatus("pending");

//        EventInstance eventInstance3 = new EventInstance();
//        eventInstance3.setWorkflowInstance(workflowInstance2);
//        eventInstance3.setUser(user2);
//        eventInstance3.setEvent(e1);
//        eventInstance3.setstatus("pending");
//
//        EventInstance eventInstance4 = new EventInstance();
//        eventInstance4.setWorkflowInstance(workflowInstance2);
//        eventInstance4.setUser(user1);
//        eventInstance4.setEvent(e2);
//        eventInstance4.setstatus("pending");

        session.persist(workflow1);

        session.persist(designer1);

        session.persist(e1);
        session.persist(e2);
        session.persist(user1);
        session.persist(user2);
        session.persist(user3);
        session.persist(user4);
        session.persist(user5);


        session.persist(workflowInstance1);
        //session.persist(workflowInstance2);
        session.persist(eventInstance1);
        session.persist(eventInstance2);
       // session.persist(eventInstance3);
        //session.persist(eventInstance4);
        t.commit();
        session.close();


        /*Event e1 = new Event();
        e1.setPre_event(0);
        e1.setNext_event(2);
        e1.setWhat("Approve");

        Event e2 = new Event();
        e2.setWhat("Approve/Reject");
        e2.setNext_event(3);
        e2.setPre_event(2);

        Event e3 = new Event();
        e3.setWhat("Fill");
        e3.setNext_event(2);
        e3.setPre_event(0);

        ArrayList<Event> list1=new ArrayList<Event>();
        ArrayList<Event> list2=new ArrayList<Event>();
        list1.add(e1);
        list1.add(e2);

        list2.add(e3);
        ArrayList<Event> doneByUser1 = new ArrayList<Event>();
        ArrayList<Event> doneByUser2 = new ArrayList<Event>();

        doneByUser1.add(e1);
        doneByUser1.add(e2);

        doneByUser2.add(e3);

        User user1 = new User();
        user1.setName("Samarth");
        user1.setPasswd("s");
        user1.setRole("Clerk");
        //user1.setEvents(doneByUser1);


        User user2 = new User();
        user2.setName("Parul");
        user2.setPasswd("p");
        user2.setRole("Admin");
        //user2.setEvents(doneByUser2);

        ArrayList<User> l=new ArrayList<User>();
        l.add(user1);
        l.add(user2);

        ArrayList<User> l2=new ArrayList<User>();
        l2.add(user1);

        ArrayList<Workflow> managedByDesigner1 = new ArrayList<Workflow>();


        Workflow workflow1 = new Workflow();
        workflow1.setNumber_of_events(3);
        workflow1.setName("Loan Application");
        workflow1.setEvents(list1);

        //workflow1.setUsers(l);

        Workflow workflow2 = new Workflow();
        workflow2.setNumber_of_events(1);
        workflow2.setName("Approval process");
        workflow2.setEvents(list2);
        //workflow2.setUsers(l2);

        managedByDesigner1.add(workflow1);
        managedByDesigner1.add(workflow2);

        Designer designer = new Designer();
        designer.setName("Abcd");
        designer.setPasswd("abcd");
        designer.setWorkflow(managedByDesigner1);

        // Instantiation code.
        EventInstance ev1 = new EventInstance();
        ev1.setEvent(e1);
        ev1.setUser(user1);

        EventInstance ev2 = new EventInstance();
        ev2.setEvent(e2);
        ev2.setUser(user2);

        WorkflowInstance workflowInstance1 = new WorkflowInstance();
        workflowInstance1.setWorkflow(workflow1);
        workflowInstance1.setUsers(user1);

        ev1.setWorkflowInstance(workflowInstance1);
        ev2.setWorkflowInstance(workflowInstance1);

        session.persist(workflow1);
        session.persist(workflow2);
        session.persist(designer);
        session.persist(e1);
        session.persist(e2);
        session.persist(e3);
        session.persist(user1);
        session.persist(user2);
        session.persist(workflowInstance1);
        session.persist(ev1);
        session.persist(ev2);
        t.commit();
        session.close();*/
        System.out.println("success");
    }
}
