package net.sf.anathema.hero.combat.sheet.social.stats;

import net.sf.anathema.character.framework.library.IStats;

public interface ISocialCombatStats extends IStats {

  int getDeceptionAttackValue();

  int getDeceptionMDV();

  int getHonestyAttackValue();

  int getHonestyMDV();

  int getRate();

  int getSpeed();
}