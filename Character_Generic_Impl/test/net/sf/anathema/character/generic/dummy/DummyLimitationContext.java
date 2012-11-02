package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.HashMap;
import java.util.Map;

public class DummyLimitationContext implements ILimitationContext {
  private final Map<ITraitType, IGenericTrait> traits = new HashMap<>();

  @Override
  public ITraitLimitation getEssenceLimitation() {
    return new StaticTraitLimitation(7);
  }

  @Override
  public ICasteType getCasteType() {
    return null;
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return new IGenericTraitCollection() {

      @Override
      public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
        return (IFavorableGenericTrait) getTrait(type);
      }

      @Override
      public IGenericTrait getTrait(ITraitType type) {
        return traits.get(type);
      }

      @Override
      public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
        throw new NotYetImplementedException();
      }

      @Override
      public boolean isFavoredOrCasteTrait(ITraitType type) {
        return getFavorableTrait(type).isCasteOrFavored();
      }

    };
  }
  
  @Override
  public int getEssenceCap(boolean modified)
  {
	  return 0;
  }
  
  @Override
  public int getAge() {
    return 0;
  }

  public void addTrait(IGenericTrait trait) {
    traits.put(trait.getType(), trait);
  }
}