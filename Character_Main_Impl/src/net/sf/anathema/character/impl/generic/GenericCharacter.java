package net.sf.anathema.character.impl.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.character.ICharacterPoints;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitLimitation;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.special.IMultiLearnableCharmConfiguration;
import net.sf.anathema.character.model.charm.special.IMultipleEffectCharmConfiguration;
import net.sf.anathema.character.model.charm.special.ISubeffect;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;

public class GenericCharacter implements IGenericCharacter {

  private final ICharacterStatistics statistics;
  private final CharacterPoints characterPoints;

  public GenericCharacter(ICharacterStatistics statistics, IExperiencePointManagement experiencePointManagement) {
    this.statistics = statistics;
    this.characterPoints = new CharacterPoints(statistics, experiencePointManagement);
  }

  public boolean isFavoredOrCasteTrait(ITraitType type) {
    ITrait trait = statistics.getTraitConfiguration().getTrait(type);
    if (trait instanceof IFavorableTrait) {
      return ((IFavorableTrait) trait).getFavorization().isCasteOrFavored();
    }
    return false;
  }

  public IGenericTrait getTrait(ITraitType traitType) {
    return statistics.getTraitConfiguration().getTrait(traitType);
  }

  public IFavorableGenericTrait getFavorableTrait(ITraitType type) {
    return statistics.getTraitConfiguration().getFavorableTrait(type);
  }

