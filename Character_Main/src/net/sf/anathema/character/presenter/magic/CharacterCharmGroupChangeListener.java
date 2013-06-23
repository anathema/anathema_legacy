package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.charmtree.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.charmtree.view.CharmDisplayPropertiesMap;
import net.sf.anathema.charmtree.view.CharmTreeRenderer;
import net.sf.anathema.charmtree.view.ICharmGroupArbitrator;
import net.sf.anathema.lib.util.Identifier;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  public CharacterCharmGroupChangeListener(ICharmGroupArbitrator arbitrator, CharmTreeRenderer treeRenderer,
                                           CharmDisplayPropertiesMap displayPropertiesMap) {
    super(arbitrator, treeRenderer, displayPropertiesMap);
  }

  @Override
  protected void modifyCharmVisuals(Identifier type) {
    // Nothing to do
  }

  @Override
  public ILearningCharmGroup getCurrentGroup() {
    return (ILearningCharmGroup) super.getCurrentGroup();
  }
}
