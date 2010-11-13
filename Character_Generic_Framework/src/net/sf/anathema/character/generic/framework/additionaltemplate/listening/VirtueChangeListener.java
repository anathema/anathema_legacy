package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public abstract class VirtueChangeListener extends ConfigurableCharacterChangeListener {

  public VirtueChangeListener() {
    ITraitType[] values = VirtueType.values();
    addTraitTypes(values);
  }

  @Override
  public abstract void configuredChangeOccured();
}
