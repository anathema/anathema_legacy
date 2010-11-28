package net.sf.anathema.character.meritsflaws.model.perk.evaluator;

import java.util.Arrays;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.IBasicCharacterData;

public class CasteEvaluator implements ICharacterEvaluator {

  private final String[] casteNames;

  public CasteEvaluator(String[] casteNames) {
    this.casteNames = casteNames;
  }

  public boolean evaluateCharacter(IBasicCharacterData characterData) {
    return ArrayUtilities.contains(casteNames, characterData.getCasteType().getId());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CasteEvaluator)) {
      return false;
    }
    return Arrays.equals(this.casteNames, ((CasteEvaluator) obj).casteNames);
  }

  @Override
  public int hashCode() {
    return casteNames.hashCode() * 17;
  }
}