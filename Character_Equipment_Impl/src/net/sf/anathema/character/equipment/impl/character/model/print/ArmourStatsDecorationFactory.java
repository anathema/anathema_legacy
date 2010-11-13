package net.sf.anathema.character.equipment.impl.character.model.print;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class ArmourStatsDecorationFactory implements IEquipmentStatsDecorationFactory<IArmourStats> {

  private String createItemName(IEquipmentItem item, IEquipmentStats stats) {
    String itemName = item.getTemplateId();
    if (item.getStats().length > 1) {
      itemName += " - " + stats.getName(); //$NON-NLS-1$
    }
    return itemName;
  }

  public IArmourStats createRenamedPrintDecoration(final IEquipmentItem item, final IArmourStats stats) {
    final String name = createItemName(item, stats);
    return new IArmourStats() {
      public Integer getFatigue() {
        return stats.getFatigue();
      }

      public Integer getHardness(HealthType type) {
        return stats.getHardness(type);
      }

      public Integer getMobilityPenalty() {
        return stats.getMobilityPenalty();
      }

      public Integer getSoak(HealthType type) {
        return stats.getSoak(type);
      }

      public IIdentificate getName() {
        return new Identificate(name);
      }

      @Override
      public String getId() {
        return getName().getId();
      }
    };
  }
}
