package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.IndirectCharmRequirement;

public interface CharmColoring {

  void colorCharm(ICharm charm);

  void setPrerequisiteVisuals(IndirectCharmRequirement requirement);
}