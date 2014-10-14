package org.zanata.rest.dto.stats.contribution;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.zanata.common.AbstractTranslationCount;
import org.zanata.common.LocaleId;
import org.zanata.common.TransUnitCount;
import org.zanata.rest.dto.DTOUtil;

/**
 * Map that hold user contribution data
 *
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 */
public class LocaleStatistics implements Serializable,
        Map<LocaleId, TransUnitCount> {

    private Map<LocaleId, TransUnitCount> localeStatsMap =
            new HashMap<LocaleId, TransUnitCount>();

    @Override
    public int size() {
        return localeStatsMap.size();
    }

    @Override
    public boolean isEmpty() {
        return localeStatsMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return localeStatsMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return localeStatsMap.containsValue(value);
    }

    @Override
    public TransUnitCount get(Object key) {
        return localeStatsMap.get(key);
    }

    @Override
    public TransUnitCount put(LocaleId key,
            TransUnitCount value) {
        return localeStatsMap.put(key, value);
    }

    @Override
    public TransUnitCount remove(Object key) {
        return localeStatsMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends LocaleId, ? extends TransUnitCount> m) {
        localeStatsMap.putAll(m);
    }

    @Override
    public void clear() {
        localeStatsMap.clear();
    }

    @Override
    public Set<LocaleId> keySet() {
        return localeStatsMap.keySet();
    }

    @Override
    public Collection<TransUnitCount> values() {
        return localeStatsMap.values();
    }

    @Override
    public Set<Entry<LocaleId, TransUnitCount>> entrySet() {
        return localeStatsMap.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocaleStatistics)) return false;

        LocaleStatistics that = (LocaleStatistics) o;

        if (localeStatsMap != null ?
            !localeStatsMap.equals(that.localeStatsMap) :
            that.localeStatsMap != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return localeStatsMap != null ? localeStatsMap.hashCode() : 0;
    }
}
