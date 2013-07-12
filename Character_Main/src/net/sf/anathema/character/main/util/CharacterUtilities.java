package net.sf.anathema.character.main.util;

import net.sf.anathema.character.main.equipment.HeroStatsModifiers;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.TraitModelFetcher;

public class CharacterUtilities {

  public static int getDodgeMdv(TraitMap traitMap, HeroStatsModifiers equipment) {
    return getDodgeMdvWithSpecialty(traitMap, equipment, 0);
  }

  public static int getDodgeMdvWithSpecialty(TraitMap traitMap, HeroStatsModifiers equipment, int specialty) {
    int dvPool = getTotalValue(traitMap, OtherTraitType.Willpower, AbilityType.Integrity, OtherTraitType.Essence) +
                 specialty + equipment.getMDDVPoolMod();
    int dv = getRoundDownDv(dvPool);
    return Math.max(dv, 0);
  }

  public static int getParryMdv(TraitMap traitMap, HeroStatsModifiers equipment, TraitType... types) {
    int dvPool = getTotalValue(traitMap, types) + equipment.getMPDVPoolMod();
    int dv = getRoundUpDv(dvPool);

    return Math.max(dv, 0);
  }

  private static int getRoundDownDv(int dvPool) {
    return dvPool / 2;
  }

  private static int getRoundUpDv(int dvPool) {
    return (int) Math.ceil(dvPool * 0.5);
  }

  public static int getSocialAttackValue(TraitMap traitMap, TraitType... types) {
    return getTotalValue(traitMap, types);
  }

  public static int getJoinBattle(TraitMap traitMap, HeroStatsModifiers equipment) {
    int baseValue = getTotalValue(traitMap, AttributeType.Wits, AbilityType.Awareness);
    baseValue += equipment.getJoinBattleMod();
    return Math.max(baseValue, 1);
  }

  public static int getJoinBattleWithSpecialty(TraitMap traitMap, HeroStatsModifiers equipment, int awarenessSpecialty) {
    int baseValue = getJoinBattle(traitMap, equipment);
    baseValue += awarenessSpecialty;
    return Math.max(baseValue, 1);
  }

  public static int getJoinDebate(TraitMap traitMap, HeroStatsModifiers equipment) {
    int baseValue = getTotalValue(traitMap, AttributeType.Wits, AbilityType.Awareness);
    baseValue += equipment.getJoinDebateMod();
    return Math.max(baseValue, 1);
  }

  public static int getJoinDebateWithSpecialty(TraitMap traitMap, HeroStatsModifiers equipment, int awarenessSpecialty) {
    int baseValue = getJoinDebate(traitMap, equipment);
    baseValue += awarenessSpecialty;
    return Math.max(baseValue, 1);
  }

  public static int getKnockdownThreshold(TraitMap traitMap) {
    int baseValue = getTotalValue(traitMap, AttributeType.Stamina, AbilityType.Resistance);
    return Math.max(baseValue, 0);
  }

  public static int getKnockdownPool(Hero hero) {
    return getKnockdownPool(TraitModelFetcher.fetch(hero));
  }

  public static int getKnockdownPool(TraitMap traitMap) {
    int attribute = getMaxValue(traitMap, AttributeType.Dexterity, AttributeType.Stamina);
    int ability = getMaxValue(traitMap, AbilityType.Athletics, AbilityType.Resistance);
    int pool = attribute + ability;
    return Math.max(pool, 0);
  }

  public static int getStunningThreshold(TraitMap traitMap) {
    int baseValue = getTotalValue(traitMap, AttributeType.Stamina);
    return Math.max(baseValue, 0);
  }

  public static int getStunningPool(TraitMap traitMap) {
    int baseValue = getTotalValue(traitMap, AttributeType.Stamina, AbilityType.Resistance);
    return Math.max(baseValue, 0);
  }

  private static int getMaxValue(TraitMap traitMap, TraitType second, TraitType first) {
    return Math.max(traitMap.getTrait(first).getCurrentValue(), traitMap.getTrait(second).getCurrentValue());
  }

  private static int getTotalValue(TraitMap traitCollection, TraitType... types) {
    int sum = 0;
    for (TraitType type : types) {
      sum += traitCollection.getTrait(type).getCurrentValue();
    }
    return sum;
  }

  private static int getDodgeDvPool(TraitMap traitCollection) {
    int essence = traitCollection.getTrait(OtherTraitType.Essence).getCurrentValue();
    int dvPool = getTotalValue(traitCollection, AttributeType.Dexterity, AbilityType.Dodge);
    if (essence >= 2) {
      dvPool += essence;
    }
    return dvPool;
  }

  private static int getRoundedDodgeDv(ICharacterType characterType, int dvPool) {
    int dv;
    if (characterType.isEssenceUser()) {
      dv = (int) Math.ceil(dvPool * 0.5);
    } else {
      dv = dvPool / 2;
    }
    return dv;
  }

  public static int getDodgeDv(ICharacterType characterType, TraitMap traitMap, HeroStatsModifiers modifiers) {
    return getDodgeDvWithSpecialty(characterType, traitMap, modifiers, 0);
  }

  public static int getDodgeDvWithSpecialty(ICharacterType characterType, TraitMap traitMap, HeroStatsModifiers equipment, int specialty) {
    int dvPool = getDodgeDvPool(traitMap) + specialty + equipment.getDDVPoolMod();
    int dv = getRoundedDodgeDv(characterType, dvPool) + equipment.getMobilityPenalty();
    return Math.max(dv, 0);
  }
}