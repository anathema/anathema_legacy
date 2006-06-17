package net.sf.anathema.character.generic.impl;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharacterUtilties {

  public static int getDodgeMdv(IGenericTraitCollection traitCollection) {
    return getRoundDownDv(traitCollection, OtherTraitType.Willpower, AbilityType.Integrity, OtherTraitType.Essence);
  }

  public static int getKnockdownPool(IGenericCharacter character) {
    int attribute = getMaxValue(character, AttributeType.Dexterity, AttributeType.Stamina);
    int ability = getMaxValue(character, AbilityType.Athletics, AbilityType.Resistance);
    return attribute + ability;
  }

  private static int getMaxValue(IGenericCharacter character, ITraitType second, ITraitType first) {
    return Math.max(character.getTrait(first).getCurrentValue(), character.getTrait(second).getCurrentValue());
  }

  private static int getRoundDownDv(IGenericTraitCollection traitCollection, ITraitType... types) {
    int sum = 0;
    for (ITraitType type : types) {
      sum += traitCollection.getTrait(type).getCurrentValue();
    }
    return sum / 2;
  }

  public static int getDv(IGenericCharacter character, ITraitType... types) {
    CharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    if (characterType == CharacterType.MORTAL) {
      return getRoundDownDv(character, types);
    }
    return getRoundUpDv(character, types);
  }

  public static int getDv(CharacterType characterType, IGenericTraitCollection traitCollection, ITraitType... types) {
    if (characterType == CharacterType.MORTAL) {
      return getRoundDownDv(traitCollection, types);
    }
    return getRoundUpDv(traitCollection, types);
  }

  private static int getRoundUpDv(IGenericTraitCollection traitCollection, ITraitType... types) {
    int sum = 0;
    for (ITraitType type : types) {
      sum += traitCollection.getTrait(type).getCurrentValue();
    }
    return (int) Math.ceil(sum * 0.5);
  }

  public static int getTotalValue(IGenericTraitCollection traitCollection, ITraitType... types) {
    int sum = 0;
    for (ITraitType type : types) {
      sum += traitCollection.getTrait(type).getCurrentValue();
    }
    return sum;
  }

  public static int getDodgeDv(CharacterType characterType, IGenericTraitCollection traitCollection) {
    int essenceValue = traitCollection.getTrait(OtherTraitType.Essence).getCurrentValue();
     if (essenceValue > 1) {
      return getDv(characterType, traitCollection, AttributeType.Dexterity, AbilityType.Dodge, OtherTraitType.Essence);
    }
    return getDv(characterType, traitCollection, AttributeType.Dexterity, AbilityType.Dodge);
  }
}