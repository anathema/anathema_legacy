package net.sf.anathema.development.character;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.ICharacterPoints;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.xml.trait.alternate.test.DummyFavorableGenericTrait;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.impl.testing.DummyGenericTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
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
import net.sf.anathema.character.solar.caste.SolarCaste;

public class DemoGenericCharacter implements IGenericCharacter {

  private final List<ITraitType> specialtyTraitTypes = new ArrayList<ITraitType>();
  private final List<ITraitType> subbedTraitTypes = new ArrayList<ITraitType>();
  private final List<IMagic> allLearnedMagic = new ArrayList<IMagic>();
  private final List<IGenericTrait> allBackgrounds = new ArrayList<IGenericTrait>();
  private final List<IGenericCombo> allCombos = new ArrayList<IGenericCombo>();
  private final DemoCharacterTemplate characterTemplate;
  private final DemoGenericDescription description = new DemoGenericDescription();
  private final DemoConcept concept = new DemoConcept();
  private IExaltedRuleSet ruleSet;
  
  public DemoGenericCharacter(CharacterType characterType) {
    this.characterTemplate = new DemoCharacterTemplate(characterType);
  }

  public IAdditionalModel getAdditionalModel(String templateId) {
    return null;
  }

  public List<IMagic> getAllLearnedMagic() {
    return allLearnedMagic;
  }

  public IGenericTrait[] getBackgrounds() {
    return allBackgrounds.toArray(new IGenericTrait[allBackgrounds.size()]);
  }

  public ICharacterPoints getCharacterPoints() {
    return null;
  }

  public IGenericCombo[] getCombos() {
    return allCombos.toArray(new IGenericCombo[allCombos.size()]);
  }

  public DemoConcept getConcept() {
    return concept;
  }

  public int getHealthLevelTypeCount(HealthLevelType type) {
    return 0;
  }

  public int getLearnCount(ICharm charm) {
    return 0;
  }

  public int getPainTolerance() {
    return 0;
  }

  public String getPeripheralPool() {
    return "25"; //$NON-NLS-1$
  }

  public String getPersonalPool() {
    return "10"; //$NON-NLS-1$
  }

  public IExaltedRuleSet getRules() {
    return ruleSet;
  }

  public void setRuleSet(IExaltedRuleSet ruleSet) {
    this.ruleSet = ruleSet;
  }

  public INamedGenericTrait[] getSpecialties(ITraitType traitType) {
    if (specialtyTraitTypes.contains(traitType)) {
      return new INamedGenericTrait[] {
          new DemoNamedGenericTrait(traitType, 3, " (Specialty)")
      };
    }
    return new INamedGenericTrait[0];
  }
  
  public INamedGenericTrait[] getSubTraits(ITraitType traitType) {
    if (subbedTraitTypes.contains(traitType)) {
      return new INamedGenericTrait[] {
          new DemoNamedGenericTrait(traitType, 2, " (Sub)")
      };
    }
    return new INamedGenericTrait[0];
  }

  public DemoCharacterTemplate getTemplate() {
    return characterTemplate;
  }

  public String[] getUncompletedCelestialMartialArtsGroups() {
    return new String[0];
  }

  public boolean isAlienCharm(ICharm charm) {
    return false;
  }

  public boolean isExperienced() {
    return false;
  }

  public boolean isFavoredOrCasteTrait(ITraitType type) {
    return false;
  }

  public boolean isLearned(IMagic magic) {
    return false;
  }

  public boolean isRequirementFulfilled(ICharmAttributeRequirement requirement) {
    return false;
  }

  public ICasteType getCasteType() {
    return null;
  }

  public ITraitLimitation getEssenceLimitation() {
    return null;
  }

  public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
    boolean isFavored = net.sf.anathema.lib.random.RandomUtilities.nextPercent() < 50;
    return new DummyFavorableGenericTrait(type, 2, isFavored);
  }

  public IGenericTrait getTrait(ITraitType type) {
    return new DummyGenericTrait(type, 1);
  }

  public int getLearnCount(IMultiLearnableCharm multiLearnableCharm) {
    return 0;
  }

  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
  }

  public DemoGenericDescription getDescription() {
    return description;
  }

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

  public void addSpecialtyTrait(ITraitType traitType) {
    specialtyTraitTypes.add(traitType);
  }

  public void addSubbedTrait(ITraitType traitType) {
    subbedTraitTypes.add(traitType);
  }
}