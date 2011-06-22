package net.sf.anathema.development.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ICharacterPoints;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.intimacies.template.IntimaciesTemplate;
import net.sf.anathema.character.solar.caste.SolarCaste;
import net.sf.anathema.dummy.character.additional.DemoEquipmentAdditionalModel;
import net.sf.anathema.dummy.character.additional.DemoIntimaciesModel;
import net.sf.anathema.dummy.character.trait.DummyFavorableGenericTrait;
import net.sf.anathema.dummy.character.trait.DummyGenericTrait;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DemoGenericCharacter implements IGenericCharacter {

  private final List<ITraitType> specialtyTraitTypes = new ArrayList<ITraitType>();
  private final List<ITraitType> subbedTraitTypes = new ArrayList<ITraitType>();
  private final List<IMagic> allLearnedMagic = new ArrayList<IMagic>();
  private final List<IGenericTrait> allBackgrounds = new ArrayList<IGenericTrait>();
  private final List<IGenericCombo> allCombos = new ArrayList<IGenericCombo>();
  private final DemoCharacterTemplate characterTemplate;
  private final DemoGenericDescription description = new DemoGenericDescription();
  private final DemoConcept concept = new DemoConcept();
  private final Map<String, IAdditionalModel> additionalModels = new HashMap<String, IAdditionalModel>();
  private final DemoEquipmentAdditionalModel equipmentModel = new DemoEquipmentAdditionalModel();
  private final DemoIntimaciesModel intimaciesModel = new DemoIntimaciesModel();
  private IExaltedRuleSet ruleSet;
  private int painTolerance = 0;
  private int totalExperiencePoints = 500;
  private int spentExperiencePoints = 125;

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return new IGenericTraitCollection() {
      @Override
      public IGenericTrait getTrait(ITraitType type) {
        return new DummyGenericTrait(type, 1);
      }

      @Override
      public IGenericTrait[] getTraits(ITraitType[] traitTypes) {
        throw new NotYetImplementedException();
      }

      @Override
      public boolean isFavoredOrCasteTrait(ITraitType type) {
        return false;
      }

      @Override
      public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
        boolean isFavored = net.sf.anathema.lib.random.RandomUtilities.nextPercent() < 50;
        return new DummyFavorableGenericTrait(type, 2, isFavored);
      }
    };
  }

  public DemoGenericCharacter(CharacterType characterType) {
    this.characterTemplate = new DemoCharacterTemplate(characterType);
  }

  @Override
  public IAdditionalModel getAdditionalModel(String templateId) {
    if (templateId.equals(IEquipmentAdditionalModelTemplate.ID)) {
      return equipmentModel;
    }
    if (templateId.equals(IntimaciesTemplate.ID)) {
      return intimaciesModel;
    }
    return additionalModels.get(templateId);
  }

  @Override
  public List<IMagic> getAllLearnedMagic() {
    return allLearnedMagic;
  }

  @Override
  public IGenericTrait[] getBackgrounds() {
    return allBackgrounds.toArray(new IGenericTrait[allBackgrounds.size()]);
  }

  @Override
  public ICharacterPoints getCharacterPoints() {
    return null;
  }

  public void addCombo(IGenericCombo combo) {
    allCombos.add(combo);
  }
  
  @Override
