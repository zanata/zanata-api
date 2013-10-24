package org.zanata.common.statistic;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
public class WordsStatistic extends AbstractStatistic {

    private double remainingHours;

    public WordsStatistic() {
        super();
    }

    public WordsStatistic(int approved, int needReview, int untranslated,
        int translated, int rejected) {
        super(approved, needReview, untranslated, translated, rejected);
    }

    public void setRemainingHours(double remainingHours) {
        this.remainingHours = remainingHours;
    }

    public double getRemainingHours() {
        return remainingHours;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("translated-" + getTranslated());
        sb.append("\nneedReview-" + getNeedReview());
        sb.append("\nuntranslated-" + getUntranslated());
        sb.append("\ntotal-" + getTotal());
        sb.append("\nremainingHours-" + getRemainingHours());

        return sb.toString();
    }
}
