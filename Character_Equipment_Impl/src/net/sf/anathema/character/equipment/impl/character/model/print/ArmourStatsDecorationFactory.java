package net.sf.anathema.character.equipment.impl.character.model.print;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class ArmourStatsDecorationFactory implements IEquipmentStatsDecorationFactory<IArmourStats> {

  private String createItemName(IEquipmentItem item, IEquipmentStats stats) {
    String itemName = item.getTitle();
    if (getListedStats(item) > 1) {
      itemName += " - " + stats.getName(); //$NON-NLS-1$
    }
    return itemName;
  }

  private int getListedStats(IEquipmentItem item) {
    int listedStats = 0;
    for (IEquipmentStats stats : item.getStats()) {
      if (stats instanceof IArtifactStats) continue;
      listedStats++;
    }
    return listedStats;
  }

  @Override
  public IArmourStats createRenamedPrintDecoration(final IEquipmentItem item, final IArmourStats stats) {
    final String name = createItemName(item, stats);
    return new IArmourStats() {
      @Override
      public Integer getFatigue() {
        return stats.getFatigue();
      }

      @Override
      public Integer getHardness(HealthType type) {
        return stats.getHardness(type);
      }

      @Override
      public Integer getMobilityPenalty() {
        return stats.getMobilityPenalty();
      }

      @Override
      public Integer getSoak(HealthType type) {
        return stats.getSoak(type);
      }

      @Override
      public IIdentificate getName() {
        return new Identificate(name);
      }

      @Override
      public String getId() {
        return getName().getId();
      }

      @Override
      public boolean representsItemForUseInCombat() {
        return stats.representsItemForUseInCombat();
      }
    };
  }
}
