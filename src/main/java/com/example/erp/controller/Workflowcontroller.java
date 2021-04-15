package com.example.erp.controller;

import com.example.erp.bean.*;
import com.example.erp.services.Workflowservice;

import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("workflow")
public class Workflowcontroller
{
    @POST
    @Path("/add_workflow/{name}/{desname}/{nevent}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response add_workflow(@PathParam("name") String wname,
                             @PathParam("desname") String desiname, @PathParam("nevent") int events) throws URISyntaxException {
        String result = "Workflow Added";
        System.out.println("workflow");
        Workflowservice wfs = new Workflowservice();
        Designer desi = new Designer();
        desi = wfs.getdes(desiname);
        wfs.addworkflow(wname, desi,events);
        System.out.println(result);
        return Response.status(200).entity(result).build();
    }

    @POST
    @Path("/add_event/{name}/{desc}/{wid}/{act}/{prior}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response add_event(@PathParam("name") String wname,
                             @PathParam("desc") String description, @PathParam("wid") int wfid,@PathParam("act") String action,@PathParam("prior") int priority) throws URISyntaxException {
        String result = "Event Added";
        Workflowservice wfs = new Workflowservice();
        Workflow wf = new Workflow();
        wf = wfs.getwf(wfid);
        int n = wf.getNumber_of_events();
        int totalevents = wfs.geteventcount();
        int pre,next;
        if(priority==1)
        {
            pre = -1;
            next  = totalevents+2;
        }
        else if(priority==n)
        {
            pre = totalevents;
            next = -1;
        }
        else
        {
            pre = totalevents;
            next = totalevents+2;
        }
        wfs.addevent(wname, description,wf,pre,next,action);
        System.out.println(result);
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/viewworkflow")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response viewAllBills(
    ) throws URISyntaxException
    {
        List<Workflow> sp  = new ArrayList<>();
        Workflowservice bls = new Workflowservice();
        sp = bls.allworkflow();
        GenericEntity<List<Workflow>> genericEntity = new GenericEntity<List<Workflow>>(sp){};
        return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/add_workflowinstance/{name}/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response add_workflow(@PathParam("name") String wname,
                                 @PathParam("id") int id) throws URISyntaxException {
        String result = "Workflow instance Added";
        Workflowservice wfs = new Workflowservice();
        Workflow wf = new Workflow();
        wf = wfs.getwf(id);
        wfs.addworkflowinstnace(wf);
        System.out.println(result);
        return Response.status(200).entity(result).build();
    }
    @POST
    @Path("/add_eventinstance/{name}/{role}/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response add_eventinstance(@PathParam("name") String tname,
                                 @PathParam("role") String role, @PathParam("id") int wfiid) throws URISyntaxException {
        String result = "Event Instance Added";
        Workflowservice wfs = new Workflowservice();
        Event e =  wfs.geteventid(tname);
        WorkflowInstance wi = new WorkflowInstance();
        wi = wfs.getworkflowins(wfiid);
        EventInstance e1 = new EventInstance();
        List<User> u  = new ArrayList<>();
        u = wfs.getuserrole(role);
        e1.setstatus("pending");
        e1.setWorkflowInstance(wi);
        e1.setEvent(e);
        e1.setUser(u.get(0));
        wfs.addeventinstance(e1);
        System.out.println(result);
        return Response.status(200).entity(result).build();
    }
    @GET
    @Path("/task_list/{uname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response viewtasks(@PathParam("uname") String name
    ) throws URISyntaxException
    {
        List<EventInstance> sp  = new ArrayList<>();
        Workflowservice bls = new Workflowservice();
        int id;
        id = bls.getuserid(name);
        sp = bls.gettaksofuser(id);
        GenericEntity<List<EventInstance>> genericEntity = new GenericEntity<List<EventInstance>>(sp){};
        return Response.ok(genericEntity, MediaType.APPLICATION_JSON).build();
    }
    @POST
    @Path("/user_task/{eid}/{einsid}/{action}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public Response execute_task(@PathParam("eid") int eventid,
                                 @PathParam("einsid") int eventinsid,@PathParam("action") String action) throws URISyntaxException {
        String result = "User task status";
        System.out.println(result);
        Workflowservice wfs = new Workflowservice();
         Event e = wfs.geteventbyid(eventid);
        System.out.println(e.getWhat());
         int wfins = wfs.getworkflowinstance(eventinsid);
        System.out.println(wfins);
        int preeveid = e.getPre_event();
        System.out.println(preeveid);
        if (preeveid == -1) {
            System.out.println("Execute the task And Mark status as completed");
            System.out.println("task Executing----" + e.getWhat());
            wfs.updatestatus(eventinsid);
        }
        else
        {
            EventInstance evx = new EventInstance();
            evx  = wfs.geteventstatus(preeveid,wfins);
            System.out.println(evx.getstatus());
            if (evx.getstatus().equals("pending"))
            {
                System.out.println("Task cannot be completed because previous task is not completed");
            }
            else
                {
                System.out.println("Task Executing---- " + e.getWhat());
                wfs.updatestatus(eventinsid);

            }
        }

        return Response.status(200).entity(result).build();
    }


}
