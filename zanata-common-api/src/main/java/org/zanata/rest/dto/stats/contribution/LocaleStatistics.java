package org.zanata.rest.dto.stats.contribution;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "translation-stats", "review-stats" })
public class LocaleStatistics {

    private BaseContributionStatistic translationStats;

    private BaseContributionStatistic reviewStats;

    public LocaleStatistics() {
        this(null, null);
    }

    public LocaleStatistics(
            BaseContributionStatistic translationStats,
            BaseContributionStatistic reviewStats) {
        this.translationStats = translationStats;
        this.reviewStats = reviewStats;
    }

    @JsonProperty("translation-stats")
    public BaseContributionStatistic getTranslationStats() {
        return translationStats;
    }

    @JsonProperty("review-stats")
    public BaseContributionStatistic getReviewStats() {
        return reviewStats;
    }

    public void setTranslationStats(
            BaseContributionStatistic translationStats) {
        this.translationStats = translationStats;
    }

    public void setReviewStats(
            BaseContributionStatistic reviewStats) {
        this.reviewStats = reviewStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LocaleStatistics))
            return false;

        LocaleStatistics that = (LocaleStatistics) o;

        if (translationStats != null
                ? !translationStats.equals(that.translationStats)
                : that.translationStats != null)
            return false;
        return !(reviewStats != null ? !reviewStats.equals(that.reviewStats)
                : that.reviewStats != null);

    }

    @Override
    public int hashCode() {
        int result = translationStats != null ? translationStats.hashCode() : 0;
        result =
                31 * result
                        + (reviewStats != null ? reviewStats.hashCode() : 0);
        return result;
    }
}
