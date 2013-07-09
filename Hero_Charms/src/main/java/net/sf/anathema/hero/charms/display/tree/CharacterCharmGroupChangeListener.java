package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.display.view.charmtree.AbstractCharmGroupChangeListener;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmDisplayPropertiesMap;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmGroupArbitrator;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmTreeRenderer;
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
