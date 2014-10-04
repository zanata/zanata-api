/*
 * Copyright 2010, Red Hat, Inc. and individual contributors as indicated by the
 * @author tags. See the copyright.txt file in the distribution for a full
 * listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package org.zanata.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.enunciate.jaxrs.TypeHint;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.dto.stats.ContainerTranslationStatistics;
import org.zanata.rest.dto.stats.TranslationStatistics;

/**
 * @author Carlos Munoz <a
 *         href="mailto:camunoz@redhat.com">camunoz@redhat.com</a>
 */
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface StatisticsResource {
    public static final String SERVICE_PATH = "/stats";

    /**
     * Get translation statistics for a Project iteration and (optionally) it's
     * underlying documents.
     *
     * @param projectSlug
     *            Project identifier.
     * @param iterationSlug
     *            Project Iteration identifier.
     * @param includeDetails
     *            Indicates whether to include detailed statistics for the
     *            project iteration's documents.
     * @param includeWordStats
     *            Indicates whether to include word-level statistics. Default is
     *            only message level stats.
     * @param locales
     *            Locale statistics to be fetched. If this is empty, all locale
     *            statistics will be returned. This parameter may be specified
     *            multiple times if multiple locales are to be fetched.
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing translation statistics for the
     *         specified parameters.<br>
     *         NOT FOUND(404) - If a project iteration could not be found for
     *         the given parameters.<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @GET
    @Path("/proj/{projectSlug}/iter/{iterationSlug}")
    @TypeHint(ContainerTranslationStatistics.class)
    public
            ContainerTranslationStatistics
            getStatistics(
                    @PathParam("projectSlug") String projectSlug,
                    @PathParam("iterationSlug") String iterationSlug,
                    @QueryParam("detail") @DefaultValue("false") boolean includeDetails,
                    @QueryParam("word") @DefaultValue("false") boolean includeWordStats,
                    @QueryParam("locale") String[] locales);

    /**
     * Get translation statistics for a Document.
     *
     * @param projectSlug
     *            Project identifier.
     * @param iterationSlug
     *            Project Iteration identifier.
     * @param docId
     *            Document identifier.
     * @param includeWordStats
     *            Indicates whether to include word-level statistics. Default is
     *            only message level stats.
     * @param locales
     *            Locale statistics to be fetched. If this is empty, all locale
     *            statistics will be returned. This parameter may be specified
     *            multiple times if multiple locales are to be fetched.
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing translation statistics for the
     *         specified parameters.<br>
     *         NOT FOUND(404) - If a document could not be found for the given
     *         parameters.<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @GET
    @Path("/proj/{projectSlug}/iter/{iterationSlug}/doc/{docId:.*}")
    @TypeHint(ContainerTranslationStatistics.class)
    public
            ContainerTranslationStatistics
            getStatistics(
                    @PathParam("projectSlug") String projectSlug,
                    @PathParam("iterationSlug") String iterationSlug,
                    @PathParam("docId") String docId,
                    @QueryParam("word") @DefaultValue("false") boolean includeWordStats,
                    @QueryParam("locale") String[] locales);


    /**
     * Retrieves word and message statistics of document.
     *
     * @param docId
     *            The document identifier. Some document ids could have forward
     *            slashes ('/') in them which would cause conflicts with the
     *            browser's own url interpreter. For this reason, the supplied
     *            id must have all its '/' characters replaced with commas
     *            (',').
     *
     * @return The following response status codes will be returned from this
     *         operation:<br>
     *         OK(200) - Response containing a list of statistics(word and messages). <br>
     *         NOT FOUND(404) - If a document could not be found for the given
     *         parameters.<br>
     *         INTERNAL SERVER ERROR(500) - If there is an unexpected error in
     *         the server while performing this operation.
     */
    @GET
    @Path("/project/{projectSlug}/version/{versionSlug}/doc/{docId}/locale/{localeId}")
    @TypeHint(TranslationStatistics[].class)
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getDocumentStatistics(
            @PathParam("projectSlug") String projectSlug,
            @PathParam("versionSlug") String versionSlug,
            @PathParam("docId") String docId,
            @PathParam("localeId") String localeId);

}
