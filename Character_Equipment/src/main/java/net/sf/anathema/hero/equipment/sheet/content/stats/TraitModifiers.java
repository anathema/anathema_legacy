package net.sf.anathema.hero.equipment.sheet.content.stats;

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
