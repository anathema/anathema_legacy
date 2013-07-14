package net.sf.anathema.hero.combat.model.social;

import net.sf.anathema.character.main.util.IStats;

public interface ISocialCombatStats extends IStats {

  int getDeceptionAttackValue();

  int getDeceptionMDV();

  int getHonestyAttackValue();

  int getHonestyMDV();

  int getRate();

  int getSpeed();
}