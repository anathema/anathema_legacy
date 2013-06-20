package net.sf.anathema.charmtree.presenter;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmtree.filters.CharmFilter;

import java.util.ArrayList;
import java.util.List;

public class CharmFilterSet {
  private List<CharmFilter> filterSet = new ArrayList<>();

  public void init(Iterable<CharmFilter> filters) {
    filterSet = Lists.newArrayList(filters);
  }

  public void resetAllFilters() {
    for (CharmFilter filter : filterSet) {
      filter.reset();
    }
  }

  public void applyAllFilters() {
    for (CharmFilter filter : filterSet) {
      filter.apply();
    }
  }

  public boolean acceptsCharm(ICharm charm) {
    for (CharmFilter filter : filterSet) {
      if (!filter.acceptsCharm(charm, false)) {
        return false;
      }
    }
    return true;
  }

  public boolean filterCharm(ICharm charm, boolean isAncestor) {
    for (CharmFilter filter : filterSet) {
      if (!filter.acceptsCharm(charm, isAncestor)) {
        return false;
      }
    }
    return true;
  }

  public List<CharmFilter> getAllFilters() {
    return filterSet;
  }
}