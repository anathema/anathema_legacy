package net.sf.anathema.character.presenter.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;

import java.awt.*;

public final class CharmLearnVisualizer extends CharmLearnAdapter {
  private final CharacterCharmDye dye;

  public CharmLearnVisualizer(CharacterCharmDye dye) {
    this.dye = dye;
  }

  @Override
  public void charmLearned(ICharm charm) {
    dye.setCharmVisuals(charm);
  }

  @Override
  public void charmForgotten(ICharm charm) {
    dye.setCharmVisuals(charm);
  }

  @Override
  public void charmNotLearnable(ICharm charm) {
    Toolkit.getDefaultToolkit().beep();
  }

  @Override
  public void charmNotUnlearnable(ICharm charm) {
    Toolkit.getDefaultToolkit().beep();
  }
}