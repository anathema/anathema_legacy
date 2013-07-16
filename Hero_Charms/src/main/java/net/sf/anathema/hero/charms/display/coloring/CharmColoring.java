package net.sf.anathema.hero.charms.display.coloring;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.IndirectCharmRequirement;

public interface CharmColoring {

  void colorCharm(Charm charm);

  void setPrerequisiteVisuals(IndirectCharmRequirement requirement);
}