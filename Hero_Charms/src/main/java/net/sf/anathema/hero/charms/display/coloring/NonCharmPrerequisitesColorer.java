package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.prerequisite.IndirectCharmLearnPrerequisite;

public class NonCharmPrerequisitesColorer implements CharmColorer {
  private CharmColoring coloring;

  public NonCharmPrerequisitesColorer(CharmColoring coloring) {
    this.coloring = coloring;
  }

  public void color(Charm charm) {
    for (IndirectCharmLearnPrerequisite prerequisite : charm.getPrerequisitesOfType(IndirectCharmLearnPrerequisite.class)) {
      coloring.setPrerequisiteVisuals(prerequisite);
    }
  }
}