package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ICharacterPoints;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IdentifiedInteger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyGenericCharacter implements IGenericCharacter {

  private final ICharacterTemplate template;
  private final Map<ITraitType, IGenericTrait> traitsByType = new HashMap<ITraitType, IGenericTrait>();

  public DummyGenericCharacter(ICharacterTemplate template) {
    this.template = template;
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return new IGenericTraitCollection() {

      @Override
      public IGenericTrait getTrait(ITraitType type) {
        return traitsByType.get(type);
      }

      @Override
      public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
        throw new NotYetImplementedException();
      }

      @Override
      public boolean isFavoredOrCasteTrait(ITraitType type) {
        return getFavorableTrait(type).isCasteOrFavored();
      }

      @Override
      public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
        return (IFavorableGenericTrait) getTrait(type);
      }
    };
  }

  @Override
  public int getEssenceCap(boolean modified) {
    return 7;
  }

  public void addTrait(IGenericTrait trait) {
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
  public boolean isAlienCharm(ICharm charm) {
    return false;
  }

  @Override
  public ICharacterTemplate getTemplate() {
    return template;
  }

  @Override
  public INamedGenericTrait[] getSpecialties(ITraitType type) {
    return new INamedGenericTrait[0];
  }

  @Override
  public INamedGenericTrait[] getSubTraits(ITraitType traitType) {
    return new INamedGenericTrait[0];
  }

  @Override
  public ICasteType getCasteType() {
    return null;
  }

  @Override
  public IExaltedRuleSet getRules() {
    return ExaltedRuleSet.SecondEdition;
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
  public IGenericTrait[] getBackgrounds() {
    return new IGenericTrait[0];
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
      public String getWillpowerRegainingConceptName() {
        return null;
      }

      @Override
      public ICasteType getCasteType() {
        return ICasteType.NULL_CASTE_TYPE;
      }

      @Override
      public String getWillpowerRegainingComment(IResources resources) {
        return null;
      }
    };
  }

  @Override
  public List<IMagic> getAllLearnedMagic() {
    return new ArrayList<IMagic>();
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
  public String[] getUncompletedCelestialMartialArtsGroups() {
    return new String[0];
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
  public IIdentifiedTraitTypeGroup[] getYoziTypeGroups() {
    return null;
  }

  @Override
  public int getLearnCount(String charmName) {
    return 0;
  }

  @Override
  public IEquipmentModifiers getEquipmentModifiers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setLearnCount(String charmName, int newValue) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addSpecialtyListChangeListener(IChangeListener listener) {
    // TODO Auto-generated method stub
  }
}