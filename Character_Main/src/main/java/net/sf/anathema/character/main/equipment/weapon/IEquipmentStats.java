package net.sf.anathema.character.main.equipment.weapon;

import net.sf.anathema.character.main.util.IStats;
import net.sf.anathema.lib.util.Identifier;

public interface IEquipmentStats extends IStats, Identifier {
  boolean representsItemForUseInCombat();
}