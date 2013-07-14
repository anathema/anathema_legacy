package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.display.presenter.AbstractCharmGroupChangeListener;
import net.sf.anathema.hero.charms.display.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.presenter.CharmGroupArbitrator;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.TreeRenderer;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  public CharacterCharmGroupChangeListener(CharmGroupArbitrator arbitrator, TreeRenderer treeRenderer,
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
