package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.generic.traits.ITraitType;

public abstract class ConfigurableCharacterChangeListener extends GlobalCharacterChangeAdapter {

  private final Set<ITraitType> notifableTraitTypes = new HashSet<ITraitType>();

  public final void addTraitTypes(ITraitType... traitTypes) {
    Collections.addAll(notifableTraitTypes, traitTypes);
  }

  @Override
  public final void traitChanged(ITraitType type) {
    if (notifableTraitTypes.contains(type)) {
      configuredChangeOccured();
    }
  }

  public abstract void configuredChangeOccured();
}