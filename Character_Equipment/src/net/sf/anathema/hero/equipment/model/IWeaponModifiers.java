package net.sf.anathema.hero.equipment.model;

public interface IWeaponModifiers {

  int getMeleeSpeedMod();

  int getMeleeAccuracyMod();

  int getMeleeDamageMod();

  int getMeleeRateMod();

  int getRangedSpeedMod();

  int getRangedAccuracyMod();

  int getRangedDamageMod();

  int getRangedRateMod();

  int getPDVPoolMod();
}
