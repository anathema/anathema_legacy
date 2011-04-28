package net.sf.anathema.dummy.character;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummyLimitationContext implements ILimitationContext {
  private final Map<ITraitType, IGenericTrait> traits = new HashMap<ITraitType, IGenericTrait>();

  public ITraitLimitation getEssenceLimitation() {
    return new StaticTraitLimitation(7);
  }

  public ICasteType getCasteType() {
    return null;
  }

  public IGenericTraitCollection getTraitCollection() {
    return new IGenericTraitCollection() {

      public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
        return (IFavorableGenericTrait) getTrait(type);
      }

      public IGenericTrait getTrait(ITraitType type) {
        return traits.get(type);
      }

      public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
        throw new NotYetImplementedException();
      }

      public boolean isFavoredOrCasteTrait(ITraitType type) {
        return getFavorableTrait(type).isCasteOrFavored();
      }

    };
  }
  
  public int getAge() {
    return 0;
  }

  public void addTrait(IGenericTrait trait) {
    traits.put(trait.getType(), trait);
  }
}