package net.sf.anathema.character.meritsflaws.model.perk.evaluator;

import java.util.Arrays;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.type.CharacterType;


public class CharacterTypeEvaluator implements ICharacterEvaluator {

  private final CharacterType[] types;

  public CharacterTypeEvaluator(CharacterType[] types) {
    this.types = types;
  }

  public boolean evaluateCharacter(IBasicCharacterData characterData) {
    return ArrayUtilities.contains(types, characterData.getCharacterType());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CharacterTypeEvaluator)) {
      return false;
    }
    return Arrays.equals(this.types, ((CharacterTypeEvaluator) obj).types);
  }

  @Override
  public int hashCode() {
    return types.hashCode() * 11;
  }
}