package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.presenter.magic.CharmColoring;

public class SimpleCharmColorer implements CharmColorer {
  private final CharmColoring coloring;

  public SimpleCharmColorer(CharmColoring coloring) {
    this.coloring = coloring;
  }

  public void color(ICharm charm) {
    coloring.colorCharm(charm);
  }
}