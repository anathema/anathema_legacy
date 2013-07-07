package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.IndirectCharmRequirement;

public interface CharmColoring {

  void colorCharm(ICharm charm);

  void setPrerequisiteVisuals(IndirectCharmRequirement requirement);
}