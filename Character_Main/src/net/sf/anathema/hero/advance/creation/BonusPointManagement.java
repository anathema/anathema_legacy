package net.sf.anathema.hero.advance.creation;

import net.sf.anathema.hero.advance.experience.models.AbstractSpendingModel;
import net.sf.anathema.hero.advance.creation.virtue.VirtueBonusModel;
import net.sf.anathema.hero.advance.creation.virtue.VirtueCostCalculator;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.main.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.ISpendingModel;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.TraitModelFetcher;

import java.util.ArrayList;
import java.util.List;

public class BonusPointManagement implements IBonusPointManagement {

  private final VirtueCostCalculator virtueCalculator;
  private final Trait willpower;
  private final BonusPointCosts cost;
  private final Trait essence;
  private final ICreationPoints creationPoints;
  private int essenceBonusPoints;
  private int willpowerBonusPoints;
  private final BonusPointCalculator bonusPointCalculator = new BonusPointCalculator();

  public BonusPointManagement(Hero hero) {
    this.creationPoints = hero.getTemplate().getCreationPoints();
    for (HeroBonusPointCalculator additionalCalculator : PointModelFetcher.fetch(hero).getBonusPointCalculators()) {
      bonusPointCalculator.addBonusPointCalculator(additionalCalculator);
    }
    this.cost = hero.getTemplate().getBonusPointCosts();
    TraitMap traitMap = TraitModelFetcher.fetch(hero);
    Trait[] virtues = TraitCollectionUtilities.getVirtues(traitMap);
    this.virtueCalculator = new VirtueCostCalculator(virtues, creationPoints.getVirtueCreationPoints(), cost);
    this.willpower = TraitCollectionUtilities.getWillpower(traitMap);
    this.essence = TraitCollectionUtilities.getEssence(traitMap);
  }

  @Override
  public void recalculate() {
    virtueCalculator.calculateVirtuePoints();
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

  private ISpendingModel getVirtueModel() {
    return new VirtueBonusModel(virtueCalculator, creationPoints);
  }

  @Override
  public ISpendingModel getTotalModel() {
    return new TotalBonusPointModel();
  }

  @Override
  public IOverviewModel[] getAllModels() {
    List<IOverviewModel> models = new ArrayList<>();
    models.add(getVirtueModel());
    models.add(getTotalModel());
    return models.toArray(new IOverviewModel[models.size()]);
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
      int spiritualTraitPoints = getVirtueModel().getSpentBonusPoints() + willpowerBonusPoints + essenceBonusPoints;
      return spiritualTraitPoints + bonusPointCalculator.getTotalValue();
    }

    @Override
    public int getAllotment() {
      return creationPoints.getBonusPointCount() + bonusPointCalculator.getAdditionalGeneralBonusPoints();
    }
  }
}