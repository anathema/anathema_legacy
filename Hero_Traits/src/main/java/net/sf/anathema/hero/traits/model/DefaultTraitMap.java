package net.sf.anathema.hero.traits.model;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.traits.TraitMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultTraitMap implements TraitMap {

  private final Map<TraitType, Trait> traitsByType = new HashMap<>();

  public final void addTraits(Trait... traits) {
    for (Trait trait : traits) {
      addSingleTrait(trait);
    }
  }

  private void addSingleTrait(Trait trait) {
    Preconditions.checkArgument(!contains(trait.getType()), "Trait of type already contained " + trait.getType());
    traitsByType.put(trait.getType(), trait);
  }

  @Override
  public final Trait getTrait(TraitType traitType) {
    if (contains(traitType)) {
      return traitsByType.get(traitType);
    }
    throw new UnsupportedOperationException("Unsupported trait type " + traitType);
  }

  @Override
  public final Trait[] getTraits(TraitType... traitTypes) {
    List<Trait> foundTraits = new ArrayList<>();
    for (TraitType type : traitTypes) {
      foundTraits.add(getTrait(type));
    }
    return foundTraits.toArray(new Trait[foundTraits.size()]);
  }

  public final Trait[] getAll() {
    Collection<Trait> attributes = traitsByType.values();
    return attributes.toArray(new Trait[attributes.size()]);
  }

  public final boolean contains(TraitType traitType) {
    return traitsByType.containsKey(traitType);
  }
}