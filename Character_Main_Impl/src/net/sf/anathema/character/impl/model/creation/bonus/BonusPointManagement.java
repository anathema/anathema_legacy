package net.sf.anathema.character.impl.model.creation.bonus;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.advance.models.AbstractAdditionalSpendingModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.AbilityCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.ability.DefaultAbilityBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.FavoredAbilityBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.FavoredAbilityPickModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.IAbilityCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.additional.AdditionalBonusPointPoolManagement;
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
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IAdditionalSpendingModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;
import net.sf.anathema.character.presenter.overview.ISpendingModel;

public class BonusPointManagement implements IBonusPointManagement {

  private final IAdditionalMagicLearnPointManagement magicAdditionalPools;
  private final AdditionalBonusPointPoolManagement bonusAdditionalPools;
  private final IAbilityCostCalculator abilityCalculator;
  private final AttributeCostCalculator attributeCalculator;
  private final VirtueCostCalculator virtueCalculator;
  private final BackgroundBonusPointCostCalculator backgroundCalculator;
  private final MagicCostCalculator magicCalculator;
  private final IDefaultTrait willpower;
  private final IBonusPointCosts cost;
  private final IComboConfiguration combos;
  private final IDefaultTrait essence;
  private final ICreationPoints creationPoints;
  private final ICharacterStatistics statistics;
  private int essenceBonusPoints;
  private int willpowerBonusPoints;
  private int comboBonusPoints;
  private final BonusPointCalculator bonusPointCalculator = new BonusPointCalculator();

  public BonusPointManagement(ICharacterStatistics statistics) {
    this.statistics = statistics;
    this.creationPoints = statistics.getCharacterTemplate().getCreationPoints();
    for (IAdditionalModel model : statistics.getExtendedConfiguration().getAdditionalModels()) {
      bonusPointCalculator.addAdditionalBonusPointCalculator(model.getBonusPointCalculator());
    }
    bonusAdditionalPools = new AdditionalBonusPointPoolManagement(
        statistics.getTraitConfiguration(),
        statistics.getCharacterTemplate().getAdditionalRules().getAdditionalBonusPointPools());
    this.cost = statistics.getCharacterTemplate().getBonusPointCosts();
    ICharacterTemplate characterTemplate = statistics.getCharacterTemplate();
    GenericCharacter characterAbstraction = GenericCharacterUtilities.createGenericCharacter(statistics);
    ICoreTraitConfiguration traitConfiguration = statistics.getTraitConfiguration();
    this.abilityCalculator = new AbilityCostCalculator(
        traitConfiguration,
        creationPoints.getAbilityCreationPoints(),
        cost,
        bonusAdditionalPools);
    this.attributeCalculator = new AttributeCostCalculator(
        traitConfiguration,
        creationPoints.getAttributeCreationPoints(),
        cost);
    this.backgroundCalculator = new BackgroundBonusPointCostCalculator(
        bonusAdditionalPools,
        traitConfiguration.getBackgrounds(),
        cost,
        creationPoints.getBackgroundPointCount(),
        characterTemplate.getAdditionalRules());
    IDefaultTrait[] virtues = TraitCollectionUtilities.getVirtues(traitConfiguration);
    this.virtueCalculator = new VirtueCostCalculator(virtues, creationPoints.getVirtueCreationPoints(), cost);
    magicAdditionalPools = new AdditionalMagicLearnPointManagement(characterTemplate.getAdditionalRules()
        .getAdditionalMagicLearnPools(), characterAbstraction);
    this.magicCalculator = new MagicCostCalculator(
        characterTemplate.getMagicTemplate(),
        statistics.getCharms(),
        statistics.getSpells(),
        creationPoints.getFavoredCreationCharmCount(),
        creationPoints.getDefaultCreationCharmCount(),
        cost,
        bonusAdditionalPools,
        magicAdditionalPools,
        statistics.getCharacterContext().getBasicCharacterContext(),
        statistics.getCharacterContext().getTraitCollection());
    this.combos = statistics.getCombos();
    this.willpower = TraitCollectionUtilities.getWillpower(traitConfiguration);
    this.essence = TraitCollectionUtilities.getEssence(traitConfiguration);
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
    bonusPointCalculator.recalculate();
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
        + bonusPointCalculator.getAdditionalModelModel().getValue();
  }

  private ISpendingModel getVirtueModel() {
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
        return creationPoints.getBonusPointCount() + bonusPointCalculator.getAdditionalGeneralBonusPoints();
      }

      public boolean isExtensionRequired() {
        IAdditionalRules additionalRules = statistics.getCharacterTemplate().getAdditionalRules();
        return additionalRules != null && additionalRules.getAdditionalBonusPointPools().length > 0;
      }

      public int getRequiredSize() {
        return 5;
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
    bonusPointCalculator.addMiscModel(models);
    models.add(getTotalModel());
    return models.toArray(new IOverviewModel[models.size()]);
  }

  private void addCharmModels(List<IOverviewModel> models) {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms(statistics.getRules())) {
      return;
    }
    models.add(getFavoredCharmModel());
    models.add(getDefaultCharmModel());
  }
}