package org.zanata.rest.client;

import java.util.List;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.client.ClientResponse;
import org.zanata.common.LocaleId;
import org.zanata.rest.GlossaryFileUploadForm;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.GlossaryEntry;
import org.zanata.rest.dto.GlossaryInfo;
import org.zanata.rest.dto.ResultList;
import org.zanata.rest.service.GlossaryResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(GlossaryResource.SERVICE_PATH)
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON })
public interface IGlossaryResource extends GlossaryResource {
    public static final String SERVICE_PATH = "/glossary";

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
    @Path("/entries")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
            MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ClientResponse<ResultList> getEntries(
            @DefaultValue("en-US") @QueryParam("srcLocale") LocaleId srcLocale,
            @QueryParam("transLocale") LocaleId transLocale,
            @DefaultValue("1") @QueryParam("page") int page,
            @DefaultValue("1000") @QueryParam("sizePerPage") int sizePerPage,
            @QueryParam("filter") String filter,
            @QueryParam("sort") String fields);

    @Override
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/entries")
    public ClientResponse<GlossaryEntry> post(
        List<GlossaryEntry> glossaryEntries);

    @Override
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public ClientResponse<GlossaryEntry> upload(
            @MultipartForm GlossaryFileUploadForm form);

    @Override
    @DELETE
    @Path("/entries/{hash}")
    @Produces(MediaType.APPLICATION_JSON)
    public ClientResponse<GlossaryEntry> deleteEntry(
            @PathParam("hash") String hash);

    @Override
    @DELETE
    public ClientResponse<String> deleteAllEntries();

}
