package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.character.main.magic.model.charm.Charm;

public class SimpleCharmColorer implements CharmColorer {
  private final CharmColoring coloring;

  public SimpleCharmColorer(CharmColoring coloring) {
    this.coloring = coloring;
  }

  public void color(Charm charm) {
    coloring.colorCharm(charm);
  }
}