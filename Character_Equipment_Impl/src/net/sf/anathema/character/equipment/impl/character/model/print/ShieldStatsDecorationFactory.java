package net.sf.anathema.character.equipment.impl.character.model.print;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class ShieldStatsDecorationFactory implements IEquipmentStatsDecorationFactory<IShieldStats> {

  private String createItemName(IEquipmentItem item, IEquipmentStats stats) {
    String itemName = item.getTemplateId();
    if (item.getStats().length > 1) {
      itemName += " - " + stats.getName(); //$NON-NLS-1$
    }
    return itemName;
  }

  @Override
  public IShieldStats createRenamedPrintDecoration(IEquipmentItem item, final IShieldStats stats) {
    final String name = createItemName(item, stats);
    return new IShieldStats() {
      public int getCloseCombatBonus() {
        return stats.getCloseCombatBonus();
      }

      public int getRangedCombatBonus() {
        return stats.getRangedCombatBonus();
      }

      public IIdentificate getName() {
        return new Identificate(name);
      }

      public Integer getFatigue() {
        return stats.getFatigue();
      }

      public Integer getMobilityPenalty() {
        return stats.getMobilityPenalty();
      }

      @Override
      public String getId() {
        return getName().getId();
      }
    };
  }

}