public int getEssenceCap(boolean modified)
  {
	  return 0;
  }

  @Override
  public IGenericCombo[] getCombos() {
    return allCombos.toArray(new IGenericCombo[allCombos.size()]);
  }

  @Override
  public DemoConcept getConcept() {
    return concept;
  }

  @Override
  public int getHealthLevelTypeCount(HealthLevelType type) {
    return 10 + type.getIntValue();
  }

  @Override
  public int getLearnCount(ICharm charm) {
    return 0;
  }

  @Override
  public int getPainTolerance() {
    return painTolerance;
  }

  public void setPainTolerance(int painTolerance) {
    this.painTolerance = painTolerance;
  }

  @Override
  public String getPeripheralPool() {
    return "25"; //$NON-NLS-1$
  }

  @Override
  public String getPersonalPool() {
    return "10"; //$NON-NLS-1$
  }

  @Override
  public IExaltedRuleSet getRules() {
    return ruleSet;
  }

  public void setRuleSet(IExaltedRuleSet ruleSet) {
    this.ruleSet = ruleSet;
  }

  @Override
  public INamedGenericTrait[] getSpecialties(ITraitType traitType) {
    if (specialtyTraitTypes.contains(traitType)) {
      return new INamedGenericTrait[] { new DemoNamedGenericTrait(traitType, 3, " (Specialty)") };
    }
    return new INamedGenericTrait[0];
  }

  @Override
  public INamedGenericTrait[] getSubTraits(ITraitType traitType) {
    if (subbedTraitTypes.contains(traitType)) {
      return new INamedGenericTrait[] { new DemoNamedGenericTrait(traitType, 2, " (Sub)") };
    }
    return new INamedGenericTrait[0];
  }

  @Override
  public DemoCharacterTemplate getTemplate() {
    return characterTemplate;
  }

  @Override
  public String[] getUncompletedCelestialMartialArtsGroups() {
    return new String[0];
  }

  @Override
  public boolean isAlienCharm(ICharm charm) {
    return false;
  }

  @Override
  public boolean isExperienced() {
    return false;
  }

  @Override
  public boolean isLearned(IMagic magic) {
    return false;
  }

  @Override
  public ICasteType getCasteType() {
    return ICasteType.NULL_CASTE_TYPE;
  }

  @Override
  public ITraitLimitation getEssenceLimitation() {
    return null;
  }
  
  @Override
  public int getAge() {
    return 0;
  }

  @Override
  public int getLearnCount(IMultiLearnableCharm multiLearnableCharm) {
    return 0;
  }

  @Override
  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
    throw new UnsupportedOperationException();
  }

  public DemoGenericDescription getDescription() {
    return description;
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return new IIdentifiedTraitTypeGroup[] {
        new IdentifiedTraitTypeGroup(new ITraitType[] {
            AbilityType.Archery,
            AbilityType.MartialArts,
            AbilityType.Melee,
            AbilityType.Thrown,
            AbilityType.War }, SolarCaste.Dawn),
        new IdentifiedTraitTypeGroup(new ITraitType[] {
            AbilityType.Integrity,
            AbilityType.Performance,
            AbilityType.Presence,
            AbilityType.Resistance,
            AbilityType.Survival }, SolarCaste.Zenith),
        new IdentifiedTraitTypeGroup(new ITraitType[] {
            AbilityType.Craft,
            AbilityType.Investigation,
            AbilityType.Lore,
            AbilityType.Medicine,
            AbilityType.Occult }, SolarCaste.Twilight),
        new IdentifiedTraitTypeGroup(new ITraitType[] {
            AbilityType.Athletics,
            AbilityType.Awareness,
            AbilityType.Dodge,
            AbilityType.Larceny,
            AbilityType.Stealth }, SolarCaste.Night),
        new IdentifiedTraitTypeGroup(new ITraitType[] {
            AbilityType.Bureaucracy,
            AbilityType.Linguistics,
            AbilityType.Ride,
            AbilityType.Sail,
            AbilityType.Socialize }, SolarCaste.Eclipse) };
  }
  
  @Override
  public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups()
  {
	  return null;
  }
  
  @Override
  public IIdentifiedTraitTypeGroup[] getYoziTypeGroups()
  {
	  return null;
  }

  public void addSpecialtyTrait(ITraitType traitType) {
    specialtyTraitTypes.add(traitType);
  }

  public void addSubbedTrait(ITraitType traitType) {
    subbedTraitTypes.add(traitType);
  }

  public DemoIntimaciesModel getIntimaciesModel() {
    return intimaciesModel;
  }

  public DemoEquipmentAdditionalModel getEquipmentModel() {
    return equipmentModel;
  }

  public void addAdditionalModel(IAdditionalModel model) {
    additionalModels.put(model.getTemplateId(), model);
  }

  @Override
  public int getSpentExperiencePoints() {
    return spentExperiencePoints;
  }

  @Override
  public int getTotalExperiencePoints() {
    return totalExperiencePoints;
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
  public IMagicStats[] getGenericCharmStats() {
    return new IMagicStats[0];
  }

  @Override
  public ICharm[] getLearnedCharms() {
    return new ICharm[0];
  }
}