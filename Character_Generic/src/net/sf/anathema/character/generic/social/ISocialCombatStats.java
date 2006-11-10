package net.sf.anathema.character.generic.social;

import net.sf.anathema.character.generic.util.IStats;

public interface ISocialCombatStats extends IStats {

  public int getDeceptionAttackValue();

  public int getDeceptionMDV();

  public int getHonestyAttackValue();

  public int getHonestyMDV();

  public int getRate();

  public int getSpeed();
}