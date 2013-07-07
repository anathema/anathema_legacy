package net.sf.anathema.charmtree.presenter;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;
import net.sf.anathema.character.main.presenter.magic.CharmColoring;

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