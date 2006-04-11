package net.sf.anathema.character.impl.model.creation.bonus;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.creation.bonus.ability.AbilityCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.additional.AdditionalBonusPointPoolManagement;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.AttributeCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.backgrounds.BackgroundBonusPointCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.magic.MagicCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.virtue.VirtueCostCalculator;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.model.generic.GenericCharacter;
import net.sf.anathema.character.presenter.overview.IAdditionalSpendingModel;
import net.sf.anathema.character.presenter.overview.ISpendingModel;
import net.sf.anathema.character.presenter.overview.IValueSpendingModel;

public class BonusPointManagement implements IBonusPointManagement {

  private final IAdditionalMagicLearnPointManagement magicAdditionalPools;
  private final AdditionalBonusPointPoolManagement bonusAdditionalPools;
  private final AbilityCostCalculator abilityCalculator;
  private final AttributeCostCalculator attributeCalculator;
  private final VirtueCostCalculator virtueCalculator;
  private final BackgroundBonusPointCostCalculator backgroundCalculator;
  private MagicCostCalculator magicCalculator;
  private final ITrait willpower;
  private final IBonusPointCosts cost;
  private IComboConfiguration combos;
  private int willpowerBonusPoints;
  private int comboBonusPoints;
  private ITrait essence;
  private int essenceBonusPoints;
  private List<IAdditionalModelBonusPointCalculator> additionalCalculators = new ArrayList<IAdditionalModelBonusPointCalculator>();

  public BonusPointManagement(ICharacterStatistics statistics) {
    for (IAdditionalModel model : statistics.getExtendedConfiguration().getAdditionalModels()) {
      additionalCalculators.add(model.getBonusPointCalculator());
    }
    bonusAdditionalPools = new AdditionalBonusPointPoolManagement(statistics);
    this.cost = statistics.getCharacterTemplate().getBonusPointCosts();
    ICharacterTemplate characterTemplate = statistics.getCharacterTemplate();
    GenericCharacter characterAbstraction = GenericCharacterUtilities.createGenericCharacter(statistics);
    this.abilityCalculator = new AbilityCostCalculator(
        statistics.getTraitConfiguration(),
        characterTemplate.getCreationPoints().getAbilityCreationPoints(),
        cost,
        bonusAdditionalPools);
    this.attributeCalculator = new AttributeCostCalculator(
        statistics.getTraitConfiguration(),
        characterTemplate.getCreationPoints().getAttributeCreationPoints(),
        cost);
    this.backgroundCalculator = new BackgroundBonusPointCostCalculator(
        bonusAdditionalPools,
        statistics.getTraitConfiguration().getBackgrounds(),
        cost,
        characterTemplate.getCreationPoints().getBackgroundPointCount(),
        characterTemplate.getAdditionalRules());
    this.virtueCalculator = new VirtueCostCalculator(
        statistics.getTraitConfiguration().getTraits(VirtueType.values()),
        characterTemplate.getCreationPoints().getVirtueCreationPoints(),
        cost);
    magicAdditionalPools = new AdditionalMagicLearnPointManagement(characterTemplate.getAdditionalRules()
        .getAdditionalMagicLearnPools(), characterAbstraction);
    this.magicCalculator = new MagicCostCalculator(
        characterTemplate.getMagicTemplate(),
        statistics.getCharms(),
        statistics.getSpells(),
        characterTemplate.getCreationPoints().getFavoredCreationCharmCount(),
        characterTemplate.getCreationPoints().getDefaultCreationCharmCount(),
        cost,
        bonusAdditionalPools,
        magicAdditionalPools,
        statistics.getCharacterContext().getBasicCharacterContext(),
        statistics.getCharacterContext().getTraitCollection());
    this.combos = statistics.getCombos();
    this.willpower = statistics.getTraitConfiguration().getTrait(OtherTraitType.Willpower);
    this.essence = statistics.getTraitConfiguration().getTrait(OtherTraitType.Essence);
  }

  public void recalculate() {
    bonusAdditionalPools.reset();
    backgroundCalculator.calculateBonusPoints();
    abilityCalculator.calculateCosts();
    attributeCalculator.calculateAttributeCosts();
    virtueCalculator.calculateVirtuePoints();
    magicCalculator.calculateMagicCosts();
    comboBonusPoints = calculateComboPoints();
    willpowerBonusPoints = calculateWillpowerPoints();
    essenceBonusPoints = calculateEssencePoints();
    for (IAdditionalModelBonusPointCalculator calculator : additionalCalculators) {
      calculator.recalculate();
    }
  }

  private int calculateEssencePoints() {
    return (essence.getCreationValue() - essence.getZeroCalculationValue()) * cost.getEssenceCost();
  }

  private int calculateWillpowerPoints() {
    return (willpower.getCreationValue() - willpower.getMinimalValue()) * cost.getWillpowerCosts();
  }

