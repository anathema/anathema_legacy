package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.generic.traits.ITraitType;

public abstract class ConfigurableCharacterChangeListener extends GlobalCharacterChangeAdapter {
  
  private final Set<ITraitType> notifableTraitTypes = new HashSet<ITraitType>();

  public final void addTraitType(ITraitType traitType) {
    notifableTraitTypes.add(traitType);
  }
  
  public final void addTraitTypes(Collection<ITraitType> traitTypes) {
    notifableTraitTypes.addAll(traitTypes);
  }
  
  @Override
  public final void traitChanged(ITraitType type) {
    if (notifableTraitTypes.contains(type)) {
      configuredChangeOccured();
    }
  }
  
  public abstract void configuredChangeOccured();
}