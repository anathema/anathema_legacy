package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.util.Identified;

public interface IEquipmentStats extends IStats, Identified {
  boolean representsItemForUseInCombat();
}