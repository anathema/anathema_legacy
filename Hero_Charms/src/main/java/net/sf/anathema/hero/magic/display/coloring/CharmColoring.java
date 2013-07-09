package net.sf.anathema.hero.magic.display.coloring;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;

public interface CharmColoring {

  void colorCharm(Charm charm);

  void setPrerequisiteVisuals(IndirectCharmRequirement requirement);
}