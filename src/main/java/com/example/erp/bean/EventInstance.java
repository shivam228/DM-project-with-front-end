package com.example.erp.bean;

import javax.persistence.*;
import java.util.List;

@Entity
public class EventInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private  User user;

    @ManyToOne
    private Event event;

    @ManyToOne
    private WorkflowInstance workflowInstance;
    private String status;


    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public WorkflowInstance getWorkflowInstance() {
        return workflowInstance;
    }

    public void setWorkflowInstance(WorkflowInstance workflowInstance) {
        this.workflowInstance = workflowInstance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
