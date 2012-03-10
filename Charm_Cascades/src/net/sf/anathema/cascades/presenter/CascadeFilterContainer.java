package net.sf.anathema.cascades.presenter;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.presenter.charm.CascadeSourceBookFilter;
import net.sf.anathema.character.presenter.charm.EssenceLevelCharmFilter;
import net.sf.anathema.character.presenter.charm.SourceBookCharmFilter;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.CharmFilterContainer;

import java.util.List;

public class CascadeFilterContainer implements CharmFilterContainer {

  private IExaltedRuleSet ruleSet;

  public CascadeFilterContainer(IExaltedRuleSet ruleSet) {
    this.ruleSet = ruleSet;
  }

  @Override
  public List<ICharmFilter> getCharmFilters() {
    SourceBookCharmFilter sourceFilter = new CascadeSourceBookFilter(ruleSet);
    EssenceLevelCharmFilter essenceLevelFilter = new EssenceLevelCharmFilter();
    return Lists.newArrayList(sourceFilter, essenceLevelFilter);
  }
}