package net.sf.anathema.cascades.presenter;

import com.google.common.collect.Lists;
import net.sf.anathema.character.presenter.charm.CascadeSourceBookFilter;
import net.sf.anathema.character.presenter.magic.EssenceLevelCharmFilter;
import net.sf.anathema.character.presenter.magic.SourceBookCharmFilter;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.CharmFilterContainer;
import net.sf.anathema.charmtree.presenter.CharmGroupCollection;

import java.util.List;

public class CascadeFilterContainer implements CharmFilterContainer {

  private CharmGroupCollection charmGroups;

  public CascadeFilterContainer(CharmGroupCollection charmGroups) {
    this.charmGroups = charmGroups;
  }

  @Override
  public List<ICharmFilter> getCharmFilters() {
    SourceBookCharmFilter sourceFilter = new CascadeSourceBookFilter(charmGroups);
    EssenceLevelCharmFilter essenceLevelFilter = new EssenceLevelCharmFilter();
    return Lists.newArrayList(sourceFilter, essenceLevelFilter);
  }
}
