package net.sf.anathema.character.main.testing.dummy;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.IdentifiedInteger;

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
  public int getEssenceCap(boolean modified) {
    return 7;
  }

  public void addTrait(GenericTrait trait) {
    traitsByType.put(trait.getType(), trait);
  }

  @Override
  public int getLearnCount(IMultiLearnableCharm charm) {
    return 0;
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
  public ICasteType getCasteType() {
    return null;
  }

  @Override
  public int getHealthLevelTypeCount(HealthLevelType type) {
    return 0;
  }

  @Override
  public String getPeripheralPool() {
    return null;
  }

  @Override
  public int getPeripheralPoolValue() {
    return 0;
  }

  @Override
  public String getPersonalPool() {
    return null;
  }

  @Override
  public int getPersonalPoolValue() {
    return 0;
  }

  @Override
  public int getOverdrivePoolValue() {
    return 0;
  }

  @Override
  public IdentifiedInteger[] getComplexPools() {
    return new IdentifiedInteger[0];
  }

  @Override
  public int getAttunedPoolValue() {
    return 0;
  }

  @Override
  public IAdditionalModel getAdditionalModel(String templateId) {
    return null;
  }

  @Override
  public IConcept getConcept() {
    return new IConcept() {

      @Override
      public int getAge() {
        return 0;
      }

      @Override
      public ICasteType getCasteType() {
        return ICasteType.NULL_CASTE_TYPE;
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
  public ITraitLimitation getEssenceLimitation() {
    return new StaticTraitLimitation(7);
  }

  @Override
  public int getAge() {
    return 0;
  }

  @Override
  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
    // Nothing to do
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return new IIdentifiedTraitTypeGroup[0];
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups() {
    return new IIdentifiedTraitTypeGroup[0];
  }

  @Override
  public int getSpentExperiencePoints() {
    return 0;
  }

  @Override
  public int getTotalExperiencePoints() {
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
  public boolean isSubeffectCharm(ICharm magic) {
    return false;
  }

  @Override
  public ICharm[] getLearnedCharms() {
    return new ICharm[0];
  }

  @Override
  public ICharm[] getGenericCharms() {
    return new ICharm[0];
  }

  @Override
  public int getLearnCount(String charmName) {
    return 0;
  }

  @Override
  public void setLearnCount(String charmName, int newValue) {
    // nothing to do
  }

  @Override
  public void addSpecialtyListChangeListener(IChangeListener listener) {
    // nothing to do
  }

  @Override
  public <T> List<T> getAllRegistered(Class<T> interfaceClass) {
    return Lists.newArrayList();
  }
}