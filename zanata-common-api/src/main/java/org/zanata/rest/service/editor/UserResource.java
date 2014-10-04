package org.zanata.rest.service.editor;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.User;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public interface UserResource {

    public static final String SERVICE_PATH = "/user";

    /**
     * Retrieves user information of current authenticated user
     *
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing user information <br>
     *         NOT FOUND(404) - If no authenticated user found. <br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @GET
    @Produces({ MediaTypes.APPLICATION_ZANATA_USER_JSON,
        MediaType.APPLICATION_JSON })
    @TypeHint(User.class)
    public Response getMyInfo();

    @GET
    @Produces({ MediaTypes.APPLICATION_ZANATA_USER_JSON,
        MediaType.APPLICATION_JSON})
    @TypeHint(User.class)
    @Path("/{username:[a-z\\d_]{3,20}}")
    public Response getUserInfo(@PathParam("username") String username);
}
