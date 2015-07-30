package org.zanata.rest.dto;

import java.io.Serializable;
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
@XmlRootElement(name = "glossaryLocale")
@XmlType(name = "glossaryLocaleType", propOrder = { "locale", "count" })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "locale", "count"})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GlossaryLocale implements Serializable {
    private LocaleDetails locale;
    private int count;

    public GlossaryLocale() {
        this(null, 0);
    }

    public GlossaryLocale(LocaleDetails locale, int count) {
        this.locale = locale;
        this.count = count;
    }

    @XmlElement(name = "locale", required = false,
        namespace = Namespaces.ZANATA_OLD)
    public LocaleDetails getLocale() {
        return locale;
    }

    public void setLocale(LocaleDetails locale) {
        this.locale = locale;
    }

    @XmlElement(name = "count", required = false,
        namespace = Namespaces.ZANATA_OLD)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
