package org.cencora;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.cencora.model.Ticket;

import java.util.List;

@Path("/tickets")
public class TicketResource {
    @PersistenceContext
    EntityManager em;

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets() {
//        List<Ticket>tkts= em.createQuery("from Ticket", Ticket.class).getResultList();
//        return Response.ok(tkts).build();
        List<Ticket> tik = Ticket.listAll();
        return Response.ok(tik).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketById(@PathParam("id") int id) {
//        Ticket tkt= em.find(Ticket.class, id);
//        if( tkt == null ){
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.ok(tkt).build();
        Ticket tik = Ticket.findById(id);
        return Response.ok(tik).build();
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addTicket(Ticket ticket) {
//        em.persist(ticket);
        Ticket.persist(ticket);
        return Response.ok(ticket).build();
    }

    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Transactional
    public Response deleteTicket(@PathParam("id") int id) {
//        Ticket tkt= em.find(Ticket.class, id);
//        if(tkt != null){
//            em.remove(tkt);
//            return Response.ok().build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
        Ticket.deleteById(id);
        return Response.ok().build();
    }

    @Path("/update/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateTicket(@PathParam("id") int id, Ticket ticket) {
        Ticket tkt= em.find(Ticket.class, id);
        if(tkt != null){
            tkt.setDescription(ticket.getDescription());
            tkt.setDate(ticket.getDate());
            em.merge(tkt);
            return Response.ok(tkt).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
