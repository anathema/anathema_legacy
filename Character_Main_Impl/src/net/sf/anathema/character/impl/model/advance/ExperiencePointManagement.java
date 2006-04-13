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
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IValueModel;

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

  private int getAbilityCosts() {
    int experienceCosts = 0;
    for (IFavorableTrait ability : getAllAbilities()) {
      experienceCosts += calculator.getAbilityCosts(ability, ability.getFavorization().isCaste()
          || ability.getFavorization().isFavored());
    }
    return experienceCosts;
  }

  private int getAttributeCosts() {
    int experienceCosts = 0;
    for (IIdentifiedTraitTypeGroup group : traitConfiguration.getAttributeTypeGroups()) {
      for (ITrait attribute : traitConfiguration.getTraits(group.getAllGroupTypes())) {
        experienceCosts += calculator.getAttributeCosts(attribute);
      }
    }
    return experienceCosts;
  }

  private int getBackgroundExperience() {
    int xpSum = 0;
    for (ITrait background : traitConfiguration.getBackgrounds().getBackgrounds()) {
      int difference = background.getCalculationValue() - background.getCreationValue();
      xpSum += difference * 3;
    }
    return xpSum;
  }

  private int getCharmCosts() {
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

  private int getComboCosts() {
    int experienceCosts = 0;
    for (ICombo combo : statistics.getCombos().getExperienceLearnedCombos()) {
      experienceCosts += calculator.getComboCosts(combo.getCharms());
    }
    return experienceCosts;
  }

  private int getEssenceCosts() {
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

  private int getSpellCosts() {
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

  private int getVirtueCosts() {
    int experienceCosts = 0;
    for (ITrait virtue : traitConfiguration.getTraits(VirtueType.values())) {
      experienceCosts += calculator.getVirtueCosts(virtue);
    }
    return experienceCosts;
  }

  private int getWillpowerCosts() {
    return calculator.getWillpowerCosts(traitConfiguration.getTrait(OtherTraitType.Willpower));
  }

  private int getSpecialtyCosts() {
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
    return traitConfiguration.getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]));
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

  private int getMiscCosts() {
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

  private IValueModel<Integer> getAttributeModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getAttributeCosts();
      }

      public String getId() {
        return "Attributes"; //$NON-NLS-1$
      }
    };
  }

  private IValueModel<Integer> getAbilityModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getAbilityCosts();
      }

      public String getId() {
        return "Abilities"; //$NON-NLS-1$
      }
    };
  }

  private IValueModel<Integer> getCharmModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getCharmCosts();
      }

      public String getId() {
        return "Charms"; //$NON-NLS-1$
      }
    };
  }

  private IValueModel<Integer> getComboModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getComboCosts();
      }

      public String getId() {
        return "Combos"; //$NON-NLS-1$
      }
    };
  }

  private IValueModel<Integer> getEssenceModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getEssenceCosts();
      }

      public String getId() {
        return "Essence"; //$NON-NLS-1$
      }
    };
  }

  private IValueModel<Integer> getMiscModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getMiscCosts();
      }

      public String getId() {
        return "Miscellaneous"; //$NON-NLS-1$
      }
    };
  }

  private IValueModel<Integer> getSpecialtyModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getSpecialtyCosts();
      }

      public String getId() {
        return "Specialties"; //$NON-NLS-1$
      }
    };
  }

  private IValueModel<Integer> getSpellModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getSpellCosts();
      }

      public String getId() {
        return "Spells"; //$NON-NLS-1$
      }
    };
  }

  private IValueModel<Integer> getVirtueModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getVirtueCosts();
      }

      public String getId() {
        return "Virtues"; //$NON-NLS-1$
      }
    };
  }

  private IValueModel<Integer> getWillpowerModel() {
    return new IValueModel<Integer>() {
      public Integer getValue() {
        return getWillpowerCosts();
      }

      public String getId() {
        return "Willpower"; //$NON-NLS-1$
      }
    };
  }

  public IValueModel<Integer>[] getAllModels() {
    return new IValueModel[] {
        getAttributeModel(),
        getAbilityModel(),
        getSpecialtyModel(),
        getCharmModel(),
        getComboModel(),
        getSpellModel(),
        getVirtueModel(),
        getWillpowerModel(),
        getEssenceModel(),
        getMiscModel() };
  }
}