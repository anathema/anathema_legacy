package net.sf.anathema.character.generic.framework.additionaltemplate.listening;

import net.sf.anathema.character.generic.traits.TraitType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class ConfigurableCharacterChangeListener extends GlobalCharacterChangeAdapter {

  private final Set<TraitType> notifableTraitTypes = new HashSet<>();

  public final void addTraitTypes(TraitType... traitTypes) {
    Collections.addAll(notifableTraitTypes, traitTypes);
  }

  @Override
  public final void traitChanged(TraitType type) {
    if (notifableTraitTypes.contains(type)) {
      configuredChangeOccured();
    }
  }

  public abstract void configuredChangeOccured();
}