package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.main.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WeaponStatsNameStringFactory {

  private final Resources resources;

  public WeaponStatsNameStringFactory(Resources resources) {
    this.resources = resources;
  }

  public String create(IEquipmentItem item, IWeaponStats stats) {
    StringBuilder builder = new StringBuilder(stats.getName().getId());
    if (hasMultipleViews(item, stats)) {
      builder.append(" (");
      builder.append(resources.getString(stats.getTraitType().getId()));
      builder.append(")");
    }
    return builder.toString();
  }

  @SuppressWarnings("SimplifiableIfStatement")
  private boolean hasMultipleViews(IEquipmentItem item, IWeaponStats stats) {
    if (item == null) {
      return true;
    }
    return Collections.frequency(getStatNames(item, new ArrayList<Identifier>()), stats.getName()) > 1;
  }

  private Collection<Identifier> getStatNames(IEquipmentItem item, Collection<Identifier> names) {
    for (IEquipmentStats stats : item.getStats()) {
      names.add(stats.getName());
    }
    return names;
  }
}