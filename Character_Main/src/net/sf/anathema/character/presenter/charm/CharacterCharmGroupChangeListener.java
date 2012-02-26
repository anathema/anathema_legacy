package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.filters.ICharmFilter;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.charmtree.presenter.view.CharmTreeRenderer;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.List;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  public CharacterCharmGroupChangeListener(ICharmGroupArbitrator arbitrator, List<ICharmFilter> charmFilterSet,
                                           IExaltedEdition edition, CharmTreeRenderer treeRenderer,
                                           CharmDisplayPropertiesMap displayPropertiesMap) {
    super(arbitrator, charmFilterSet, edition, treeRenderer, displayPropertiesMap);
  }

  @Override
  protected void modifyCharmVisuals(IIdentificate type) {
    // Nothing to do
  }

  @Override
  public ILearningCharmGroup getCurrentGroup() {
    return (ILearningCharmGroup) super.getCurrentGroup();
  }
}