package org.zanata.rest.dto.resource.editor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.zanata.common.LocaleId;
import org.zanata.rest.dto.resource.TextFlow;
import org.zanata.rest.dto.resource.TextFlowTarget;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TransUnit implements HasJSONEntity, Serializable {

    private static final String SOURCE = "source";

    private TextFlow source;
    private Map<String, TextFlowTarget> targets =
        new HashMap<String, TextFlowTarget>();

    public TransUnit() {
    }

    public TransUnit(TextFlow textFlow) {
        setSource(textFlow);
    }

    public void setSource(TextFlow textFlow) {
        this.source = textFlow;
    }

    public TextFlow getSource() {
        return source;
    }

    public void addTarget(LocaleId localeId, TextFlowTarget target) {
        targets.put(localeId.getId(), target);
    }

    public TextFlowTarget getTarget(LocaleId localeId) {
        return targets.get(localeId.getId());
    }

    @Override
    public Object getJSONEntity() {
        Map<String, Object> entity = new HashMap<String, Object>();
        if(source != null) {
            entity.put(SOURCE, source);
        }
        for(Map.Entry<String, TextFlowTarget> target: targets.entrySet()) {
            entity.put(target.getKey(), target.getValue());
        }
        return entity;
    }

}
