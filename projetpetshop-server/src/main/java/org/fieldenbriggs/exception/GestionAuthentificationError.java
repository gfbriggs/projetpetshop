package org.fieldenbriggs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Geoffrey on 11/1/2016.
 */
@Provider
public class GestionAuthentificationError implements ExceptionMapper<AuthentificationErrorException> {
    public Response toResponse(AuthentificationErrorException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getClass().getSimpleName()).build();
    }
}
