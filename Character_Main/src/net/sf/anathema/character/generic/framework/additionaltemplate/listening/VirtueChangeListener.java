package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public abstract class VirtueChangeListener extends ConfigurableCharacterChangeListener {

  public VirtueChangeListener() {
    TraitType[] values = VirtueType.values();
    addTraitTypes(values);
  }

  @Override
  public abstract void configuredChangeOccured();
}
