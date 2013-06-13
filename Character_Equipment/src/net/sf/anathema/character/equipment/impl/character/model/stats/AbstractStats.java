package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.Identified;

public abstract class AbstractStats implements IEquipmentStats {

  private Identified name;

  @Override
  public Identified getName() {
    return name;
  }

  public final void setName(Identified name) {
    this.name = name;
  }
}