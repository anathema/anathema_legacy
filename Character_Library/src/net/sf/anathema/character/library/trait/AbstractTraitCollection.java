package net.sf.anathema.character.library.trait;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;

public abstract class AbstractTraitCollection implements ITraitCollection {

  private final Map<ITraitType, ITrait> traitsByType = new HashMap<ITraitType, ITrait>();


  protected final ITrait[] getAllTraits() {
    Collection<ITrait> traits= traitsByType.values();
    return traits.toArray(new ITrait[traits.size()]);
  }

  
  public ITrait getTrait(ITraitType traitType) {
    return traitsByType.get(traitType);
  }

  protected final boolean contains(ITraitType traitType) {
    return traitsByType.containsKey(traitType);
  }

  public final ITrait[] getTraits(ITraitType[] traitTypes) {
    ITrait[] traits = new ITrait[traitTypes.length];
    for (int index = 0; index < traitTypes.length; index++) {
      traits[index] = getTrait(traitTypes[index]);
    }
    return traits;
  }

  public IFavorableTrait getFavorableTrait(ITraitType traitType) {
    ITrait favorableTrait = getTrait(traitType);
    Ensure.ensureArgumentTrue("No favorable trait type " + traitType, favorableTrait instanceof IFavorableTrait); //$NON-NLS-1$
    return (IFavorableTrait) favorableTrait;
  }

  public IFavorableTrait[] getFavorableTraits(ITraitType[] traitTypes) {
    IFavorableTrait[] favorableTraits = new IFavorableTrait[traitTypes.length];
    for (int index = 0; index < favorableTraits.length; index++) {
      favorableTraits[index] = getFavorableTrait(traitTypes[index]);
    }
    return favorableTraits;
  }

  protected final void addTrait(ITrait trait) {
    Ensure.ensureArgumentFalse("Trait of type already contained " + trait.getType(), contains(trait.getType())); //$NON-NLS-1$
    traitsByType.put(trait.getType(), trait);
  }

  protected final void addTraits(ITrait[] traits) {
    for (ITrait trait : traits) {
      addTrait(trait);
    }
  }

  public final boolean isFavoredOrCasteTrait(ITraitType type) {
    ITrait trait = getTrait(type);
    return trait instanceof IFavorableTrait && ((IFavorableTrait) trait).isCasteOrFavored();
  }
}