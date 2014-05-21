package net.sf.anathema.hero.spiritual.advance.creation;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.hero.spiritual.model.traits.TraitCollectionUtilities;
import net.sf.anathema.character.main.library.trait.experience.TraitRatingCostCalculator;
import net.sf.anathema.character.main.template.experience.CurrentRatingCosts;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;

import static net.sf.anathema.hero.spiritual.model.traits.TraitCollectionUtilities.getVirtues;
import static net.sf.anathema.character.main.traits.types.OtherTraitType.Essence;

public class SpiritualBonusPointsCalculator implements HeroBonusPointCalculator {

  private final VirtueBonusCostCalculator virtueCalculator;
  private final Trait willpower;
  private final Trait essence;
  private int essenceBonusPoints;
  private int willpowerBonusPoints;
  private SpiritualCreationData creationData;

  public SpiritualBonusPointsCalculator(SpiritualTraitModel spiritualTraits, SpiritualCreationData creationData) {
    this.creationData = creationData;
    this.virtueCalculator = new VirtueBonusCostCalculator(getVirtues(spiritualTraits), creationData);
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
    CurrentRatingCosts essenceCost = creationData.getEssenceCost();
    int calculationBase = creationData.getCalculationBase(Essence);
    return TraitRatingCostCalculator.getTraitRatingCosts(calculationBase, essence.getCreationValue(), essenceCost);
  }

  private int calculateWillpowerPoints() {
    int calculationBase = creationData.getCalculationBase(OtherTraitType.Willpower);
    return (willpower.getCreationValue() - calculationBase) * creationData.getWillpowerCost();
  }

  public VirtueBonusCostCalculator getVirtueBonusPointCalculator() {
    return virtueCalculator;
  }
}
