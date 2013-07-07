package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.IGenericTraitCollection;
import net.sf.anathema.character.main.ILimitationContext;
import net.sf.anathema.character.main.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.HashMap;
import java.util.Map;

public class DummyLimitationContext implements ILimitationContext {
  private final Map<TraitType, ValuedTraitType> traits = new HashMap<>();

  @Override
  public ITraitLimitation getEssenceLimitation() {
    return new StaticTraitLimitation(7);
  }

  @Override
  public CasteType getCasteType() {
    return null;
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return new IGenericTraitCollection() {

      @Override
      public ValuedTraitType getTrait(TraitType type) {
        return traits.get(type);
      }

      @Override
      public ValuedTraitType[] getTraits(TraitType[] traitTypes) {
        throw new NotYetImplementedException();
      }

      @Override
      public boolean isFavoredOrCasteTrait(TraitType type) {
        return getTrait(type).isCasteOrFavored();
      }
    };
  }

  @Override
  public int getAge() {
    return 0;
  }

  public void addTrait(ValuedTraitType trait) {
    traits.put(trait.getType(), trait);
  }
}