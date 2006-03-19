package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public abstract class VirtueChangeListener extends ConfigurableCharacterChangeListener {

  public VirtueChangeListener() {
    List<ITraitType> virtueTypes = new ArrayList<ITraitType>();
    Collections.addAll(virtueTypes, VirtueType.values());
    addTraitTypes(virtueTypes);
  }

  @Override
  public abstract void configuredChangeOccured();
}
