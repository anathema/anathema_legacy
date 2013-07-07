package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.IndirectCharmRequirement;

public interface CharmColoring {

  void colorCharm(ICharm charm);

  void setPrerequisiteVisuals(IndirectCharmRequirement requirement);
}