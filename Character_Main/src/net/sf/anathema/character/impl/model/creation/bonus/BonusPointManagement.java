package net.sf.anathema.character.impl.model.creation.bonus;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.HeroModelBonusPointCalculator;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.advance.models.AbstractAdditionalSpendingModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.AbilityCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.ability.DefaultAbilityBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.FavoredAbilityBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.FavoredAbilityPickModel;
import net.sf.anathema.character.impl.model.creation.bonus.ability.IAbilityCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.ability.SpecialtyBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.additional.AdditionalBonusPointPoolManagement;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.AttributeBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.AttributeCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.FavoredAttributeDotBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.FavoredAttributePickModel;
import net.sf.anathema.character.impl.model.creation.bonus.attribute.GenericAttributeDotBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.magic.DefaultCharmModel;
import net.sf.anathema.character.impl.model.creation.bonus.magic.FavoredCharmModel;
import net.sf.anathema.character.impl.model.creation.bonus.magic.MagicCostCalculator;
import net.sf.anathema.character.impl.model.creation.bonus.virtue.VirtueBonusModel;
import net.sf.anathema.character.impl.model.creation.bonus.virtue.VirtueCostCalculator;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.character.main.model.attributes.AttributesModelFetcher;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.presenter.overview.IAdditionalSpendingModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;
import net.sf.anathema.character.presenter.overview.ISpendingModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.PointModelFetcher;

import java.util.ArrayList;
import java.util.List;

public class BonusPointManagement implements IBonusPointManagement {

  private final IAdditionalMagicLearnPointManagement magicAdditionalPools;
  private final AdditionalBonusPointPoolManagement bonusAdditionalPools;
  private final IAbilityCostCalculator abilityCalculator;
  private final AttributeCostCalculator attributeCalculator;
  private final VirtueCostCalculator virtueCalculator;
  private final MagicCostCalculator magicCalculator;
  private final Trait willpower;
  private final BonusPointCosts cost;
  private final Trait essence;
  private final ICreationPoints creationPoints;
  private final Hero character;
  private int essenceBonusPoints;
  private int willpowerBonusPoints;
  private final BonusPointCalculator bonusPointCalculator = new BonusPointCalculator();

  public BonusPointManagement(Hero character) {
    this.character = character;
    this.creationPoints = character.getTemplate().getCreationPoints();
    for (HeroModelBonusPointCalculator additionalCalculator : PointModelFetcher.fetch(character).getBonusPointCalculators()) {
      bonusPointCalculator.addAdditionalBonusPointCalculator(additionalCalculator);
    }
    bonusAdditionalPools = new AdditionalBonusPointPoolManagement(TraitModelFetcher.fetch(character),
            character.getTemplate().getAdditionalRules().getAdditionalBonusPointPools());
    this.cost = character.getTemplate().getBonusPointCosts();
    HeroTemplate characterTemplate = character.getTemplate();
    GenericCharacter characterAbstraction = GenericCharacterUtilities.createGenericCharacter(character);
    TraitMap traitMap = TraitModelFetcher.fetch(character);
    this.abilityCalculator = new AbilityCostCalculator(character, AbilityModelFetcher.fetch(character), creationPoints.getAbilityCreationPoints(),
            creationPoints.getSpecialtyCreationPoints(), cost, bonusAdditionalPools);
    this.attributeCalculator = new AttributeCostCalculator(AttributesModelFetcher.fetch(character), creationPoints.getAttributeCreationPoints(), cost,
            bonusAdditionalPools);
    Trait[] virtues = TraitCollectionUtilities.getVirtues(traitMap);
    this.virtueCalculator = new VirtueCostCalculator(virtues, creationPoints.getVirtueCreationPoints(), cost);
    magicAdditionalPools =
            new AdditionalMagicLearnPointManagement(characterTemplate.getAdditionalRules().getAdditionalMagicLearnPools(), characterAbstraction);
    this.magicCalculator =
            new MagicCostCalculator(character, cost, bonusAdditionalPools,
                    magicAdditionalPools);
    this.willpower = TraitCollectionUtilities.getWillpower(traitMap);
    this.essence = TraitCollectionUtilities.getEssence(traitMap);
  }

  @Override
  public void recalculate() {
    bonusAdditionalPools.reset();
    abilityCalculator.calculateCosts();
    attributeCalculator.calculateAttributeCosts();
    virtueCalculator.calculateVirtuePoints();
    magicCalculator.calculateMagicCosts();
    willpowerBonusPoints = calculateWillpowerPoints();
    essenceBonusPoints = calculateEssencePoints();
    bonusPointCalculator.recalculate();
  }

  private int calculateEssencePoints() {
    CurrentRatingCosts costs = cost.getEssenceCost();
    return TraitRatingCostCalculator.getTraitRatingCosts(essence.getZeroCalculationValue(), essence.getCreationValue(), costs);
  }

