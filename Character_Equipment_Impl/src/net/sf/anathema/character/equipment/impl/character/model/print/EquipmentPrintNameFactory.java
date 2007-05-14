package net.sf.anathema.character.equipment.impl.character.model.print;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class EquipmentPrintNameFactory {
  private final IResources resources;

  public EquipmentPrintNameFactory(IResources resources) {
    this.resources = resources;
  }

  public String create(IEquipmentItem item, IWeaponStats stats) {
    StringBuilder builder = new StringBuilder(item.getTemplateId());
    if (item.getStats().length == 1) {
      return builder.toString();
    }
    if (!hasSingleOriginalStat(item)) {
      builder.append(" - "); //$NON-NLS-1$
      builder.append(stats.getName());
    }
    if (Collections.frequency(getStatNames(item, new ArrayList<IIdentificate>()), stats.getName()) > 1) {
      builder.append(" ("); //$NON-NLS-1$
      builder.append(resources.getString(stats.getTraitType().getId()));
      builder.append(")"); //$NON-NLS-1$
    }
    return builder.toString();
  }

  private boolean hasSingleOriginalStat(IEquipmentItem item) {
    return getStatNames(item, new HashSet<IIdentificate>()).size() == 1;
  }

  private Collection<IIdentificate> getStatNames(IEquipmentItem item, Collection<IIdentificate> names) {
    for (IEquipmentStats stats : item.getStats()) {
      names.add(stats.getName());
    }
    return names;
  }
}
