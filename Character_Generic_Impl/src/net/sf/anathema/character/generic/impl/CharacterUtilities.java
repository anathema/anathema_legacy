package net.sf.anathema.character.generic.impl;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;

public class CharacterUtilities {

  public static int getDodgeMdv(IGenericTraitCollection traitCollection, ICharacterStatsModifiers equipment) {
    return getDodgeMdvWithSpecialty( traitCollection, equipment, 0);
  }
  
  public static int getDodgeMdvWithSpecialty(IGenericTraitCollection traitCollection, ICharacterStatsModifiers equipment, int specialty) {
	int dvPool = getTotalValue( traitCollection, OtherTraitType.Willpower, AbilityType.Integrity, OtherTraitType.Essence ) +
			specialty + equipment.getMDDVPoolMod();
	int dv = getRoundDownDv( dvPool );
	
    return Math.max(dv, 0);
  }

  public static int getParryMdv( IGenericTraitCollection traitCollection, ICharacterStatsModifiers equipment, ITraitType... types) {
	int dvPool = getTotalValue( traitCollection, types ) + equipment.getMPDVPoolMod();
	int dv = getRoundUpDv( dvPool );
	
    return Math.max(dv, 0);
  }
  
  private static int getRoundDownDv(int dvPool) {
    return dvPool / 2;
  }
  
  private static int getRoundUpDv(int dvPool) {
    return (int) Math.ceil( dvPool * 0.5 );
  }
  
  public static int getSocialAttackValue( IGenericTraitCollection traitCollection, ITraitType... types ) {
      return getTotalValue( traitCollection, types );
  }
  
  public static int getJoinBattle(IGenericTraitCollection traitCollection, ICharacterStatsModifiers equipment) {
    int baseValue = getTotalValue(traitCollection, AttributeType.Wits, AbilityType.Awareness);
    baseValue += equipment.getJoinBattleMod();
    return Math.max(baseValue, 1);
  }
  
  public static int getJoinBattleWithSpecialty(IGenericTraitCollection traitCollection, ICharacterStatsModifiers equipment, int awarenessSpecialty) {
    int baseValue = getJoinBattle( traitCollection, equipment );
    baseValue += awarenessSpecialty;
    return Math.max(baseValue, 1);
  }

  public static int getJoinDebate(IGenericTraitCollection traitCollection, ICharacterStatsModifiers equipment) {
      int baseValue = getTotalValue(traitCollection, AttributeType.Wits, AbilityType.Awareness);
      baseValue += equipment.getJoinDebateMod();
      return Math.max(baseValue, 1);
  }
  
  public static int getJoinDebateWithSpecialty( IGenericTraitCollection traitCollection, ICharacterStatsModifiers equipment, int awarenessSpecialty) {
      int baseValue = getJoinDebate( traitCollection, equipment );
      baseValue += awarenessSpecialty;
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
  
  private static int getTotalValue(IGenericTraitCollection traitCollection, ITraitType... types) {
    int sum = 0;
    for (ITraitType type : types) {
      sum += traitCollection.getTrait(type).getCurrentValue();
    }
    return sum;
  }
  
  private static int getDodgeDvPool(IGenericTraitCollection traitCollection) {
    int essence = traitCollection.getTrait(OtherTraitType.Essence).getCurrentValue();
    int dvPool  = getTotalValue( traitCollection, AttributeType.Dexterity, AbilityType.Dodge );
    if( essence >= 2 ) {
        dvPool += essence;
    }
    return dvPool;
  }
  
  private static int getRoundedDodgeDv( ICharacterType characterType, int dvPool ) {
    int dv;
    if(characterType.isEssenceUser()) {
      dv = (int) Math.ceil(dvPool * 0.5);
    } else {
      dv = dvPool / 2;
    }
    return dv;
  }

  public static int getDodgeDv(ICharacterType characterType,
                               IGenericTraitCollection traitCollection,
                               ICharacterStatsModifiers equipment) {
    return getDodgeDvWithSpecialty( characterType, traitCollection, equipment, 0 );
  }

  public static int getDodgeDvWithSpecialty(ICharacterType characterType,
                                            IGenericTraitCollection traitCollection,
                                            ICharacterStatsModifiers equipment,
                                            int specialty) {
    int dvPool = getDodgeDvPool(traitCollection) + specialty + equipment.getDDVPoolMod();
    int dv     = getRoundedDodgeDv(characterType, dvPool) + equipment.getMobilityPenalty();
    
    return Math.max(dv, 0);
  }
  
}