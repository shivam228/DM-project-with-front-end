package com.example.erp.bean;

import javax.persistence.*;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private Integer pre_event;
    private Integer next_event;
    private String action;

    @ManyToOne
    private Workflow workflow;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private String what;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public Integer getPre_event() {
        return pre_event;
    }

    public void setPre_event(Integer pre_event) {
        this.pre_event = pre_event;
    }

    public Integer getNext_event() {
        return next_event;
    }

    public void setNext_event(Integer next_event) {
        this.next_event = next_event;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }
}
