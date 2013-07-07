package net.sf.anathema.character.main.magic.display.presenter;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.presenter.magic.CharmColoring;

public class SimpleCharmColorer implements CharmColorer {
  private final CharmColoring coloring;

  public SimpleCharmColorer(CharmColoring coloring) {
    this.coloring = coloring;
  }

  public void color(ICharm charm) {
    coloring.colorCharm(charm);
  }
}