package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.charmtree.filters.ICharmFilter;

import java.util.List;

public interface CharmFilterContainer {

  void setCharmFilters(List<ICharmFilter> filters);

  List<ICharmFilter> getCharmFilters();
}