  private int calculateComboPoints() {
    int bonusPoints = 0;
    for (ICombo combo : combos.getCreationCombos()) {
      bonusPoints += combo.getCharms().length;
    }
    return bonusPoints;
  }

  public int getSpecialtyBonusPointCosts() {
    return abilityCalculator.getSpecialtyBonusPointCosts();
  }

  public int getDefaultCharmPicksSpent() {
    return magicCalculator.getGeneralCharmPicksSpent();
  }

  public int getCharmBonusPointsSpent() {
    if (magicCalculator == null) {
      return 0;
    }
    return magicCalculator.getBonusPointsSpentForCharms();
  }

  public int getSpellBonusPointsSpent() {
    if (magicCalculator == null) {
      return 0;
    }
    return magicCalculator.getBonusPointsSpentForSpells();
  }

  public int getWillpowerBonusPointsSpent() {
    return willpowerBonusPoints;
  }

  public int getComboBonusPointsSpent() {
    return comboBonusPoints;
  }

  public int getEssenceBonusPointsSpent() {
    return essenceBonusPoints;
  }

  public void dump(PrintStream printStream) {
    printStream.println("Dots"); //$NON-NLS-1$
    printStream.println("   Primary Attributes: " + getAttributeModel(AttributeGroupPriority.Primary).getValue()); //$NON-NLS-1$
    printStream.println("   Secondary Attributes: " + getAttributeModel(AttributeGroupPriority.Secondary).getValue()); //$NON-NLS-1$
    printStream.println("   Tertiary Attributes: " + getAttributeModel(AttributeGroupPriority.Tertiary).getValue()); //$NON-NLS-1$
    printStream.println("   Favored Abilities:" + getFavoredAbilityModel().getValue()); //$NON-NLS-1$
    printStream.println("   General Abilities:" + getDefaultAbilityModel().getValue()); //$NON-NLS-1$
    printStream.println("   Virtues:" + getVirtueModel().getValue()); //$NON-NLS-1$
    printStream.println("   Backgrounds:" + getBackgroundModel().getValue()); //$NON-NLS-1$
    printStream.println("Magic Picks"); //$NON-NLS-1$
    printStream.println("   Favored Picks:" + getFavoredCharmModel().getValue()); //$NON-NLS-1$
    printStream.println("   Default Picks:" + getDefaultCharmPicksSpent()); //$NON-NLS-1$
    printStream.println("Bonus Points"); //$NON-NLS-1$
    printStream.println("   Primary Attributes: " + getAttributeModel(AttributeGroupPriority.Primary).getSpentBonusPoints()); //$NON-NLS-1$
    printStream.println("   Secondary Attributes: " + getAttributeModel(AttributeGroupPriority.Secondary).getSpentBonusPoints()); //$NON-NLS-1$
    printStream.println("   Tertiary Attributes: " + getAttributeModel(AttributeGroupPriority.Tertiary).getSpentBonusPoints()); //$NON-NLS-1$
    printStream.println("   Abilities:" + getDefaultAbilityModel().getSpentBonusPoints()); //$NON-NLS-1$
    printStream.println("   Specialties:" + getSpecialtyBonusPointCosts()); //$NON-NLS-1$
    printStream.println("   Virtues:" + getVirtueModel().getSpentBonusPoints()); //$NON-NLS-1$
    printStream.println("   Willpower:" + getWillpowerBonusPointsSpent()); //$NON-NLS-1$
    printStream.println("   Backgrounds:" + getBackgroundModel().getSpentBonusPoints()); //$NON-NLS-1$
    printStream.println("   Essence:" + getEssenceBonusPointsSpent()); //$NON-NLS-1$
    printStream.println("   Charms:" + getCharmBonusPointsSpent()); //$NON-NLS-1$
    printStream.println("   Combos:" + getComboBonusPointsSpent()); //$NON-NLS-1$
    printStream.println("Additional Bonus Points"); //$NON-NLS-1$
    printStream.println("   Amount: " + getAdditionalBonusPointAmount()); //$NON-NLS-1$
    printStream.println("   Spent: " + getAdditionalBonusPointSpent()); //$NON-NLS-1$
    printStream.println("Additional Magic Points"); //$NON-NLS-1$
    printStream.println("   Amount: " + getAdditionalMagicPointsAmount()); //$NON-NLS-1$
    printStream.println("   Spent: " + getAdditionalMagicPointsSpent()); //$NON-NLS-1$

  }

  public int getAdditionalBonusPointSpent() {
    return bonusAdditionalPools.getPointSpent();
  }

  public int getAdditionalBonusPointAmount() {
    return bonusAdditionalPools.getAmount();
  }

  public int getStandardBonusPointsSpent() {
    return getTotalBonusPointsSpent() - getAdditionalBonusPointSpent();
  }

