package org.zanata.rest.dto.resource.editor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.zanata.rest.dto.resource.editor.TransUnit;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TransUnits implements HasJSONEntity, Serializable {

    private Map<String, TransUnit> transUnitMap =
            new HashMap<String, TransUnit>();

    public void addTransUnit(String textFlowId, TransUnit transUnit) {
        transUnitMap.put(textFlowId, transUnit);
    }

    public TransUnit getTransUnit(String textFlowId) {
        return transUnitMap.get(textFlowId);
    }

    @Override
    public Object getJSONEntity() {
        return transUnitMap;
    }
}
