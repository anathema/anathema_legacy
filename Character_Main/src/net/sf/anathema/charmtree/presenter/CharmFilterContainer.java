package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.charmtree.filters.ICharmFilter;

import java.util.List;

public interface CharmFilterContainer {

  List<ICharmFilter> getCharmFilters();
}