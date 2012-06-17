package net.sf.anathema.character.generic.framework.xml.rules;

import net.sf.anathema.character.generic.additionalrules.ITraitCostModifier;
import net.sf.anathema.character.generic.util.IPointModification;

import java.util.List;

public class BackgroundCostModifier implements ITraitCostModifier {
  private final List<IPointModification> bonusModifications;
  private final List<IPointModification> dotCostModifications;

  public BackgroundCostModifier(List<IPointModification> bonusModifications,
                                List<IPointModification> dotCostModifications) {
    this.bonusModifications = bonusModifications;
    this.dotCostModifications = dotCostModifications;
  }

  @Override
  public int getAdditionalBonusPointsToSpend(int traitValue) {
    int cost = 0;
    for (IPointModification modification : bonusModifications) {
      cost += modification.getAdditionalPoints(traitValue);
    }
    return cost;
  }

  @Override
  public int getAdditionalDotsToSpend(int traitValue) {
    int cost = 0;
    for (IPointModification modification : dotCostModifications) {
      cost += modification.getAdditionalPoints(traitValue);
    }
    return cost;
  }
}