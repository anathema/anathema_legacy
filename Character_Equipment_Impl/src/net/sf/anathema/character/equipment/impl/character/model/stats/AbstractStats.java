package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractStats implements IEquipmentStats {

  private IIdentificate name;

  @Override
  public IIdentificate getName() {
    return name;
  }

  public final void setName(IIdentificate name) {
    if (name == null) name = null;
    this.name = name;
  }
}