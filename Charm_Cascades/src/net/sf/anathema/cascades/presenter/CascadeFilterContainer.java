package net.sf.anathema.cascades.presenter;

import com.google.common.collect.Lists;
import net.sf.anathema.character.presenter.charm.CascadeSourceBookFilter;
import net.sf.anathema.character.presenter.magic.EssenceLevelCharmFilter;
import net.sf.anathema.character.presenter.magic.SourceBookCharmFilter;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.CharmFilterContainer;

import java.util.List;

public class CascadeFilterContainer implements CharmFilterContainer {

  private EditionCharmGroups charmGroups;

  public CascadeFilterContainer(EditionCharmGroups charmGroups) {
    this.charmGroups = charmGroups;
  }

  @Override
  public List<ICharmFilter> getCharmFilters() {
    SourceBookCharmFilter sourceFilter = new CascadeSourceBookFilter(charmGroups);
    EssenceLevelCharmFilter essenceLevelFilter = new EssenceLevelCharmFilter();
    return Lists.newArrayList(sourceFilter, essenceLevelFilter);
  }
}
