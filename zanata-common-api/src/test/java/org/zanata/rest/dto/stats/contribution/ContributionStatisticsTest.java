package org.zanata.rest.dto.stats.contribution;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.zanata.common.ContentState;
import org.zanata.common.LocaleId;
import org.zanata.common.TransUnitCount;
import org.zanata.rest.dto.DTOUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
public class ContributionStatisticsTest {

    @Test
    public void testJsonOutput() throws IOException {
        ContributionStatistics stats = generateData();
        String result = DTOUtil.<ContributionStatistics>toJSON(stats);

        assertThat(result, not(isEmptyOrNullString()));
        assertThat(result, StringContains.containsString("user1"));
        assertThat(result, StringContains.containsString(LocaleId.DE.toString()));
        assertThat(result, StringContains.containsString("10"));
    }

    private ContributionStatistics generateData() {
        TransUnitCount data = new TransUnitCount(40, 20, 30, 10, 50);
        LocaleStatistics localeStatistics = new LocaleStatistics();
        localeStatistics.put(LocaleId.DE, data);

        ContributionStatistics statistics = new ContributionStatistics();
        statistics.put("user1", localeStatistics);

        return statistics;
    }
}
