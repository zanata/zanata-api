package org.zanata.rest.dto.resource.editor;

/**
 * Interface for Entity that generates custom JSON output.
 * (not properties in class)
 *
 *
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
public interface HasJSONEntity {

    Object getJSONEntity();
}
