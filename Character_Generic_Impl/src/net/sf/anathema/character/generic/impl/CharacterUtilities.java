package net.sf.anathema.character.generic.impl;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;

public class CharacterUtilities {

  public static int getDodgeMdv(IGenericTraitCollection traitCollection, IEquipmentModifiers equipment) {
    int baseValue = getRoundDownDv(traitCollection, OtherTraitType.Willpower, AbilityType.Integrity,
            OtherTraitType.Essence);
    baseValue += equipment.getMDDVMod();
    return Math.max(baseValue, 0);
  }

  public static int getJoinBattle(IGenericTraitCollection traitCollection, IEquipmentModifiers equipment) {
    int baseValue = getTotalValue(traitCollection, AttributeType.Wits, AbilityType.Awareness);
    baseValue += equipment.getJoinBattleMod();
    return Math.max(baseValue, 1);
  }

  public static int getJoinDebate(IGenericTraitCollection traitCollection, IEquipmentModifiers equipment) {
    int baseValue = getTotalValue(traitCollection, AttributeType.Wits, AbilityType.Awareness);
    baseValue += equipment.getJoinDebateMod();
    return Math.max(baseValue, 1);
  }

  public static int getKnockdownThreshold(IGenericTraitCollection traitCollection) {
    int baseValue = getTotalValue(traitCollection, AttributeType.Stamina, AbilityType.Resistance);
    return Math.max(baseValue, 0);

  }

  public static int getKnockdownPool(IGenericCharacter character) {
    return getKnockdownPool(character.getTraitCollection());
  }

  public static int getKnockdownPool(IGenericTraitCollection traitCollection) {
    int attribute = getMaxValue(traitCollection, AttributeType.Dexterity, AttributeType.Stamina);
    int ability = getMaxValue(traitCollection, AbilityType.Athletics, AbilityType.Resistance);
    int pool = attribute + ability;
    return Math.max(pool, 0);
  }

  public static int getStunningThreshold(IGenericTraitCollection traitCollection) {
    int baseValue = getTotalValue(traitCollection, AttributeType.Stamina);
    return Math.max(baseValue, 0);
  }

  public static int getStunningPool(IGenericTraitCollection traitCollection) {
    int baseValue = getTotalValue(traitCollection, AttributeType.Stamina, AbilityType.Resistance);
    return Math.max(baseValue, 0);
  }

  private static int getMaxValue(IGenericTraitCollection traitCollection, ITraitType second, ITraitType first) {
    return Math.max(traitCollection.getTrait(first).getCurrentValue(),
            traitCollection.getTrait(second).getCurrentValue());
  }

  private static int getRoundDownDv(IGenericTraitCollection traitCollection, ITraitType... types) {
    int sum = 0;
    for (ITraitType type : types) {
      sum += traitCollection.getTrait(type).getCurrentValue();
    }
    return sum / 2;
  }

  private static int getDv(ICharacterType characterType, IGenericTraitCollection traitCollection, ITraitType... types) {
    if (!characterType.isEssenceUser()) {
      return getRoundDownDv(traitCollection, types);
    }
    return getRoundUpDv(traitCollection, types);
  }

  public static int getRoundUpDv(IGenericTraitCollection traitCollection, ITraitType... types) {
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

  public static int getDodgeDv(ICharacterType characterType, IGenericTraitCollection traitCollection,
                               IEquipmentModifiers equipment) {
    int dv;
    int essenceValue = traitCollection.getTrait(OtherTraitType.Essence).getCurrentValue();
    if (essenceValue > 1) {
      dv = getDv(characterType, traitCollection, AttributeType.Dexterity, AbilityType.Dodge, OtherTraitType.Essence);
    } else {
      dv = getDv(characterType, traitCollection, AttributeType.Dexterity, AbilityType.Dodge);
    }
    dv += equipment.getDDVMod() + equipment.getMobilityPenalty();
    return Math.max(dv, 0);
  }
}