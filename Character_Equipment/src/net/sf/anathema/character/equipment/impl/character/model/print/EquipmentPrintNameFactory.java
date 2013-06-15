package net.sf.anathema.character.equipment.impl.character.model.print;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class EquipmentPrintNameFactory {
  private final Resources resources;

  public EquipmentPrintNameFactory(Resources resources) {
    this.resources = resources;
  }

  public String create(IEquipmentItem item, IWeaponStats stats) {
    StringBuilder builder = new StringBuilder(item.getTitle());
    if (item.getStats().length == 1) {
      return builder.toString();
    }
    if (!hasSingleOriginalStat(item)) {
      builder.append(" - ");
      builder.append(stats.getName());
    }
    if (Collections.frequency(getStatNames(item, new ArrayList<Identifier>()), stats.getName()) > 1) {
      builder.append(" (");
      builder.append(resources.getString(stats.getTraitType().getId()));
      builder.append(")");
    }
    return builder.toString();
  }

  private boolean hasSingleOriginalStat(IEquipmentItem item) {
    return getStatNames(item, new HashSet<Identifier>()).size() == 1;
  }

  private Collection<Identifier> getStatNames(IEquipmentItem item, Collection<Identifier> names) {
    for (IEquipmentStats stats : item.getStats()) {
      if (!stats.representsItemForUseInCombat()) {
        continue;
      }
      names.add(stats.getName());
    }
    return names;
  }
}