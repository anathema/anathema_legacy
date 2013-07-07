package net.sf.anathema.character.main.creation.bonus;

import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.main.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.main.advance.models.AbstractSpendingModel;
import net.sf.anathema.character.main.charm.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.creation.IBonusPointManagement;
import net.sf.anathema.character.main.creation.bonus.magic.DefaultCharmModel;
import net.sf.anathema.character.main.creation.bonus.magic.FavoredCharmModel;
import net.sf.anathema.character.main.creation.bonus.magic.MagicCostCalculator;
import net.sf.anathema.character.main.creation.bonus.virtue.VirtueBonusModel;
import net.sf.anathema.character.main.creation.bonus.virtue.VirtueCostCalculator;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.ISpendingModel;

import java.util.ArrayList;
import java.util.List;

public class BonusPointManagement implements IBonusPointManagement {

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
    this.cost = hero.getTemplate().getBonusPointCosts();
    TraitMap traitMap = TraitModelFetcher.fetch(hero);
    Trait[] virtues = TraitCollectionUtilities.getVirtues(traitMap);
    this.virtueCalculator = new VirtueCostCalculator(virtues, creationPoints.getVirtueCreationPoints(), cost);
    this.magicCalculator = new MagicCostCalculator(hero, cost);
    this.willpower = TraitCollectionUtilities.getWillpower(traitMap);
    this.essence = TraitCollectionUtilities.getEssence(traitMap);
  }

  @Override
  public void recalculate() {
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

  private int getStandardBonusPointsSpent() {
    return getTotalBonusPointsSpent();
  }

  private int getTotalBonusPointsSpent() {
    return getDefaultCharmModel().getSpentBonusPoints() + getVirtueModel().getSpentBonusPoints() +
           willpowerBonusPoints + essenceBonusPoints + bonusPointCalculator.getMiscellaneousModel().getValue();
  }

  private ISpendingModel getVirtueModel() {
    return new VirtueBonusModel(virtueCalculator, creationPoints);
  }

  @Override
  public ISpendingModel getFavoredCharmModel() {
    return new FavoredCharmModel(magicCalculator, creationPoints);
  }

  @Override
  public ISpendingModel getDefaultCharmModel() {
    return new DefaultCharmModel(magicCalculator, creationPoints);
  }

  @Override
  public ISpendingModel getTotalModel() {
    return new TotalBonusPointModel();
  }

  @Override
  public IOverviewModel[] getAllModels() {
    List<IOverviewModel> models = new ArrayList<>();
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

  private class TotalBonusPointModel extends AbstractSpendingModel {
    public TotalBonusPointModel() {
      super("Bonus", "Total");
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
  }
}