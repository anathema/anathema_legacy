package net.sf.anathema.character.meritsflaws.model.perk.cost;

import java.util.Arrays;

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
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }
    
    FixedPerkCost other = (FixedPerkCost)obj;
    if (evaluator == null) {
      return other.evaluator == null;
    }
    return evaluator.equals(other.evaluator);
  }

  @Override
  public int hashCode() {
    return evaluator.hashCode() + 3 * Arrays.hashCode(fixedCosts);
  }
}