  private int calculateWillpowerPoints() {
    return (willpower.getCreationValue() - willpower.getMinimalValue()) * cost.getWillpowerCosts();
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
    return attributeCalculator.getBonusPoints() + getDefaultAbilityModel().getSpentBonusPoints() + abilityCalculator.getSpecialtyBonusPointCosts() +
           getDefaultCharmModel().getSpentBonusPoints() + getVirtueModel().getSpentBonusPoints() +
           willpowerBonusPoints + essenceBonusPoints + bonusPointCalculator.getAdditionalModelModel().getValue();
  }

  private ISpendingModel getVirtueModel() {
    return new VirtueBonusModel(virtueCalculator, creationPoints);
  }

  @Override
  public ISpendingModel getDefaultAbilityModel() {
    return new DefaultAbilityBonusModel(abilityCalculator, creationPoints);
  }

  @Override
  public ISpendingModel getFavoredAbilityModel() {
    return new FavoredAbilityBonusModel(abilityCalculator, creationPoints);
  }

  @Override
  public ISpendingModel getFavoredAbilityPickModel() {
    return new FavoredAbilityPickModel(abilityCalculator, creationPoints);
  }

  public ISpendingModel getSpecialtiesModel() {
    return new SpecialtyBonusModel(abilityCalculator, creationPoints);
  }

  @Override
  public ISpendingModel getAttributeModel(AttributeGroupPriority priority) {
    return new AttributeBonusModel(attributeCalculator, priority, creationPoints);
  }

  public ISpendingModel getFavoredAttributeDotModel() {
    return new FavoredAttributeDotBonusModel(attributeCalculator, creationPoints);
  }

  public ISpendingModel getGenericAttributeDotModel(boolean isExtraDots) {
    return new GenericAttributeDotBonusModel(attributeCalculator, creationPoints, isExtraDots);
  }

  public ISpendingModel getFavoredAttributePickModel() {
    return new FavoredAttributePickModel(attributeCalculator, creationPoints);
  }

  @Override
  public ISpendingModel getFavoredCharmModel() {
    return new FavoredCharmModel(magicCalculator, creationPoints);
  }

  @Override
  public IAdditionalSpendingModel getDefaultCharmModel() {
    IAdditionalRules additionalRules = character.getTemplate().getAdditionalRules();
    return new DefaultCharmModel(magicCalculator, magicAdditionalPools, creationPoints, additionalRules);
  }

  @Override
  public IAdditionalSpendingModel getTotalModel() {
    return new AbstractAdditionalSpendingModel("Bonus", "Total") {
      @Override
      public int getAdditionalRestrictedAlotment() {
        return getAdditionalBonusPointAmount();
      }

      @Override
      public int getAdditionalValue() {
        return getAdditionalBonusPointSpent();
      }

      @Override
      public int getSpentBonusPoints() {
        return 0;
      }

      @Override
      public Integer getValue() {
        return getStandardBonusPointsSpent();
      }

      @Override
      public int getAlotment() {
        return creationPoints.getBonusPointCount() + bonusPointCalculator.getAdditionalGeneralBonusPoints();
      }

      @Override
      public boolean isExtensionRequired() {
        IAdditionalRules additionalRules = character.getTemplate().getAdditionalRules();
        return additionalRules != null && additionalRules.getAdditionalBonusPointPools().length > 0;
      }

      @Override
      public int getRequiredSize() {
        return 5;
      }
    };
  }

  @Override
  public IOverviewModel[] getAllModels() {
    List<IOverviewModel> models = new ArrayList<>();

    boolean showingAttributeGroups = false;
    if (getAttributeModel(AttributeGroupPriority.Primary).getAlotment() > 0) {
      models.add(getAttributeModel(AttributeGroupPriority.Primary));
      models.add(getAttributeModel(AttributeGroupPriority.Secondary));
      models.add(getAttributeModel(AttributeGroupPriority.Tertiary));
      showingAttributeGroups = true;
    }

    if (getFavoredAttributePickModel().getAlotment() > 0) {
      models.add(getFavoredAttributePickModel());
    }
    if (getFavoredAttributeDotModel().getAlotment() > 0) {
      models.add(getFavoredAttributeDotModel());
    }
    if (getGenericAttributeDotModel(showingAttributeGroups).getAlotment() > 0) {
      models.add(getGenericAttributeDotModel(showingAttributeGroups));
    }
    models.add(getFavoredAbilityPickModel());
    if (getFavoredAbilityModel().getAlotment() > 0) {
      models.add(getFavoredAbilityModel());
    }
    models.add(getDefaultAbilityModel());
    if (getSpecialtiesModel().getAlotment() > 0) {
      models.add(getSpecialtiesModel());
    }
    addCharmModels(models);
    models.add(getVirtueModel());
    bonusPointCalculator.addMiscModel(models);
    models.add(getTotalModel());
    return models.toArray(new IOverviewModel[models.size()]);
  }

  private void addCharmModels(List<IOverviewModel> models) {
    if (!character.getTemplate().getMagicTemplate().getCharmTemplate().canLearnCharms()) {
      return;
    }
    if (getFavoredCharmModel().getAlotment() > 0) {
      models.add(getFavoredCharmModel());
    }
    models.add(getDefaultCharmModel());
  }
}