package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.display.presenter.AbstractCharmGroupChangeListener;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.presenter.CharmGroupArbitrator;
import net.sf.anathema.hero.charms.model.learn.ILearningCharmGroup;
import net.sf.anathema.lib.util.Identifier;

public class CharacterCharmGroupChangeListener extends AbstractCharmGroupChangeListener {

  public CharacterCharmGroupChangeListener(CharmGroupArbitrator arbitrator, CharmDisplayPropertiesMap displayPropertiesMap) {
    super(arbitrator, displayPropertiesMap);
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
