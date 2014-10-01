package org.zanata.rest.dto.resource;

import java.io.Serializable;
import java.util.HashMap;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.zanata.common.LocaleId;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TransUnits extends HashMap<String, Object> implements Serializable {

    public void addTransUnit(String textFlowId, TransUnit transUnit) {
        this.put(textFlowId, transUnit);
    }

}
