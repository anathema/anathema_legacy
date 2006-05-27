package net.sf.anathema.character.equipment.impl.reporting.second.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractValueStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractValueEquipmentStatsGroup<T extends IEquipment> extends AbstractValueStatsGroup<T> implements
    IEquipmentStatsGroup<T> {

  public AbstractValueEquipmentStatsGroup(IResources resources, String resourceKey) {
    super(resources, resourceKey);
  }

  @Override
  protected String getHeaderResourceBase() {
    return "Sheet.Equipment.Header."; //$NON-NLS-1$
  }
}