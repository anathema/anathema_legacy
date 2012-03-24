package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.presenter.CharmFilterSet;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.charmtree.presenter.view.CharmTreeRenderer;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.util.IIdentificate;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  public CharacterCharmGroupChangeListener(ICharmGroupArbitrator arbitrator, CharmFilterSet charmFilterSet,
                                           CharmTreeRenderer treeRenderer,
                                           CharmDisplayPropertiesMap displayPropertiesMap) {
    super(arbitrator, charmFilterSet, treeRenderer, displayPropertiesMap);
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
