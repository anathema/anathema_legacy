package net.sf.anathema.character.library.trait;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTraitCollection implements ITraitCollection {

  private final Map<ITraitType, ITrait> traitsByType = new HashMap<>();

  protected final ITrait[] getAllTraits() {
    Collection<ITrait> traits= traitsByType.values();
    return traits.toArray(new ITrait[traits.size()]);
  }

  @Override
  public ITrait getTrait(ITraitType traitType) {
    return traitsByType.get(traitType);
  }

  protected final boolean contains(ITraitType traitType) {
    return traitsByType.containsKey(traitType);
  }

  @Override
  public final ITrait[] getTraits(ITraitType[] traitTypes) {
    ITrait[] traits = new ITrait[traitTypes.length];
    for (int index = 0; index < traitTypes.length; index++) {
      traits[index] = getTrait(traitTypes[index]);
    }
    return traits;
  }

  @Override
  public IFavorableTrait getFavorableTrait(ITraitType traitType) {
    ITrait favorableTrait = getTrait(traitType);
    Preconditions.checkArgument(favorableTrait instanceof IFavorableTrait, "No favorable trait type " + traitType); //$NON-NLS-1$
    return (IFavorableTrait) favorableTrait;
  }

  @Override
  public IFavorableTrait[] getFavorableTraits(ITraitType[] traitTypes) {
    IFavorableTrait[] favorableTraits = new IFavorableTrait[traitTypes.length];
    for (int index = 0; index < favorableTraits.length; index++) {
      favorableTraits[index] = getFavorableTrait(traitTypes[index]);
    }
    return favorableTraits;
  }

  protected final void addTrait(ITrait trait) {
    Preconditions.checkArgument(!contains(trait.getType()), "Trait of type already contained " + trait.getType()); //$NON-NLS-1$
    traitsByType.put(trait.getType(), trait);
  }

  protected final void addTraits(ITrait[] traits) {
    for (ITrait trait : traits) {
      addTrait(trait);
    }
  }

  @Override
  public final boolean isFavoredOrCasteTrait(ITraitType type) {
    ITrait trait = getTrait(type);
    return trait instanceof IFavorableTrait && ((IFavorableTrait) trait).isCasteOrFavored();
  }
}