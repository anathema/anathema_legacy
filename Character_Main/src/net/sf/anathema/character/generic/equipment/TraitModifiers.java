package net.sf.anathema.character.generic.equipment;

public interface TraitModifiers {
  Integer getDDVPoolMod();

  Integer getPDVPoolMod();

  Integer getMDDVPoolMod();

  Integer getMPDVPoolMod();

  Integer getMeleeSpeedMod();

  Integer getMeleeAccuracyMod();

  Integer getMeleeDamageMod();

  Integer getMeleeRateMod();

  Integer getRangedSpeedMod();

  Integer getRangedAccuracyMod();

  Integer getRangedDamageMod();

  Integer getRangedRateMod();

  Integer getJoinBattleMod();

  Integer getJoinDebateMod();

  Integer getJoinWarMod();
}
