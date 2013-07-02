package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyGenericCharacter implements IGenericCharacter {

  private final Map<TraitType, GenericTrait> traitsByType = new HashMap<>();

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return new IGenericTraitCollection() {

      @Override
      public GenericTrait getTrait(TraitType type) {
        return traitsByType.get(type);
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
  public boolean isLearned(IMagic magic) {
    return false;
  }

  @Override
  public Specialty[] getSpecialties(TraitType type) {
    return new Specialty[0];
  }

  @Override
  public List<IMagic> getAllLearnedMagic() {
    return new ArrayList<>();
  }

  @Override
  public String[] getLearnedEffects(ICharm charm) {
    return new String[0];
  }

  @Override
  public boolean isMultipleEffectCharm(ICharm magic) {
    return false;
  }

  @Override
  public ICharm[] getGenericCharms() {
    return new ICharm[0];
  }
}