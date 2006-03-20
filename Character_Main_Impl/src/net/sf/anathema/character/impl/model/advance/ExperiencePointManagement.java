package net.sf.anathema.character.impl.model.advance;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

public class ExperiencePointManagement implements IExperiencePointManagement {

  private final IPointCostCalculator calculator;
  private final ICharacterStatistics statistics;
  private final IBasicCharacterData basicCharacter;
  private final ICoreTraitConfiguration traitConfiguration;

  public ExperiencePointManagement(ICharacterStatistics statistics) {
    this.statistics = statistics;
    this.basicCharacter = statistics.getCharacterContext().getBasicCharacterContext();
    this.traitConfiguration = statistics.getTraitConfiguration();
    this.calculator = new ExperiencePointCostCalculator(statistics.getCharacterTemplate().getExperienceCost());
  }

  public int getAbilityCosts() {
    int experienceCosts = 0;
    for (IFavorableTrait ability : getAllAbilities()) {
      experienceCosts += calculator.getAbilityCosts(ability, ability.getFavorization().isCaste()
          || ability.getFavorization().isFavored());
    }
    return experienceCosts;
  }

  public int getAttributeCosts() {
    int experienceCosts = 0;
    for (IIdentifiedTraitTypeGroup group : traitConfiguration.getAttributeTypeGroups()) {
      for (ITrait attribute : traitConfiguration.getTraits(group.getAllGroupTypes())) {
        experienceCosts += calculator.getAttributeCosts(attribute);
      }
    }
    return experienceCosts;
  }

  public int getBackgroundExperience() {
    int xpSum = 0;
    for (ITrait background : traitConfiguration.getBackgrounds().getBackgrounds()) {
      int difference = background.getCalculationValue() - background.getCreationValue();
      xpSum += difference * 3;
    }
    return xpSum;
  }

  public int getCharmCosts() {
    int experienceCosts = 0;
    ICharmConfiguration charmConfiguration = statistics.getCharms();
    for (ICharm charm : charmConfiguration.getLearnedCharms(true)) {
      int charmCosts = calculateCharmCost(charmConfiguration, charm);
      if (charmConfiguration.isAlienCharm(charm)) {
        charmCosts *= 2;
      }
      experienceCosts += charmCosts;
    }
    return experienceCosts;
  }

  private int calculateCharmCost(ICharmConfiguration charmConfiguration, ICharm charm) {
    ISpecialCharmConfiguration specialCharm = charmConfiguration.getSpecialCharmConfiguration(charm);
    int charmCost = calculator.getCharmCosts(
        charm,
        basicCharacter,
        traitConfiguration,
        statistics.getCharacterTemplate().getMagicTemplate().getFavoringTraitType());
    if (specialCharm != null) {
      int timesLearnedWithExperience = specialCharm.getCurrentLearnCount() - specialCharm.getCreationLearnCount();
      return timesLearnedWithExperience * charmCost;
    }
    else if (charmConfiguration.getGroup(charm).isLearned(charm, true)) {
      return charmCost;
    }
    return 0;
  }

  public int getComboCosts() {
    int experienceCosts = 0;
    for (ICombo combo : statistics.getCombos().getExperienceLearnedCombos()) {
      experienceCosts += calculator.getComboCosts(combo.getCharms());
    }
    return experienceCosts;
  }

  public int getEssenceCosts() {
    return calculator.getEssenceCosts(traitConfiguration.getTrait(OtherTraitType.Essence));
  }

  public int getTotalCosts() {
    int experienceCosts = 0;
    experienceCosts += getAbilityCosts();
    experienceCosts += getAttributeCosts();
    experienceCosts += getBackgroundExperience();
    experienceCosts += getCharmCosts();
    experienceCosts += getComboCosts();
    experienceCosts += getEssenceCosts();
    experienceCosts += getSpecialtyCosts();
    experienceCosts += getSpellCosts();
    experienceCosts += getVirtueCosts();
    experienceCosts += getWillpowerCosts();
    experienceCosts += getMiscCosts();
    return experienceCosts;
  }

  public int getSpellCosts() {
    int experienceCosts = 0;
    for (ISpell spell : statistics.getSpells().getLearnedSpells(true)) {
      if (!statistics.getSpells().isLearnedOnCreation(spell)) {
        experienceCosts += calculator.getSpellCosts(
            spell,
            basicCharacter,
            traitConfiguration,
            statistics.getCharacterTemplate().getMagicTemplate().getFavoringTraitType());
      }
    }
    return experienceCosts;
  }

  public int getVirtueCosts() {
    int experienceCosts = 0;
    for (ITrait virtue : traitConfiguration.getTraits(VirtueType.values())) {
      experienceCosts += calculator.getVirtueCosts(virtue);
    }
    return experienceCosts;
  }

  public int getWillpowerCosts() {
    return calculator.getWillpowerCosts(traitConfiguration.getTrait(OtherTraitType.Willpower));
  }

  public int getSpecialtyCosts() {
    int experienceCosts = 0;
    for (IFavorableTrait ability : getAllAbilities()) {
      experienceCosts += ability.getSpecialtiesContainer().getExperienceLearnedSpecialtyCount()
          * calculator.getSpecialtyCosts(ability.getFavorization().isCasteOrFavored());
    }
    return experienceCosts;
  }

  private IFavorableTrait[] getAllAbilities() {
    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
    for (IGroupedTraitType type : statistics.getCharacterTemplate().getAbilityGroups()) {
      abilityTypes.add(type.getTraitType());
    }
    return traitConfiguration.getFavorableTraits(AbilityType.values());
  }

  @Override
  public String toString() {
    return "Abilities: " //$NON-NLS-1$
        + getAbilityCosts()
        + "\nAttributes: " //$NON-NLS-1$
        + getAttributeCosts()
        + "\nCharms: " //$NON-NLS-1$
        + getCharmCosts()
        + "\nCombo:" //$NON-NLS-1$
        + getComboCosts()
        + "\nEssence: " //$NON-NLS-1$
        + getEssenceCosts()
        + "\nSpecialties: " //$NON-NLS-1$
        + getSpecialtyCosts()
        + "\nSpells: " //$NON-NLS-1$
        + getSpellCosts()
        + "\nVirtues: " //$NON-NLS-1$
        + getVirtueCosts()
        + "\nWillpower: " //$NON-NLS-1$
        + getWillpowerCosts()
        + "\nMisc: " //$NON-NLS-1$
        + getMiscCosts()
        + "\nOverall: " + getTotalCosts(); //$NON-NLS-1$
  }

  public int getMiscCosts() {
    int total = 0;
    for (IAdditionalModel model : statistics.getExtendedConfiguration().getAdditionalModels()) {
      total += model.getExperienceCalculator().calculateCost();
    }
    return total;
  }

  public int getMiscGain() {
    int total = 0;
    for (IAdditionalModel model : statistics.getExtendedConfiguration().getAdditionalModels()) {
      total += model.getExperienceCalculator().calculateGain();
    }
    return total;
  }
}