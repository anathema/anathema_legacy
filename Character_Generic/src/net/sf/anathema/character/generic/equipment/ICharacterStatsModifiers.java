package net.sf.anathema.character.generic.equipment;

public interface ICharacterStatsModifiers {
  int getPDVPoolMod();

  int getMobilityPenalty();

  int getDDVPoolMod();

  int getMDDVPoolMod();

  int getMPDVPoolMod();

  int getJoinBattleMod();

  int getJoinDebateMod();

  int getJoinWarMod();
}
