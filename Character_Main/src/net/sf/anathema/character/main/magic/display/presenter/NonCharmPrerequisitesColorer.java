package net.sf.anathema.character.main.magic.display.presenter;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;
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