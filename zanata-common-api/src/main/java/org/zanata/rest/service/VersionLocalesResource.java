package org.zanata.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.zanata.common.Namespaces;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.Locale;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public interface VersionLocalesResource {
    public static final String ITERATION_SLUG_TEMPLATE = "{versionSlug:"
            + RestConstants.SLUG_PATTERN + "}";

    public static final String SERVICE_PATH = ProjectResource.SERVICE_PATH
            + "/iterations/i/" + ITERATION_SLUG_TEMPLATE + "/locales";

    /**
     * Retrieves a full list of locales enabled in version. The result is
     *
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing a full list of locales. <br>
     *         NOT FOUND(404) - If a Version could not be found for the given
     *         parameters.<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @GET
    @Produces({ MediaTypes.APPLICATION_ZANATA_LOCALES_JSON,
            MediaType.APPLICATION_JSON })
    @Wrapped(element = "locales", namespace = Namespaces.ZANATA_API)
    @TypeHint(Locale[].class)
    public Response get();
}
