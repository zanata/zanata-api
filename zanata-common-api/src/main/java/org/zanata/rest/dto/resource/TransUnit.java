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
public class TransUnit extends HashMap<String, Object> implements Serializable {

    private static final String SOURCE = "source";

    public TransUnit(TextFlow textFlow) {
        setSource(textFlow);
    }

    public void setSource(TextFlow textFlow) {
        this.put(SOURCE, textFlow);
    }

    public TextFlow getSource() {
        return (TextFlow)this.get(SOURCE);
    }

    public void addTarget(LocaleId localeId, TextFlowTarget target) {
        this.put(localeId.getId(), target);
    }
    
    public TextFlowTarget getTarget(LocaleId localeId) {
        return (TextFlowTarget)this.get(localeId.getId());
    } 

}
