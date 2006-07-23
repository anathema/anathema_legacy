package net.sf.anathema.character.equipment.impl.reporting.second.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractNameStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public final class EquipmentNameStatsGroup<T extends IEquipmentStats> extends AbstractNameStatsGroup<T> implements
    IEquipmentStatsGroup<T> {

  public EquipmentNameStatsGroup(IResources resources) {
    super(resources);
  }

  @Override
  protected String getHeaderResourceKey() {
    return "Sheet.Equipment.Header.Name"; //$NON-NLS-1$
  }

  @Override
  protected String getResourceBase() {
    return "Equipment.Name."; //$NON-NLS-1$
  }
}