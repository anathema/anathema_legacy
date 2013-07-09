package net.sf.anathema.hero.magic.display.tree;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmLearnAdapter;
import net.sf.anathema.hero.magic.display.coloring.CharmDye;

import java.awt.Toolkit;

public final class CharmLearnVisualizer extends CharmLearnAdapter {
  private final CharmDye dye;

  public CharmLearnVisualizer(CharmDye dye) {
    this.dye = dye;
  }

  @Override
  public void charmLearned(Charm charm) {
    dye.colorCharm(charm);
  }

  @Override
  public void charmForgotten(Charm charm) {
    dye.colorCharm(charm);
  }

  @Override
  public void charmNotLearnable(Charm charm) {
    Toolkit.getDefaultToolkit().beep();
  }

  @Override
  public void charmNotUnlearnable(Charm charm) {
    Toolkit.getDefaultToolkit().beep();
  }
}
