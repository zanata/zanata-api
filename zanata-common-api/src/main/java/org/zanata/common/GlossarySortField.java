/*
 * Copyright 2015, Red Hat, Inc. and individual contributors
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

package org.zanata.common;

import java.io.Serializable;

/**
 * @author Alex Eng <a href="aeng@redhat.com">aeng@redhat.com</a>
 */
public enum GlossarySortField implements Serializable {
    src_content("term.content", true),
    part_of_speech("term.glossaryEntry.pos", true),
    desc("term.glossaryEntry.description", true),
    trans_count("term.glossaryEntry.glossaryTerms.size", true);

    private final String entityField;
    private boolean ascending;

    GlossarySortField(String entityField, boolean ascending) {
        this.entityField = entityField;
        this.ascending = ascending;
    }

    public String getEntityField() {
        return entityField;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public static final GlossarySortField getByField(String field) {
        if(field == null || field.length() <= 0) {
            throw new IllegalArgumentException(field);
        }

        boolean isAscending = !field.startsWith("-");
        String processedField =
            field.startsWith("-") ? field.substring(1) : field;

        for(GlossarySortField sortField: GlossarySortField.values()) {
            if(sortField.name().equals(processedField)) {
                sortField.setAscending(isAscending);
                return sortField;
            }
        }
        return null;
    }
}
