package net.sf.anathema.charmtree.presenter;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.charmtree.filters.ICharmFilter;

import java.util.ArrayList;
import java.util.List;

public class CharmFilterSet {
  private List<ICharmFilter> filterSet = new ArrayList<>();

  public void init(Iterable<ICharmFilter> filters) {
    filterSet = Lists.newArrayList(filters);
  }

  public void resetAllFilters() {
    for (ICharmFilter filter : filterSet) {
      filter.reset();
    }
  }

  public void applyAllFilters() {
    for (ICharmFilter filter : filterSet) {
      filter.apply();
    }
  }

  public boolean acceptsCharm(ICharm charm) {
    for (ICharmFilter filter : filterSet) {
      if (!filter.acceptsCharm(charm, false)) {
        return false;
      }
    }
    return true;
  }

  public boolean filterCharm(ICharm charm, boolean isAncestor) {
    for (ICharmFilter filter : filterSet) {
      if (!filter.acceptsCharm(charm, isAncestor)) {
        return false;
      }
    }
    return true;
  }

  public List<ICharmFilter> getAllFilters() {
    return filterSet;
  }
}