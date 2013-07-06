package net.sf.anathema.character.model.creation.bonus;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.creation.BonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.main.model.abilities.AbilityModelFetcher;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.model.advance.models.AbstractAdditionalSpendingModel;
import net.sf.anathema.character.model.charm.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.model.creation.bonus.ability.AbilityCostCalculator;
import net.sf.anathema.character.model.creation.bonus.ability.DefaultAbilityBonusModel;
import net.sf.anathema.character.model.creation.bonus.ability.FavoredAbilityBonusModel;
import net.sf.anathema.character.model.creation.bonus.ability.FavoredAbilityPickModel;
import net.sf.anathema.character.model.creation.bonus.ability.IAbilityCostCalculator;
import net.sf.anathema.character.model.creation.bonus.ability.SpecialtyBonusModel;
import net.sf.anathema.character.model.creation.bonus.additional.AdditionalBonusPoints;
import net.sf.anathema.character.model.creation.bonus.additional.AdditionalBonusPointsImpl;
import net.sf.anathema.character.model.creation.bonus.magic.DefaultCharmModel;
import net.sf.anathema.character.model.creation.bonus.magic.FavoredCharmModel;
import net.sf.anathema.character.model.creation.bonus.magic.MagicCostCalculator;
import net.sf.anathema.character.model.creation.bonus.virtue.VirtueBonusModel;
import net.sf.anathema.character.model.creation.bonus.virtue.VirtueCostCalculator;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.IAdditionalSpendingModel;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.ISpendingModel;

import java.util.ArrayList;
import java.util.List;

public class BonusPointManagement implements IBonusPointManagement {

  private final IAdditionalMagicLearnPointManagement magicAdditionalPools;
  private final AdditionalBonusPoints bonusAdditionalPools;
  private final IAbilityCostCalculator abilityCalculator;
  private final VirtueCostCalculator virtueCalculator;
  private final MagicCostCalculator magicCalculator;
  private final Trait willpower;
  private final BonusPointCosts cost;
  private final Trait essence;
  private final ICreationPoints creationPoints;
  private final Hero hero;
  private int essenceBonusPoints;
  private int willpowerBonusPoints;
  private final BonusPointCalculator bonusPointCalculator = new BonusPointCalculator();

  public BonusPointManagement(Hero hero) {
    this.hero = hero;
    this.creationPoints = hero.getTemplate().getCreationPoints();
    for (HeroBonusPointCalculator additionalCalculator : PointModelFetcher.fetch(hero).getBonusPointCalculators()) {
      bonusPointCalculator.addBonusPointCalculator(additionalCalculator);
    }
    bonusAdditionalPools = PointModelFetcher.fetch(hero).getAdditionalBonusPoints();
    this.cost = hero.getTemplate().getBonusPointCosts();
    HeroTemplate characterTemplate = hero.getTemplate();
    TraitMap traitMap = TraitModelFetcher.fetch(hero);
    this.abilityCalculator = new AbilityCostCalculator(hero, AbilityModelFetcher.fetch(hero), creationPoints.getAbilityCreationPoints(),
            creationPoints.getSpecialtyCreationPoints(), cost, bonusAdditionalPools);
    Trait[] virtues = TraitCollectionUtilities.getVirtues(traitMap);
    this.virtueCalculator = new VirtueCostCalculator(virtues, creationPoints.getVirtueCreationPoints(), cost);
    magicAdditionalPools = new AdditionalMagicLearnPointManagement(characterTemplate.getAdditionalRules().getAdditionalMagicLearnPools(), hero);
    this.magicCalculator = new MagicCostCalculator(hero, cost, bonusAdditionalPools, magicAdditionalPools);
    this.willpower = TraitCollectionUtilities.getWillpower(traitMap);
    this.essence = TraitCollectionUtilities.getEssence(traitMap);
  }

  @Override
  public void recalculate() {
    bonusAdditionalPools.reset();
    abilityCalculator.calculateCosts();
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
    return getDefaultAbilityModel().getSpentBonusPoints() + abilityCalculator.getSpecialtyBonusPointCosts() +
           getDefaultCharmModel().getSpentBonusPoints() + getVirtueModel().getSpentBonusPoints() +
           willpowerBonusPoints + essenceBonusPoints + bonusPointCalculator.getMiscellaneousModel().getValue();
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
  public ISpendingModel getFavoredCharmModel() {
    return new FavoredCharmModel(magicCalculator, creationPoints);
  }

  @Override
  public IAdditionalSpendingModel getDefaultCharmModel() {
    IAdditionalRules additionalRules = hero.getTemplate().getAdditionalRules();
    return new DefaultCharmModel(magicCalculator, magicAdditionalPools, creationPoints, additionalRules);
  }

  @Override
  public IAdditionalSpendingModel getTotalModel() {
    return new TotalBonusPointModel();
  }

  @Override
  public IOverviewModel[] getAllModels() {
    List<IOverviewModel> models = new ArrayList<>();

    models.add(getFavoredAbilityPickModel());
    if (getFavoredAbilityModel().getAllotment() > 0) {
      models.add(getFavoredAbilityModel());
    }
    models.add(getDefaultAbilityModel());
    if (getSpecialtiesModel().getAllotment() > 0) {
      models.add(getSpecialtiesModel());
    }
    addCharmModels(models);
    models.add(getVirtueModel());
    models.add(getTotalModel());
    return models.toArray(new IOverviewModel[models.size()]);
  }

  private void addCharmModels(List<IOverviewModel> models) {
    if (!(DefaultCharmTemplateRetriever.getNativeTemplate(hero).canLearnCharms())) {
      return;
    }
    if (getFavoredCharmModel().getAllotment() > 0) {
      models.add(getFavoredCharmModel());
    }
    models.add(getDefaultCharmModel());
  }

  private class TotalBonusPointModel extends AbstractAdditionalSpendingModel {
    public TotalBonusPointModel() {
      super("Bonus", "Total");
    }

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
    public int getAllotment() {
      return creationPoints.getBonusPointCount() + bonusPointCalculator.getAdditionalGeneralBonusPoints();
    }

    @Override
    public boolean isExtensionRequired() {
      IAdditionalRules additionalRules = hero.getTemplate().getAdditionalRules();
      return additionalRules != null && additionalRules.getAdditionalBonusPointPools().length > 0;
    }

    @Override
    public int getRequiredSize() {
      return 5;
    }
  }
}