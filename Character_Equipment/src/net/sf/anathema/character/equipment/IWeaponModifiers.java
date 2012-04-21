package net.sf.anathema.character.equipment;

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
