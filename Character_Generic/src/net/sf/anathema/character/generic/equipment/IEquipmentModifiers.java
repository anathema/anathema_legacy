package net.sf.anathema.character.generic.equipment;

public interface IEquipmentModifiers {
  int getPDVMod();

  int getMobilityPenalty();

  int getDDVMod();

  int getMDDVMod();

  int getMPDVMod();

  int getJoinBattleMod();

  int getJoinDebateMod();

  int getJoinWarMod();
}
