package org.zanata.rest.dto.resource;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.zanata.common.ContentState;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@JsonPropertyOrder({ "id", "revision", "status", "content", "contents", "plural" })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TranslationData extends TextContainer implements Serializable {

    @NotNull
    private Integer id;

    @NotNull
    private Integer revision;

    @NotNull
    private ContentState status = ContentState.New;

    private boolean plural;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public ContentState getStatus() {
        return status;
    }

    public void setStatus(ContentState status) {
        this.status = status;
    }

    public boolean isPlural() {
        return plural;
    }

    public void setPlural(boolean plural) {
        this.plural = plural;
    }
}
