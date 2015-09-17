package org.zanata.rest.client;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.client.ClientResponse;
import org.zanata.common.LocaleId;
import org.zanata.rest.GlossaryFileUploadForm;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.Glossary;
import org.zanata.rest.dto.GlossaryInfo;
import org.zanata.rest.service.GlossaryResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(GlossaryResource.SERVICE_PATH)
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON })
public interface IGlossaryResource extends GlossaryResource {
    public static final String SERVICE_PATH = "/glossary";

    @Deprecated
    @Override
    @GET
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
            MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @TypeHint(Glossary.class)
    public ClientResponse<Glossary> getEntries();

    @Override
    @GET
    @Path("/info")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
        MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @TypeHint(GlossaryInfo.class)
    public ClientResponse<GlossaryInfo> getInfo();


    @Override
    @GET
    @Path("/src/{srcLocale}/trans/{transLocale}")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
        MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ClientResponse<Glossary> getEntriesForLocale(@PathParam("srcLocale") LocaleId srcLocale,
        @PathParam("transLocale") LocaleId transLocale,
        @DefaultValue("1") @QueryParam("page") int page,
        @DefaultValue("5000") @QueryParam("sizePerPage") int sizePerPage,
        @QueryParam("filter") String filter, @QueryParam("sort") String fields);

    @Override
    @GET
    @Path("/src/{srcLocale}")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
        MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ClientResponse<Glossary> getAllEntries(@PathParam("srcLocale") LocaleId srcLocale,
        @DefaultValue("1") @QueryParam("page") int page,
        @DefaultValue("5000") @QueryParam("sizePerPage") int sizePerPage,
        @QueryParam("filter") String filter, @QueryParam("sort") String fields);

    @Override
    @PUT
    public ClientResponse<String> put(Glossary glossary);

    @Override
    @POST
    public ClientResponse<String> post(Glossary glossary);

    @Override
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response upload(@MultipartForm GlossaryFileUploadForm form);

    @Override
    @DELETE
    @Path("/entries/{resId}")
    public ClientResponse<String> deleteEntry(@PathParam("resId") String resId);

    @Deprecated
    @Override
    @DELETE
    public ClientResponse<String> deleteAllEntries();

}
