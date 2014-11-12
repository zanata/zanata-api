package org.zanata.rest.dto.stats.contribution;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.zanata.common.BaseTranslationCount;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
@JsonIgnoreProperties(value = {"untranslated", "total"})
public class BaseContributionStatistic extends BaseTranslationCount {

    public BaseContributionStatistic() {
        super();
    }

    public BaseContributionStatistic(int approved, int needReview,
        int translated, int rejected) {
        super(approved, needReview, 0, translated, rejected);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof BaseTranslationCount) {
            BaseTranslationCount o = (BaseTranslationCount) obj;
            return (getApproved() == o.getApproved() && getNeedReview() == o.getNeedReview()
                && getTranslated() == o.getTranslated() && getRejected() == o.getRejected());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = getApproved();
        result = 31 * result + getNeedReview();
        result = 31 * result + getTranslated();
        result = 31 * result + getRejected();
        return result;
    }
}
