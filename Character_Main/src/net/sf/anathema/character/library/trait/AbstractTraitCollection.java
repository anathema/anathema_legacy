package net.sf.anathema.character.library.trait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.traits.TraitType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTraitCollection implements TraitCollection {

  private final Map<TraitType, Trait> traitsByType = new HashMap<>();

  protected final Trait[] getAllTraits() {
    Collection<Trait> traits = traitsByType.values();
    return traits.toArray(new Trait[traits.size()]);
  }

  @Override
  public Trait getTrait(TraitType traitType) {
    return traitsByType.get(traitType);
  }

  protected final boolean contains(TraitType traitType) {
    return traitsByType.containsKey(traitType);
  }

  @Override
  public final Trait[] getTraits(TraitType... traitTypes) {
    Trait[] traits = new Trait[traitTypes.length];
    for (int index = 0; index < traitTypes.length; index++) {
      traits[index] = getTrait(traitTypes[index]);
    }
    return traits;
  }

  private final void addSingleTrait(Trait trait) {
    Preconditions.checkArgument(!contains(trait.getType()), "Trait of type already contained " + trait.getType());
    traitsByType.put(trait.getType(), trait);
  }

  public final void addTraits(Trait... traits) {
    for (Trait trait : traits) {
      addSingleTrait(trait);
    }
  }
}