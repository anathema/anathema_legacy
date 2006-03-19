package net.sf.anathema.character.meritsflaws.model.perk.cost;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.meritsflaws.model.perk.evaluator.ICharacterEvaluator;

public class FixedPerkCost implements IFixedPerkCost {

  private final ICharacterEvaluator evaluator;
  private final int[] fixedCosts;

  public FixedPerkCost(ICharacterEvaluator evaluator, int[] fixedCosts) {
    this.evaluator = evaluator;
    this.fixedCosts = fixedCosts;
  }

  public int[] getModifiedCost(IBasicCharacterData characterData) {
    if (evaluator.evaluateCharacter(characterData)) {
      return fixedCosts;
    }
    return new int[0];
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof FixedPerkCost)) {
      return false;
    }
    return this.evaluator == ((FixedPerkCost) obj).evaluator;
  }

  @Override
  public int hashCode() {
    return evaluator.hashCode() + 3 * fixedCosts.hashCode();
  }
}