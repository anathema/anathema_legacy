package net.sf.anathema.character.main.util;

public interface HeroStatsModifiers {

  int getMobilityPenalty();

  int getDDVPoolMod();

  int getMDDVPoolMod();

  int getMPDVPoolMod();

  int getJoinBattleMod();

  int getJoinDebateMod();

  int getJoinWarMod();
}
