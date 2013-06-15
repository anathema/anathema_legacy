package net.sf.anathema.character.generic.social;

import net.sf.anathema.character.generic.util.IStats;

public interface ISocialCombatStats extends IStats {

  int getDeceptionAttackValue();

  int getDeceptionMDV();

  int getHonestyAttackValue();

  int getHonestyMDV();

  int getRate();

  int getSpeed();
}