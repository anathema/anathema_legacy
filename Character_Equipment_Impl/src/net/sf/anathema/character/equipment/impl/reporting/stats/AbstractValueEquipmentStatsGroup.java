package net.sf.anathema.character.equipment.impl.reporting.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.pdf.content.stats.AbstractValueStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractValueEquipmentStatsGroup<T extends IEquipmentStats> extends AbstractValueStatsGroup<T> implements
    IEquipmentStatsGroup<T> {

  public AbstractValueEquipmentStatsGroup(IResources resources, String resourceKey) {
    super(resources, resourceKey);
  }

  @Override
  protected String getHeaderResourceBase() {
    return "Sheet.Equipment.Header."; //$NON-NLS-1$
  }
}
