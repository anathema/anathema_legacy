package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.Identifier;

public abstract class AbstractStats implements IEquipmentStats {

  private Identifier name;

  @Override
  public Identifier getName() {
    return name;
  }

  public final void setName(Identifier name) {
    this.name = name;
  }
}