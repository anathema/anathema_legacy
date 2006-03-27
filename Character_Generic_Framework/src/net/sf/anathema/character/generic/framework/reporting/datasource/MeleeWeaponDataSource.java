package net.sf.anathema.character.generic.framework.reporting.datasource;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.health.IHealthTypeVisitor;
import net.sf.anathema.character.generic.impl.equipment.IEquippedWeapon;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;

public class MeleeWeaponDataSource implements IReportDataSource {

  public static final String COLUMN_PRINT_NAME = "PRINT_NAME"; //$NON-NLS-1$
  public static final String COLUMN_SPEED = "SPEED"; //$NON-NLS-1$
  public static final String COLUMN_ACCURACY = "ACCURACY"; //$NON-NLS-1$
  public static final String COLUMN_DAMAGE = "DAMAGE"; //$NON-NLS-1$
  public static final String COLUMN_DEFENSE = "DEFENSE"; //$NON-NLS-1$
  public static final String COLUMN_RATE = "RATE"; //$NON-NLS-1$

  private final IEquippedWeapon[] weapons;
  private final IResources resources;

  public MeleeWeaponDataSource(IResources resources, IEquippedWeapon[] weapons) {
    this.resources = resources;
    this.weapons = weapons;
  }

  public int getRowCount() {
    return weapons.length;
  }

  public Object getValue(int currentRow, String columnName) {
    if (currentRow >= getRowCount()) {
      return ""; //$NON-NLS-1$
    }
    IEquippedWeapon weapon = weapons[currentRow];
    if (COLUMN_PRINT_NAME.equals(columnName)) {
      String weaponName = resources.getString(weapon.getId());
      if (weapon.getAbility() != null) {
        weaponName = weaponName.concat(" (" + resources.getString(weapon.getAbility().getId()) + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      return weaponName;
    }
    if (COLUMN_SPEED.equals(columnName)) {
      return String.valueOf(weapon.getSpeed());
    }
    if (COLUMN_ACCURACY.equals(columnName)) {
      return String.valueOf(weapon.getAccuracy());
    }
    if (COLUMN_DAMAGE.equals(columnName)) {
      final String damageValue = String.valueOf(weapon.getDamage());
      final String[] damageString = new String[1];
      weapon.getDamageType().accept(new IHealthTypeVisitor() {
        public void visitBashing(HealthType type) {
          damageString[0] = damageValue.concat(resources.getString("Weapons.Damage.Bashing.Short")); //$NON-NLS-1$
        }

        public void visitLethal(HealthType type) {
          damageString[0] = damageValue.concat(resources.getString("Weapons.Damage.Lethal.Short")); //$NON-NLS-1$
        }

        public void visitAggravated(HealthType type) {
          damageString[0] = damageValue.concat(resources.getString("Weapons.Damage.Aggravated.Short")); //$NON-NLS-1$
        }
      });
      return damageString[0];
    }
    if (COLUMN_DEFENSE.equals(columnName)) {
      return weapon.getDefense() != null ? String.valueOf(weapon.getDefense()) : "-"; //$NON-NLS-1$
    }
    if (COLUMN_RATE.equals(columnName)) {
      return weapon.getRate() != null ? String.valueOf(weapon.getRate()) : "-"; //$NON-NLS-1$
    }

    throw new IllegalArgumentException("No column with name '" + columnName + "'."); //$NON-NLS-1$ //$NON-NLS-2$
  }
}