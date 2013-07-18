package net.sf.anathema.character.equipment.character.model.stats;

import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
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