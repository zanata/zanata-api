/*
 * Copyright 2011, Red Hat, Inc. and individual contributors
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
package org.zanata.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.zanata.common.LocaleId;
import org.zanata.common.Namespaces;

/**
 *
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 *
 **/

@XmlType(name = "glossaryTermType", propOrder = {"comment", "content", "locale", "lastModifiedDate", "lastModifiedBy"})
@JsonPropertyOrder({ "content", "comment", "locale", "lastModifiedDate", "lastModifiedBy" })
@JsonIgnoreProperties(value = "comments", ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GlossaryTerm implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 6140176481272689471L;

    @NotNull
    private LocaleId locale;

    private String content;

    private String comment;

    private String lastModifiedBy;

    private Date lastModifiedDate;

    public GlossaryTerm() {
    }

    @XmlAttribute(name = "lang", namespace = Namespaces.XML)
    @XmlJavaTypeAdapter(type = LocaleId.class, value = LocaleIdAdapter.class)
    public LocaleId getLocale() {
        return locale;
    }

    public void setLocale(LocaleId locale) {
        this.locale = locale;
    }

    @XmlElement(name = "content", required = false,
            namespace = Namespaces.ZANATA_OLD)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * See {@link #getComment}
     */
    @XmlTransient
    @Deprecated
    public List<String> getComments() {
        return new ArrayList<String>() {
            {
                add(getComment());
            }
        };
    }

    /**
     * See {@link #setComment}
     */
    @Deprecated
    public void setComments(List<String> comments) {
        setComment(StringUtils.join(comments, ","));
    }

    @XmlElement(name = "comment", namespace = Namespaces.ZANATA_API)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @XmlElement(name = "lastModifiedBy", required = false,
        namespace = Namespaces.ZANATA_API)
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @XmlElement(name = "lastModifiedDate", required = false,
        namespace = Namespaces.ZANATA_API)
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return DTOUtil.toXML(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GlossaryTerm)) return false;

        GlossaryTerm that = (GlossaryTerm) o;

        if (comment != null ? !comment.equals(that.comment) :
            that.comment != null)
            return false;
        if (content != null ? !content.equals(that.content) :
            that.content != null)
            return false;
        if (lastModifiedBy != null ?
            !lastModifiedBy.equals(that.lastModifiedBy) :
            that.lastModifiedBy != null) return false;
        if (lastModifiedDate != null ?
            !lastModifiedDate.equals(that.lastModifiedDate) :
            that.lastModifiedDate != null) return false;
        if (locale != null ? !locale.equals(that.locale) : that.locale != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = locale != null ? locale.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result =
            31 * result +
                (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
        result = 31 * result +
            (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        return result;
    }
}
