package net.sf.anathema.character.generic.equipment;

public interface TraitModifiers {
  Integer getDDVMod();

  Integer getPDVMod();

  Integer getMDDVMod();

  Integer getMPDVMod();

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
