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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.zanata.rest.DocumentFileUploadForm;
import org.zanata.rest.dto.ChunkUploadResponse;

/**
 * Interface for file upload and download REST methods.
 */
public interface FileResource
{

   public static final String FILE_RESOURCE = "/file";
   public static final String DOWNLOAD_TEMPLATE = "/download/{downloadId}";
   public static final String FILE_DOWNLOAD_TEMPLATE = "/translation/{projectSlug}/{iterationSlug}/{locale}/{fileType}" ;
   public static final String TRANSLATION_UPLOAD_TEMPLATE = "/translation/{projectSlug}/{iterationSlug}/{locale}";
   public static final String SOURCE_UPLOAD_TEMPLATE = "/source/{projectSlug}/{iterationSlug}";
   public static final String SOURCE_DOWNLOAD_TEMPLATE = "/source/{projectSlug}/{iterationSlug}/{fileType}";

   /**
    * Specifies to download the original source file that was uploaded, byte-for-byte.
    */
   public static final String FILETYPE_RAW_SOURCE_DOCUMENT = "raw";

   /**
    * Specifies to download a preview of the translated document in the original
    * source format, showing all non-empty translations (even if not approved).
    * Where translations are empty, source strings are used.
    */
   public static final String FILETYPE_TRANSLATED_APPROVED_AND_FUZZY = "half-baked";

   /**
    * Specifies to download a completed version of a translated document in the
    * original source format, showing only approved translations. Where no
    * approved translation is available, source strings are used.
    */
   public static final String FILETYPE_TRANSLATED_APPROVED = "baked";

   @POST
   @Path(SOURCE_UPLOAD_TEMPLATE)
   @Consumes( MediaType.MULTIPART_FORM_DATA)
   // /file/source/{projectSlug}/{iterationSlug}?docId={docId}
   public Response uploadSourceFile( @PathParam("projectSlug") String projectSlug,
                                     @PathParam("iterationSlug") String iterationSlug,
                                     @QueryParam("docId") String docId,
                                     @MultipartForm DocumentFileUploadForm uploadForm );

   @POST
   @Path(TRANSLATION_UPLOAD_TEMPLATE)
   @Consumes( MediaType.MULTIPART_FORM_DATA)
   // /file/translation/{projectSlug}/{iterationSlug}/{locale}?docId={docId}&merge={merge}
   public Response uploadTranslationFile( @PathParam("projectSlug") String projectSlug,
                                          @PathParam("iterationSlug") String iterationSlug,
                                          @PathParam("locale") String localeId,
                                          @QueryParam("docId") String docId,
                                          @QueryParam("merge") String merge,
                                          @MultipartForm DocumentFileUploadForm uploadForm );

   @GET
   @Path(SOURCE_DOWNLOAD_TEMPLATE)
   // /file/source/{projectSlug}/{iterationSlug}/{fileType}?docId={docId}
   public Response downloadSourceFile( @PathParam("projectSlug") String projectSlug,
                                       @PathParam("iterationSlug") String iterationSlug,
                                       @PathParam("fileType") String fileType,
                                       @QueryParam("docId") String docId);

   @GET
   @Path(FILE_DOWNLOAD_TEMPLATE)
   // /file/translation/{projectSlug}/{iterationSlug}/{locale}/{fileType}?docId={docId}
   public Response downloadTranslationFile( @PathParam("projectSlug") String projectSlug,
                                            @PathParam("iterationSlug") String iterationSlug,
                                            @PathParam("locale") String locale,
                                            @PathParam("fileType") String fileExtension,
                                            @QueryParam("docId") String docId );

   @GET
   @Path(DOWNLOAD_TEMPLATE)
   // /file/download/{downloadId}
   public Response download( @PathParam("downloadId") String downloadId );
}