  public int getTotalBonusPointsSpent() {
    return attributeCalculator.getBonusPoints()
        + getDefaultAbilityModel().getSpentBonusPoints()
        + getSpecialtyBonusPointCosts()
        + getCharmBonusPointsSpent()
        + getComboBonusPointsSpent()
        + getSpellBonusPointsSpent()
        + getBackgroundModel().getSpentBonusPoints()
        + getVirtueModel().getSpentBonusPoints()
        + getWillpowerBonusPointsSpent()
        + getEssenceBonusPointsSpent()
        + getAdditionalModelTotalValue();
  }

  public int getAdditionalMagicPointsSpent() {
    return magicCalculator.getAdditionalPointsSpent();
  }

  public int getAdditionalMagicPointsAmount() {
    return magicAdditionalPools.getAdditionalPointsAmount();
  }

  public int getAdditionalModelTotalValue() {
    int additionalSpent = 0;
    for (IAdditionalModelBonusPointCalculator calculator : additionalCalculators) {
      additionalSpent += calculator.getBonusPointCost();
    }
    return additionalSpent;
  }

  /** Return the amount of unrestricted bonus points granted by additional models */
  public int getAdditionalGeneralBonusPoints() {
    int additionalGranted = 0;
    for (IAdditionalModelBonusPointCalculator calculator : additionalCalculators) {
      additionalGranted += calculator.getBonusPointsGranted();
    }
    return additionalGranted;
  }

  public ISpendingModel getVirtueModel() {
    return new ISpendingModel() {
      public int getValue() {
        return virtueCalculator.getVirtueDotsSpent();
      }

      public int getSpentBonusPoints() {
        return virtueCalculator.getBonusPointsSpent();
      }
    };
  }

  public ISpendingModel getBackgroundModel() {
    return new ISpendingModel() {
      public int getValue() {
        return backgroundCalculator.getSpentDots();
      }

      public int getSpentBonusPoints() {
        return backgroundCalculator.getBonusPointSpent();
      }
    };
  }

  public ISpendingModel getDefaultAbilityModel() {
    return new ISpendingModel() {
      public int getValue() {
        return abilityCalculator.getFreePointsSpent(false);
      }

      public int getSpentBonusPoints() {
        return abilityCalculator.getBonusPointsSpent();
      }
    };
  }

  public ISpendingModel getFavoredAbilityModel() {
    return new ISpendingModel() {
      public int getValue() {
        return abilityCalculator.getFreePointsSpent(true);
      }

      public int getSpentBonusPoints() {
        return 0;
      }
    };
  }

  public ISpendingModel getFavoredAbilityPickModel() {
    return new ISpendingModel() {
      public int getSpentBonusPoints() {
        return 0;
      }

      public int getValue() {
        return abilityCalculator.getFavoredPicksSpent();
      }
    };
  }

  public ISpendingModel getAttributeModel(final AttributeGroupPriority priority) {
    return new ISpendingModel() {
      public int getSpentBonusPoints() {
        return attributeCalculator.getAttributePoints(priority).getBonusPointsSpent();
      }

      public int getValue() {
        return attributeCalculator.getAttributePoints(priority).getDotsSpent();
      }
    };
  }

  public ISpendingModel getFavoredCharmModel() {
    return new ISpendingModel() {
      public int getSpentBonusPoints() {
        return 0;
      }

      public int getValue() {
        return magicCalculator.getFavoredCharmPicksSpent();
      }
    };
  }

  public IAdditionalSpendingModel getDefaultCharmModel() {
    return new IAdditionalSpendingModel() {
      public int getAdditionalRestrictedAlotment() {
        return getAdditionalMagicPointsAmount();
      }

      public int getAdditionalValue() {
        return getAdditionalMagicPointsSpent();
      }

      public int getSpentBonusPoints() {
        return getCharmBonusPointsSpent() + getSpellBonusPointsSpent();
      }

      public int getValue() {
        return getDefaultCharmPicksSpent();
      }

      public int getAdditionalUnrestrictedAlotment() {
        return 0;
      }
    };
  }

  public IValueSpendingModel getAdditionalModelModel() {
    return new IValueSpendingModel() {
      public int getValue() {
        return getAdditionalModelTotalValue();
      }
    };
  }

  public IAdditionalSpendingModel getTotalModel() {
    return new IAdditionalSpendingModel() {
      public int getAdditionalRestrictedAlotment() {
        return getAdditionalBonusPointAmount();
      }

      public int getAdditionalValue() {
        return getAdditionalBonusPointSpent();
      }

      public int getSpentBonusPoints() {
        return 0;
      }

      public int getValue() {
        return getStandardBonusPointsSpent();
      }

      public int getAdditionalUnrestrictedAlotment() {
        return getAdditionalGeneralBonusPoints();
      }
    };
  }
}