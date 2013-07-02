package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyGenericCharacter implements IGenericCharacter {

  private final HeroTemplate template;
  private final Map<TraitType, GenericTrait> traitsByType = new HashMap<>();

  public DummyGenericCharacter(HeroTemplate template) {
    this.template = template;
  }

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
  public IGenericDescription getDescription() {
    return null;
  }

  @Override
  public boolean isAlienCharm(ICharm charm) {
    return false;
  }

  @Override
  public HeroTemplate getTemplate() {
    return template;
  }

  @Override
  public Specialty[] getSpecialties(TraitType type) {
    return new Specialty[0];
  }

  @Override
  public int getHealthLevelTypeCount(HealthLevelType type) {
    return 0;
  }

  @Override
  public IConcept getConcept() {
    return new IConcept() {

      @Override
      public int getAge() {
        return 0;
      }

      @Override
      public CasteType getCasteType() {
        return CasteType.NULL_CASTE_TYPE;
      }
    };
  }

  @Override
  public List<IMagic> getAllLearnedMagic() {
    return new ArrayList<>();
  }

  @Override
  public int getLearnCount(ICharm charm) {
    return 0;
  }

  @Override
  public IGenericCombo[] getCombos() {
    return new IGenericCombo[0];
  }

  @Override
  public boolean isExperienced() {
    return false;
  }

  @Override
  public int getPainTolerance() {
    return 0;
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