package org.fieldenbriggs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by 1354177 on 2016-11-02.
 */
@Provider
public class GestionException implements ExceptionMapper<Exception> {
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getClass().getSimpleName()).build();
    }
}
