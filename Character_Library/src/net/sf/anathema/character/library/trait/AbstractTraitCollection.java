package net.sf.anathema.character.library.trait;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.traits.ITraitType;

public abstract class AbstractTraitCollection implements ITraitCollection {

  private final Map<ITraitType, IModifiableTrait> traitsByType = new HashMap<ITraitType, IModifiableTrait>();

  public IModifiableTrait getTrait(ITraitType traitType) {
    return traitsByType.get(traitType);
  }

  protected final boolean contains(ITraitType traitType) {
    return traitsByType.containsKey(traitType);
  }

  public final IModifiableTrait[] getTraits(ITraitType[] traitTypes) {
    IModifiableTrait[] traits = new IModifiableTrait[traitTypes.length];
    for (int index = 0; index < traitTypes.length; index++) {
      traits[index] = getTrait(traitTypes[index]);
    }
    return traits;
  }

  public IFavorableModifiableTrait getFavorableTrait(ITraitType traitType) {
    IModifiableTrait favorableTrait = getTrait(traitType);
    Ensure.ensureArgumentTrue("No favorable trait type " + traitType, favorableTrait instanceof IFavorableModifiableTrait); //$NON-NLS-1$
    return (IFavorableModifiableTrait) favorableTrait;
  }

  public IFavorableModifiableTrait[] getFavorableTraits(ITraitType[] traitTypes) {
    IFavorableModifiableTrait[] favorableTraits = new IFavorableModifiableTrait[traitTypes.length];
    for (int index = 0; index < favorableTraits.length; index++) {
      favorableTraits[index] = getFavorableTrait(traitTypes[index]);
    }
    return favorableTraits;
  }

  protected final void addTrait(IModifiableTrait trait) {
    Ensure.ensureArgumentFalse("Trait of type already contained " + trait.getType(), contains(trait.getType())); //$NON-NLS-1$
    traitsByType.put(trait.getType(), trait);
  }

  protected final void addTraits(IModifiableTrait[] traits) {
    for (IModifiableTrait trait : traits) {
      addTrait(trait);
    }
  }

  public final boolean isFavoredOrCasteTrait(ITraitType type) {
    IModifiableTrait trait = getTrait(type);
    return trait instanceof IFavorableModifiableTrait && ((IFavorableModifiableTrait) trait).isCasteOrFavored();
  }
}