package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.TraitCollectionUtilities;
import net.sf.anathema.character.main.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;

import static net.sf.anathema.character.main.library.trait.TraitCollectionUtilities.getVirtues;

public class SpiritualBonusPointsCalculator implements HeroBonusPointCalculator{

  private final VirtueCostCalculator virtueCalculator;
  private final Trait willpower;
  private final Trait essence;
  private int essenceBonusPoints;
  private int willpowerBonusPoints;
  private BonusPointCosts costs;

  public SpiritualBonusPointsCalculator(SpiritualTraitModel spiritualTraits, ICreationPoints creationPoints, BonusPointCosts costs) {
    this.costs = costs;
    this.virtueCalculator = new VirtueCostCalculator(getVirtues(spiritualTraits), creationPoints.getVirtueCreationPoints(), costs);
    this.willpower = TraitCollectionUtilities.getWillpower(spiritualTraits);
    this.essence = TraitCollectionUtilities.getEssence(spiritualTraits);
  }

  @Override
  public void recalculate() {
    virtueCalculator.calculateVirtuePoints();
    willpowerBonusPoints = calculateWillpowerPoints();
    essenceBonusPoints = calculateEssencePoints();
  }

  @Override
  public int getBonusPointCost() {
    return virtueCalculator.getBonusPointsSpent() + willpowerBonusPoints + essenceBonusPoints;
  }

  @Override
  public int getBonusPointsGranted() {
    return 0;
  }

  private int calculateEssencePoints() {
    CurrentRatingCosts essenceCost = costs.getEssenceCost();
    return TraitRatingCostCalculator.getTraitRatingCosts(essence.getZeroCalculationValue(), essence.getCreationValue(), essenceCost);
  }

  private int calculateWillpowerPoints() {
    return (willpower.getCreationValue() - willpower.getMinimalValue()) * costs.getWillpowerCosts();
  }

  public VirtueCostCalculator getVirtueBonusPointCalculator() {
    return virtueCalculator;
  }
}
