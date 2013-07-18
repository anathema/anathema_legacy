package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class ArmourStatsDecorationFactory implements IEquipmentStatsDecorationFactory<IArmourStats> {

  private String createItemName(IEquipmentItem item, IEquipmentStats stats) {
    String itemName = item.getTitle();
    if (getListedStats(item) > 1) {
      itemName += " - " + stats.getName();
    }
    return itemName;
  }

  private int getListedStats(IEquipmentItem item) {
    int listedStats = 0;
    for (IEquipmentStats stats : item.getStats()) {
      if (stats instanceof ArtifactStats) {
        continue;
      }
      listedStats++;
    }
    return listedStats;
  }

  @Override
  public IArmourStats createRenamedPrintDecoration(IEquipmentItem item, final IArmourStats stats) {
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
      public Identifier getName() {
        return new SimpleIdentifier(name);
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
