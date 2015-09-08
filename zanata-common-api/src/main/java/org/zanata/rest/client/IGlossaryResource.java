package org.zanata.rest.client;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.zanata.common.LocaleId;
import org.zanata.rest.DocumentFileUploadForm;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.Glossary;
import org.zanata.rest.dto.GlossaryLocaleStats;
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
import javax.ws.rs.core.Response;
import java.io.File;

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
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
            MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @TypeHint(Glossary.class)
    public ClientResponse<Glossary> getEntries();

    @Override
    @GET
    @Path("/locales/list")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
        MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @TypeHint(GlossaryLocaleStats.class)
    public Response getLocaleStatistic();


    @Override
    @GET
    @Path("/src/{srcLocale}/trans/{transLocale}")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
        MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ClientResponse<Glossary> get(@PathParam("srcLocale") LocaleId srcLocale,
        @PathParam("transLocale") LocaleId transLocale,
        @DefaultValue("1") @QueryParam("page") int page,
        @DefaultValue("500") @QueryParam("sizePerPage") int sizePerPage,
        @QueryParam("filter") String filter, @QueryParam("sort") String fields);

    @Override
    @GET
    @Path("/src/{srcLocale}")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
        MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ClientResponse<Glossary> get(@PathParam("srcLocale") LocaleId srcLocale,
        @DefaultValue("1") @QueryParam("page") int page,
        @DefaultValue("500") @QueryParam("sizePerPage") int sizePerPage,
        @QueryParam("filter") String filter, @QueryParam("sort") String fields);

    @Override
    @POST
    public ClientResponse<String> put(Glossary glossary);


    @Override
    @Path("/src/{srcLocale}/trans/{transLocale}/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response upload(@PathParam("srcLocale") LocaleId srcLocale,
        @PathParam("transLocale") LocaleId transLocale,
        MultipartFormDataInput input);

    @Override
    @DELETE
    @Path("/{locale}")
    public ClientResponse<String> deleteGlossary(
            @PathParam("locale") LocaleId locale);

    @Override
    @DELETE
    @Path("/{locale}/{resId}")
    public ClientResponse<String> deleteGlossary(
        @PathParam("locale") LocaleId locale, @PathParam("resId") String resId);

    @Override
    @DELETE
    public ClientResponse<String> deleteGlossaries();

}
