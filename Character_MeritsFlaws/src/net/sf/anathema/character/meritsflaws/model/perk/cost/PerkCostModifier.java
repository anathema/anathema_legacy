package net.sf.anathema.character.meritsflaws.model.perk.cost;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.meritsflaws.model.perk.evaluator.ICharacterEvaluator;

public class PerkCostModifier implements IPerkCostModifier {

  private final ICharacterEvaluator evaluator;
  private final int modifier;

  public PerkCostModifier(ICharacterEvaluator evaluator, int modifier) {
    this.evaluator = evaluator;
    this.modifier = modifier;
  }

  public int getModifiedCost(IBasicCharacterData characterData, int currentCost) {
    if (evaluator.evaluateCharacter(characterData)) {
      return currentCost + modifier;
    }
    return currentCost;
  }
}