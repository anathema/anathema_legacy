package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.HashMap;
import java.util.Map;

public class DummyLimitationContext implements ILimitationContext {
  private final Map<TraitType, GenericTrait> traits = new HashMap<>();

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
      public GenericTrait getTrait(TraitType type) {
        return traits.get(type);
      }

      @Override
      public GenericTrait[] getTraits(TraitType[] traitTypes) {
        throw new NotYetImplementedException();
      }

      @Override
      public boolean isFavoredOrCasteTrait(TraitType type) {
        return getTrait(type).isCasteOrFavored();
      }
    };
  }

  @Override
  public int getEssenceCap(boolean modified) {
    return 0;
  }

  @Override
  public int getAge() {
    return 0;
  }

  public void addTrait(GenericTrait trait) {
    traits.put(trait.getType(), trait);
  }
}