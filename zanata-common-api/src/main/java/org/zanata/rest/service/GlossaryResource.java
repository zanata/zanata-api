/*
 * Copyright 2010, Red Hat, Inc. and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.zanata.rest.service;

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

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.zanata.common.LocaleId;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.Glossary;
import org.zanata.rest.dto.GlossaryLocaleStats;

/**
 *
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 *
 **/
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON })
public interface GlossaryResource {
    public static final String SERVICE_PATH = "/glossary";

    /**
     * Deprecated.
     * @see #get
     *
     * Returns all Glossary entries.
     *
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing all Glossary entries in the system.
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @Deprecated
    @GET
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
            MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @TypeHint(Glossary.class)
    public Response getEntries();


    /**
     * Return source locales available for all glossary entries
     *
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing all Glossary entries in the system.
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @GET
    @Path("/locales/list")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
        MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @TypeHint(GlossaryLocaleStats.class)
    public Response getLocaleStatistic();

    /**
     * Returns Glossary entries for a single locale with paging
     *
     * @param srcLocale
     *            source locale
     * @param transLocale
     *            Translation locale
     * @param page
     *            Current request page (default value: 1)
     * @param sizePerPage
     *            Size of entry per page (default value: 500)
     * @param filter
     *            String filter for source content
     * @param fields
     *            Fields to sort. Comma separated. e.g sort=desc,-part_of_speech
     *            See {@link org.zanata.common.GlossarySortField}
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing all Glossary entries for the given
     *         locale. INTERNAL SERVER ERROR(500) - If there is an unexpected
     *         error in the server while performing this operation.
     */
    @GET
    @Path("/src/{srcLocale}/trans/{transLocale}")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
            MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @TypeHint(Glossary.class)
    public Response get(@PathParam("srcLocale") LocaleId srcLocale,
        @PathParam("transLocale") LocaleId transLocale,
        @DefaultValue("1") @QueryParam("page") int page,
        @DefaultValue("500") @QueryParam("sizePerPage") int sizePerPage,
        @QueryParam("filter") String filter, @QueryParam("sort") String fields);

    /**
     * Returns Glossary entries for a source locale
     *
     * @param srcLocale
     *            source locale
     * @param page
     *            Current request page (from 1, will be ignored if negative)
     * @param sizePerPage
     *            Size of entry per page (from 1, will be ignored if negative)
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing all Glossary entries for the given
     *         locale. INTERNAL SERVER ERROR(500) - If there is an unexpected
     *         error in the server while performing this operation.
     */
    @GET
    @Path("/src/{srcLocale}")
    @Produces({ MediaTypes.APPLICATION_ZANATA_GLOSSARY_XML,
        MediaTypes.APPLICATION_ZANATA_GLOSSARY_JSON,
        MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @TypeHint(Glossary.class)
    public Response get(@PathParam("srcLocale") LocaleId srcLocale,
        @DefaultValue("1") @QueryParam("page") int page,
        @DefaultValue("500") @QueryParam("sizePerPage") int sizePerPage,
        @QueryParam("filter") String filter, @QueryParam("sort") String fields);

    /**
     * Adds glossary entries.
     *
     * @param glossary
     *            The Glossary entries to add.
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         CREATED(201) - If the glossary entries were successfully created.
     *         UNAUTHORIZED(401) - If the user does not have the proper
     *         permissions to perform this operation.<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @POST
    public Response put(Glossary messageBody);


    /**
     * Upload glossary file
     *
     * @param file
     *          Target file
     */
    @Path("/src/{srcLocale}/trans/{transLocale}/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response upload(@PathParam("srcLocale") LocaleId srcLocale,
        @PathParam("transLocale") LocaleId transLocale,
        MultipartFormDataInput input);

    /**
     * Deprecated.
     * @see #deleteGlossary
     *
     * Delete all glossary terms with the specified locale.
     *
     * @param locale
     *            The target locale to delete glossary entries from.
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - If the glossary entries were successfully deleted.
     *         UNAUTHORIZED(401) - If the user does not have the proper
     *         permissions to perform this operation.<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @Deprecated
    @DELETE
    @Path("/{locale}")
    public Response deleteGlossary(@PathParam("locale") LocaleId locale);

    /**
     *
     * Delete glossary which given resId and locale.
     *
     * @param locale
     *          The source locale to delete glossary entries from.
     * @param resId
     *          resId for source glossary term
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - If the glossary entry were successfully deleted.
     *         UNAUTHORIZED(401) - If the user does not have the proper
     *         permissions to perform this operation.<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @DELETE
    @Path("/{locale}/{resId}")
    public Response deleteGlossary(@PathParam("locale") LocaleId locale,
            @PathParam("resId") String resId);

    /**
     * Deprecated.
     * @see #deleteGlossary
     *
     * Delete all glossary terms.
     *
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - If the glossary entries were successfully deleted.
     *         UNAUTHORIZED(401) - If the user does not have the proper
     *         permissions to perform this operation.<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @Deprecated
    @DELETE
    public Response deleteGlossaries();

}
