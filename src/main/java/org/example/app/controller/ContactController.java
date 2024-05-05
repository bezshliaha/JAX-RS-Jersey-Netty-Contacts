package org.example.app.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.domain.contact.Contact;
import org.example.app.network.ResponseEntity;
import org.example.app.network.ResponseEntityList;
import org.example.app.service.impl.ContactService;
import org.example.app.utils.Constants;

import java.util.Collections;
import java.util.List;

@Path("/api/v1/contacts")
@Produces({MediaType.APPLICATION_JSON})
public class ContactController {

    ContactService service = new ContactService();

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Contact contact) {
        Contact contactCreated = service.create(contact);
        if (contactCreated != null) {
            return Response.ok(new ResponseEntity<>(201, "Created",
                            true, contactCreated).toString())
                    .status(Response.Status.CREATED).build();
        } else {
            return Response.ok(new ResponseEntity<>(204, "No Content",
                            false, Constants.TEXT_NO_CONTENT).toString())
                    .status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    public Response fetchAll() {
        List<Contact> list = service.fetchAll();
        if (!list.isEmpty()) {
            return Response.ok(new ResponseEntityList<>(200, "OK",
                            true, list).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntityList<>(404, "Not Found",
                            false, Collections.emptyList()).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{id: [0-9]+}")
    public Response fetchById(@PathParam("id") Long id) {
        Contact contact = service.fetchById(id);
        if (contact != null) {
            return Response.ok(new ResponseEntity<>(200, "OK",
                            true, contact.toString()).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntity<>(404, "Not Found",
                            true, Constants.TEXT_NOT_FOUND).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id: [0-9]+}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, Contact contact) {
        Contact contactToUpdate = service.fetchById(id);
        if (contactToUpdate != null) {
            Contact contactUpdated = service.update(id, contact);
            return Response.ok(new ResponseEntity<>(200, "OK",
                            true, contactUpdated).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntity<>(404, "Not Found",
                            true, Constants.TEXT_NOT_FOUND).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id: [0-9]+}")
    public Response delete(@PathParam("id") Long id) {
        if (service.delete(id)) {
            return Response.ok(new ResponseEntity<>(200, "OK",
                            true, Constants.TEXT_DELETED).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntity<>(404, "Not Found",
                            true, Constants.TEXT_NOT_FOUND).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }

    /*
        http://localhost:8081/api/v1/contacts/query-by-name?name=Tom
        If name does not exist
        http://localhost:8081/api/v1/contacts/query-by-name?name=Tomas
    */
    @GET
    @Path("/query-by-name")
    public Response fetchByName(@QueryParam("name") String name) {
        List<Contact> list = service.fetchByName(name);
        if (!list.isEmpty()) {
            return Response.ok(new ResponseEntityList<>(200, "OK",
                            true, list).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntityList<>(404, "Not Found",
                            false, Collections.emptyList()).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }


    /*
        http://localhost:8081/api/v1/contacts/query-between-ids?from=3&to=6
        If ids do not exist
        http://localhost:8081/api/v1/contacts/query-between-ids?from=9&to=12
    */
    @GET
    @Path("/query-between-ids")
    public Response fetchBetweenIds(
            @QueryParam("from") int from,
            @QueryParam("to") int to
    ) {
        List<Contact> list = service.fetchBetweenIds(from, to);
        if (!list.isEmpty()) {
            return Response.ok(new ResponseEntityList<>(200, "OK",
                            true, list).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntityList<>(404, "Not Found",
                            false, Collections.emptyList()).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }

    /*
        http://localhost:8081/api/v1/contacts/query-name-in?name1=Bob&name2=John
        If name1 does not exist
        http://localhost:8081/api/v1/contacts/query-name-in?name1=Bobby&name2=John
        If name2 does not exist
        http://localhost:8081/api/v1/contacts/query-name-in?name1=Bob&name2=Johnny
        If name1 and name2 do not exist
        http://localhost:8081/api/v1/contacts/query-name-in?name1=Bobby&name2=Johnny
    */
    @GET
    @Path("/query-name-in")
    public Response fetchNameIn(
            @QueryParam("name1") String name1,
            @QueryParam("name2") String name2
    ) {
        List<Contact> list =
                service.fetchNameIn(name1, name2);
        if (!list.isEmpty()) {
            return Response.ok(new ResponseEntityList<>(200, "OK",
                            true, list).toString())
                    .status(Response.Status.OK).build();
        } else {
            return Response.ok(new ResponseEntityList<>(404, "Not Found",
                            false, Collections.emptyList()).toString())
                    .status(Response.Status.NOT_FOUND).build();
        }
    }
}
