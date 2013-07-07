package net.sf.anathema.hero.magic.display.coloring;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;

public class NonCharmPrerequisitesColorer implements CharmColorer {
  private CharmColoring coloring;

  public NonCharmPrerequisitesColorer(CharmColoring coloring) {
    this.coloring = coloring;
  }

  public void color(ICharm charm) {
    for (IndirectCharmRequirement requirement : charm.getIndirectRequirements()) {
      coloring.setPrerequisiteVisuals(requirement);
    }
  }
}