package org.zanata.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.TransUnitStatus;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public interface TransUnitResource {

    public static final String SERVICE_PATH =
            ProjectVersionResource.VERSION_PATH;

    /**
     * Retrieves a list translation unit with status in a document.
     *
     * @param projectSlug Project identifier
     * @param versionSlug Project version identifier.
     * @param docId document identifier.
     * @param localeId target locale
     *
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing a full list of locales. <br>
     *         NOT FOUND(404) - If a document or locale could not be found for
     *         the given parameters.<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @GET
    @Produces({ MediaTypes.APPLICATION_ZANATA_TRANS_UNIT_RESOURCE_JSON,
            MediaType.APPLICATION_JSON })
    @TypeHint(TransUnitStatus[].class)
    @Path("/doc/{docId}/status/{localeId}")
    public Response getStatus(@PathParam("projectSlug") String projectSlug,
            @PathParam("versionSlug") String versionSlug,
            @PathParam("docId") String docId,
            @PathParam("localeId") String localeId);
}
