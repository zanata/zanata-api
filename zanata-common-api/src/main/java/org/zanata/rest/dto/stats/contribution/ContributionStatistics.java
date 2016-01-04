package org.zanata.rest.dto.stats.contribution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.zanata.common.LocaleId;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "username", "contributions" })
public class ContributionStatistics implements Serializable {

    private String username;

    private Map<LocaleId, LocaleStatistics> contributions =
            new HashMap<LocaleId, LocaleStatistics>();

    public ContributionStatistics() {
    }

    public ContributionStatistics(String username,
            Map<LocaleId, LocaleStatistics> contributions) {
        this.username = username;
        this.contributions = contributions;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("contributions")
    public Map<LocaleId, LocaleStatistics> getContributions() {
        if (contributions == null) {
            contributions = new HashMap<LocaleId, LocaleStatistics>();
        }
        return contributions;
    }

    public void
            setContributions(Map<LocaleId, LocaleStatistics> contributions) {
        this.contributions = contributions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ContributionStatistics))
            return false;

        ContributionStatistics that = (ContributionStatistics) o;

        if (username != null ? !username.equals(that.username)
                : that.username != null)
            return false;
        return !(contributions != null
                ? !contributions.equals(that.contributions)
                : that.contributions != null);

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result =
                31 * result +
                        (contributions != null ? contributions.hashCode() : 0);
        return result;
    }
}
