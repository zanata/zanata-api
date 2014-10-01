package org.zanata.rest.service.editor;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.resource.TransUnit;
import org.zanata.rest.dto.resource.TransUnits;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public interface SourceResource {

    public static final String SERVICE_PATH = "/source";

    /**
     * Retrieves a list source string in given id.
     *
     * @param ids list textFlow's id (comma separated)
     *
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing a full list of locales. <br>
     *         Forbidden(403) - If ids list is too long<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @GET
    @Produces({ MediaTypes.APPLICATION_ZANATA_SOURCE_JSON,
        MediaType.APPLICATION_JSON })
    @TypeHint(TransUnits.class)
    public Response get(@QueryParam("ids") String ids);
}
