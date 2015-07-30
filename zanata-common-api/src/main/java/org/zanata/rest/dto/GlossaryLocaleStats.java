package org.zanata.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.zanata.common.Namespaces;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@XmlRootElement(name = "glossaryLocaleStats")
@XmlType(name = "glossaryLocaleStatsType", propOrder = { "srcLocale", "transLocale"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "srcLocale", "transLocale"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GlossaryLocaleStats implements Serializable {
    private GlossaryLocale srcLocale;
    private List<GlossaryLocale> transLocale;

    public GlossaryLocaleStats() {
        this(null, new ArrayList<GlossaryLocale>());
    }

    public GlossaryLocaleStats(GlossaryLocale srcLocale, List<GlossaryLocale> transLocale) {
        this.srcLocale = srcLocale;
        this.transLocale = transLocale;
    }

    @XmlElement(name = "srcLocale", required = false,
        namespace = Namespaces.ZANATA_OLD)
    public GlossaryLocale getSrcLocale() {
        return srcLocale;
    }

    public void setSrcLocale(GlossaryLocale srcLocale) {
        this.srcLocale = srcLocale;
    }

    @XmlElement(name = "transLocale", required = false,
        namespace = Namespaces.ZANATA_OLD)
    public List<GlossaryLocale> getTransLocale() {
        return transLocale;
    }

    public void setTransLocale(List<GlossaryLocale> transLocale) {
        this.transLocale = transLocale;
    }
}

