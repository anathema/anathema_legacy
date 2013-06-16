package net.sf.anathema.character.library.trait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.traits.ITraitType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTraitCollection implements ITraitCollection {

  private final Map<ITraitType, IDefaultTrait> traitsByType = new HashMap<>();

  protected final IDefaultTrait[] getAllTraits() {
    Collection<IDefaultTrait> traits = traitsByType.values();
    return traits.toArray(new IDefaultTrait[traits.size()]);
  }

  @Override
  public IDefaultTrait getTrait(ITraitType traitType) {
    return traitsByType.get(traitType);
  }

  protected final boolean contains(ITraitType traitType) {
    return traitsByType.containsKey(traitType);
  }

  @Override
  public final IDefaultTrait[] getTraits(ITraitType[] traitTypes) {
    IDefaultTrait[] traits = new IDefaultTrait[traitTypes.length];
    for (int index = 0; index < traitTypes.length; index++) {
      traits[index] = getTrait(traitTypes[index]);
    }
    return traits;
  }

  private final void addSingleTrait(IDefaultTrait trait) {
    Preconditions.checkArgument(!contains(trait.getType()), "Trait of type already contained " + trait.getType());
    traitsByType.put(trait.getType(), trait);
  }

  protected final void addTraits(IDefaultTrait... traits) {
    for (IDefaultTrait trait : traits) {
      addSingleTrait(trait);
    }
  }

  @Override
  public final boolean isFavoredOrCasteTrait(ITraitType type) {
    ITrait trait = getTrait(type);
    return trait.isCasteOrFavored();
  }
}