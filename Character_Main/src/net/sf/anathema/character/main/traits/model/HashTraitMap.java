package net.sf.anathema.character.main.traits.model;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.Trait;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HashTraitMap implements TraitMap{

  private final Map<ITraitType, Trait> traitsByType = new HashMap<>();

  public void addTrait(Trait trait){
    Preconditions.checkArgument(!contains(trait.getType()), "Trait of type already contained " + trait.getType());
    traitsByType.put(trait.getType(), trait);
  }

  private final boolean contains(ITraitType traitType) {
    return traitsByType.containsKey(traitType);
  }

  @Override
  public Trait getTrait(ITraitType traitType) {
    return traitsByType.get(traitType);
  }

  public Trait[] getAll() {
    Collection<Trait> attributes = traitsByType.values();
    return attributes.toArray(new Trait[attributes.size()]);
  }
}
