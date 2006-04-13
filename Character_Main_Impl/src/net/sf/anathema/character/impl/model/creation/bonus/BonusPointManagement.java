package net.sf.anathema.character.impl.model.creation.bonus;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.advance.models.AbstractAdditionalSpendingModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.AbilityCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.ability.DefaultAbilityBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.FavoredAbilityBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.FavoredAbilityPickModel;
import net.sf.anathema.character.impl.model.creation.bonus.additional.AdditionalBonusPointPoolManagement;
import net.sf.anathema.character.impl.model.creation.bonus.additional.MiscBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.AttributeBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.AttributeCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.backgrounds.BackgroundBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.backgrounds.BackgroundBonusPointCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.magic.DefaultCharmModel;
import net.sf.anathema.character.impl.model.creation.bonus.magic.FavoredCharmModel;
import net.sf.anathema.character.impl.model.creation.bonus.magic.MagicCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.virtue.VirtueBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.virtue.VirtueCostCalculator;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.model.generic.GenericCharacter;
import net.sf.anathema.character.presenter.overview.IAdditionalSpendingModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;
import net.sf.anathema.character.presenter.overview.ISpendingModel;
import net.sf.anathema.character.presenter.overview.IValueModel;

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
  private final List<IAdditionalModelBonusPointCalculator> additionalCalculators = new ArrayList<IAdditionalModelBonusPointCalculator>();
  private final ICreationPoints creationPoints;
  private final ICharacterStatistics statistics;

  public BonusPointManagement(ICharacterStatistics statistics) {
    this.statistics = statistics;
    this.creationPoints = statistics.getCharacterTemplate().getCreationPoints();
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

  private int getAdditionalBonusPointSpent() {
    return bonusAdditionalPools.getPointSpent();
  }

  private int getAdditionalBonusPointAmount() {
    return bonusAdditionalPools.getAmount();
  }

  private int getStandardBonusPointsSpent() {
    return getTotalBonusPointsSpent() - getAdditionalBonusPointSpent();
  }

  private int getTotalBonusPointsSpent() {
    return attributeCalculator.getBonusPoints()
        + getDefaultAbilityModel().getSpentBonusPoints()
        + abilityCalculator.getSpecialtyBonusPointCosts()
        + getDefaultCharmModel().getSpentBonusPoints()
        + comboBonusPoints
        + getBackgroundModel().getSpentBonusPoints()
        + getVirtueModel().getSpentBonusPoints()
        + willpowerBonusPoints
        + essenceBonusPoints
        + getAdditionalModelModel().getValue();
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
    return new VirtueBonusModel(virtueCalculator, creationPoints);
  }

  public ISpendingModel getBackgroundModel() {
    return new BackgroundBonusModel(backgroundCalculator, creationPoints);
  }

  public ISpendingModel getDefaultAbilityModel() {
    return new DefaultAbilityBonusModel(abilityCalculator, creationPoints);
  }

  public ISpendingModel getFavoredAbilityModel() {
    return new FavoredAbilityBonusModel(abilityCalculator, creationPoints);
  }

  public ISpendingModel getFavoredAbilityPickModel() {
    return new FavoredAbilityPickModel(abilityCalculator, creationPoints);
  }

  public ISpendingModel getAttributeModel(final AttributeGroupPriority priority) {
    return new AttributeBonusModel(attributeCalculator, priority, creationPoints);
  }

  public ISpendingModel getFavoredCharmModel() {
    return new FavoredCharmModel(magicCalculator, creationPoints);
  }

  public IAdditionalSpendingModel getDefaultCharmModel() {
    IAdditionalRules additionalRules = statistics.getCharacterTemplate().getAdditionalRules();
    return new DefaultCharmModel(magicCalculator, magicAdditionalPools, creationPoints, additionalRules);
  }

  public IValueModel<Integer> getAdditionalModelModel() {
    return new MiscBonusModel(additionalCalculators);
  }

  public IAdditionalSpendingModel getTotalModel() {
    return new AbstractAdditionalSpendingModel("Bonus", "Total") { //$NON-NLS-1$ //$NON-NLS-2$
      public int getAdditionalRestrictedAlotment() {
        return getAdditionalBonusPointAmount();
      }

      public int getAdditionalValue() {
        return getAdditionalBonusPointSpent();
      }

      public int getSpentBonusPoints() {
        return 0;
      }

      public Integer getValue() {
        return getStandardBonusPointsSpent();
      }

      public int getAlotment() {
        return creationPoints.getBonusPointCount() + getAdditionalGeneralBonusPoints();
      }

      public boolean isExtensionRequired() {
        IAdditionalRules additionalRules = statistics.getCharacterTemplate().getAdditionalRules();
        return additionalRules != null && additionalRules.getAdditionalBonusPointPools().length > 0;
      }
    };
  }

  public IOverviewModel[] getAllModels() {
    List<IOverviewModel> models = new ArrayList<IOverviewModel>();
    models.add(getAttributeModel(AttributeGroupPriority.Primary));
    models.add(getAttributeModel(AttributeGroupPriority.Secondary));
    models.add(getAttributeModel(AttributeGroupPriority.Tertiary));
    models.add(getFavoredAbilityPickModel());
    models.add(getFavoredAbilityModel());
    models.add(getDefaultAbilityModel());
    addCharmModels(models);
    models.add(getVirtueModel());
    models.add(getBackgroundModel());
    addMiscModel(models);
    models.add(getTotalModel());
    return models.toArray(new IOverviewModel[models.size()]);
  }

  private void addMiscModel(List<IOverviewModel> models) {
    if (statistics.getExtendedConfiguration().getAdditionalModels().length > 0) {
      models.add(getAdditionalModelModel());
    }
  }

  private void addCharmModels(List<IOverviewModel> models) {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms()) {
      return;
    }
    models.add(getFavoredCharmModel());
    models.add(getDefaultCharmModel());
  }
}