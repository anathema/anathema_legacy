package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.util.Identifier;

public interface IEquipmentStats extends IStats, Identifier {
  boolean representsItemForUseInCombat();
}