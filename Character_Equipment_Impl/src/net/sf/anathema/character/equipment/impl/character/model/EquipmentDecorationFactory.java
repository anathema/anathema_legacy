package net.sf.anathema.character.equipment.impl.character.model;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class EquipmentDecorationFactory {

  public static IEquipmentStats createRenamedPrintDecoration(IEquipmentStats stats, String name) {
    if (stats instanceof IWeaponStats) {
      return getRenamedPrintDecoration((IWeaponStats) stats, name);
    }
    if (stats instanceof IArmourStats) {
      return getRenamedPrintDecoration((IArmourStats) stats, name);
    }
    if (stats instanceof IShieldStats) {
      return getRenamedPrintDecoration((IShieldStats) stats, name);
    }
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong."); //$NON-NLS-1$
  }

  private static IWeaponStats getRenamedPrintDecoration(final IWeaponStats stats, final String name) {
    return new IWeaponStats() {
      public int getAccuracy() {
        return stats.getAccuracy();
      }

      public int getDamage() {
        return stats.getDamage();
      }

      public ITraitType getDamageTraitType() {
        return stats.getDamageTraitType();
      }

      public HealthType getDamageType() {
        return stats.getDamageType();
      }

      public Integer getDefence() {
        return stats.getDefence();
      }

      public Integer getRange() {
        return stats.getRange();
      }

      public Integer getRate() {
        return stats.getRate();
      }

      public int getSpeed() {
        return stats.getSpeed();
      }

      public IIdentificate[] getTags() {
        return stats.getTags();
      }

      public ITraitType getTraitType() {
        return stats.getTraitType();
      }

      public boolean inflictsNoDamage() {
        return stats.inflictsNoDamage();
      }

      public IIdentificate getName() {
        return new Identificate(name);
      }

      public boolean isRangedCombat() {
        return stats.isRangedCombat();
      }

      @Override
      public IEquipmentStats[] getViews() {
        return new IEquipmentStats[] { this };
      }
    };
  }

  private static IArmourStats getRenamedPrintDecoration(final IArmourStats stats, final String newName) {
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
        return new Identificate(newName);
      }
    };
  }

  private static IShieldStats getRenamedPrintDecoration(final IShieldStats stats, final String name) {
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
    };
  }
}
