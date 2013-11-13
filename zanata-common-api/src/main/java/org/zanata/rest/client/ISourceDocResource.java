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
package org.zanata.rest.client;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.jboss.resteasy.client.ClientResponse;
import org.zanata.common.Namespaces;
import org.zanata.rest.dto.resource.Resource;
import org.zanata.rest.dto.resource.ResourceMeta;
import org.zanata.rest.service.SourceDocResource;

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
import java.util.List;
import java.util.Set;

/**
 * Client interface for Source document resources.
 *
 * @author Carlos Munoz <a
 *         href="mailto:camunoz@redhat.com">camunoz@redhat.com</a>
 */
//TODO remove the template parameters from SourceDocResource's Path
//@Path(SourceDocResource.SERVICE_PATH)
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface ISourceDocResource extends SourceDocResource {
    @Override
    @GET
    @Wrapped(element = "resources", namespace = Namespaces.ZANATA_API)
    public ClientResponse<List<ResourceMeta>> get(
            @QueryParam("ext") Set<String> extensions);

    @Override
    @POST
    public ClientResponse<String> post(Resource messageBody,
            @QueryParam("ext") Set<String> extensions,
            @QueryParam("copyTrans") @DefaultValue("true") boolean copytrans);

    @Override
    @GET
    @Path(RESOURCE_SLUG_TEMPLATE)
    public ClientResponse<Resource> getResource(
            @PathParam("id") String idNoSlash,
            @QueryParam("ext") Set<String> extensions);

    @Override
    @PUT
    @Path(RESOURCE_SLUG_TEMPLATE)
    public ClientResponse<String> putResource(
            @PathParam("id") String idNoSlash, Resource resource,
            @QueryParam("ext") Set<String> extensions,
            @QueryParam("copyTrans") @DefaultValue("true") boolean copytrans);

    @PUT
    @Path(RESOURCE_SLUG_TEMPLATE)
    @Deprecated
    public ClientResponse<String> putResource(
            @PathParam("id") String idNoSlash, Resource resource,
            @QueryParam("ext") Set<String> extensions);

    @Override
    @DELETE
    @Path(RESOURCE_SLUG_TEMPLATE)
    public ClientResponse<String> deleteResource(
            @PathParam("id") String idNoSlash);

    @Override
    @GET
    @Path(RESOURCE_SLUG_TEMPLATE + "/meta")
    public ClientResponse<ResourceMeta> getResourceMeta(
            @PathParam("id") String idNoSlash,
            @QueryParam("ext") Set<String> extensions);

    @Override
    @PUT
    @Path(RESOURCE_SLUG_TEMPLATE + "/meta")
    public ClientResponse<String> putResourceMeta(
            @PathParam("id") String idNoSlash, ResourceMeta messageBody,
            @QueryParam("ext") Set<String> extensions);

}