  public int getLearnCount(IMultiLearnableCharm charm) {
    ICharmConfiguration charms = statistics.getCharms();
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) charms.getSpecialCharmConfiguration(charm.getCharmId());
    return configuration.getCurrentLearnCount();
  }

  public void setLearnCount(IMultiLearnableCharm multiLearnableCharm, int newValue) {
    ICharmConfiguration charms = statistics.getCharms();
    IMultiLearnableCharmConfiguration configuration = (IMultiLearnableCharmConfiguration) charms.getSpecialCharmConfiguration(multiLearnableCharm.getCharmId());
    configuration.setCurrentLearnCount(newValue);
  }

  public boolean isLearned(IMagic magic) {
    final boolean[] isLearned = new boolean[1];
    magic.accept(new IMagicVisitor() {
      public void visitSpell(ISpell spell) {
        isLearned[0] = statistics.getSpells().isLearned(spell);
      }

      public void visitCharm(ICharm charm) {
        isLearned[0] = statistics.getCharms().isLearned(charm);
      }
    });
    return isLearned[0];
  }

  public boolean isAlienCharm(ICharm charm) {
    return statistics.getCharms().isAlienCharm(charm);
  }

  public ICharacterTemplate getTemplate() {
    return statistics.getCharacterTemplate();
  }

  public INamedGenericTrait[] getSpecialties(ITraitType traitType) {
    return statistics.getTraitConfiguration().getTrait(traitType).getSpecialtiesContainer().getSpecialties();
  }

  public INamedGenericTrait[] getSubTraits(ITraitType traitType) {
    // todo vom (14.05.2006) (sieroux): Multiple Crafts
    return new INamedGenericTrait[0];
  }

  public ICasteType< ? extends ICasteTypeVisitor> getCasteType() {
    return statistics.getCharacterConcept().getCaste().getType();
  }

  public ICharacterPoints getCharacterPoints() {
    return characterPoints;
  }

  public IExaltedRuleSet getRules() {
    return statistics.getRules();
  }

  public int getHealthLevelTypeCount(HealthLevelType type) {
    return statistics.getHealth().getHealthLevelTypeCount(type);
  }

  public String getPeripheralPool() {
    return getTemplate().getEssenceTemplate().isEssenceUser() ? statistics.getEssencePool().getPeripheralPool() : null;
  }

  public String getPersonalPool() {
    return getTemplate().getEssenceTemplate().isEssenceUser() ? statistics.getEssencePool().getPersonalPool() : null;
  }

  public IGenericTrait[] getBackgrounds() {
    return statistics.getTraitConfiguration().getBackgrounds().getBackgrounds();
  }

  public IAdditionalModel getAdditionalModel(String id) {
    return statistics.getExtendedConfiguration().getAdditionalModel(id);
  }

  public IConcept getConcept() {
    return new GenericConcept(statistics.getCharacterConcept());
  }

  public List<IMagic> getAllLearnedMagic() {
    boolean experienced = statistics.isExperienced();
    List<IMagic> magicList = new ArrayList<IMagic>();
    magicList.addAll(Arrays.asList(statistics.getCharms().getLearnedCharms(experienced)));
    magicList.addAll(Arrays.asList(statistics.getSpells().getLearnedSpells(experienced)));
    return magicList;
  }

  public int getLearnCount(ICharm charm) {
    return getLearnCount(charm, statistics.getCharms());
  }

  private int getLearnCount(ICharm charm, ICharmConfiguration configuration) {
    ISpecialCharmConfiguration specialCharmConfiguration = configuration.getSpecialCharmConfiguration(charm.getId());
    if (specialCharmConfiguration != null) {
      return specialCharmConfiguration.getCurrentLearnCount();
    }
    return configuration.isLearned(charm) ? 1 : 0;
  }

  public IGenericCombo[] getCombos() {
    List<IGenericCombo> genericCombos = new ArrayList<IGenericCombo>();
    for (ICombo combo : statistics.getCombos().getCurrentCombos()) {
      genericCombos.add(new GenericCombo(combo));
    }
    return genericCombos.toArray(new IGenericCombo[0]);
  }

  public boolean isExperienced() {
    return statistics.isExperienced();
  }

  public boolean isRequirementFulfilled(ICharmAttributeRequirement requirement) {
    return statistics.getCharms().isFullfilled(requirement);
  }

  public String[] getUncompletedCelestialMartialArtsGroups() {
    return statistics.getCharms().getUncompletedCelestialMartialArtsGroups();
  }

  public int getPainTolerance() {
    return statistics.getHealth().getPainToleranceLevel();
  }

  public ITraitLimitation getEssenceLimitation() {
    return getTemplate().getTraitTemplateCollection().getTraitTemplate(OtherTraitType.Essence).getLimitation();
  }

  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return statistics.getTraitConfiguration().getAbilityTypeGroups();
  }

  public IArmour[] getPrintArmours() {
    // todo vom (14.05.2006) (sieroux): Equipments
    return new IArmour[0];
  }

  public IWeapon[] getPrintWeapons() {
    // todo vom (14.05.2006) (sieroux): Equipments
    return new IWeapon[0];
  }

  public int getSpentExperiencePoints() {
    return new ExperiencePointManagement(statistics).getTotalCosts();
  }

  public int getTotalExperiencePoints() {
    return statistics.getExperiencePoints().getTotalExperiencePoints();
  }

  public boolean isSubeffectCharm(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = statistics.getCharms().getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof ISubeffectCharmConfiguration;
  }

  public boolean isMultipleEffectCharm(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = statistics.getCharms().getSpecialCharmConfiguration(charm);
    return charmConfiguration instanceof IMultipleEffectCharmConfiguration
        && !(charmConfiguration instanceof ISubeffectCharmConfiguration);
  }

  public String[] getLearnedEffects(ICharm charm) {
    ISpecialCharmConfiguration charmConfiguration = statistics.getCharms().getSpecialCharmConfiguration(charm);
    if (!(charmConfiguration instanceof IMultipleEffectCharmConfiguration)) {
      return new String[0];
    }
    IMultipleEffectCharmConfiguration configuration = (IMultipleEffectCharmConfiguration) charmConfiguration;
    List<String> learnedEffectIds = new ArrayList<String>();
    for (ISubeffect effect : configuration.getEffects()) {
      if (effect.isLearned()) {
        learnedEffectIds.add(effect.getId());
      }
    }
    return learnedEffectIds.toArray(new String[learnedEffectIds.size()]);
  }
}