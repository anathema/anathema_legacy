package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.main.magic.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.charmtree.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.character.main.magic.charmtree.view.CharmDisplayPropertiesMap;
import net.sf.anathema.character.main.magic.charmtree.view.CharmGroupArbitrator;
import net.sf.anathema.character.main.magic.charmtree.view.CharmTreeRenderer;
import net.sf.anathema.lib.util.Identifier;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  public CharacterCharmGroupChangeListener(CharmGroupArbitrator arbitrator, CharmTreeRenderer treeRenderer,
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
