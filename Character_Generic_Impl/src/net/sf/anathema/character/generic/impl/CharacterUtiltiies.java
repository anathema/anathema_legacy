package net.sf.anathema.character.generic.impl;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharacterUtiltiies {

  public static int getTotalValue(IGenericCharacter character, ITraitType... types) {
    int sum = 0;
    for (ITraitType type : types) {
      sum += character.getTrait(type).getCurrentValue();
    }
    return sum;
  }

  public static int getDvValue(IGenericCharacter character, ITraitType... types) {
    int sum = 0;
    for (ITraitType type : types) {
      sum += character.getTrait(type).getCurrentValue();
    }
    CharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    if (characterType == CharacterType.MORTAL) {
      return sum / 2;
    }
    return (int) Math.ceil(sum * 0.5);
  }
  
  public static int getKnockdownPool(IGenericCharacter character) {
    int attribute = getMaxValue(character, AttributeType.Dexterity, AttributeType.Stamina);
    int ability = getMaxValue(character, AbilityType.Athletics, AbilityType.Resistance);
    return attribute + ability;
  }

  private static int getMaxValue(IGenericCharacter character, ITraitType second, ITraitType first) {
    return Math.max(character.getTrait(first).getCurrentValue(), character.getTrait(second).getCurrentValue());
  }
}