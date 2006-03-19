package net.sf.anathema.character.generic.framework.reporting.datasource;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.health.IHealthTypeVisitor;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;

public class MeleeWeaponDataSource implements IReportDataSource {

  public static final String COLUMN_PRINT_NAME = "PRINT_NAME"; //$NON-NLS-1$
  public static final String COLUMN_SPEED = "SPEED"; //$NON-NLS-1$
  public static final String COLUMN_ACCURACY = "ACCURACY"; //$NON-NLS-1$
  public static final String COLUMN_DAMAGE = "DAMAGE"; //$NON-NLS-1$
  public static final String COLUMN_DEFENSE = "DEFENSE"; //$NON-NLS-1$
  public static final String COLUMN_RATE = "RATE"; //$NON-NLS-1$
  private final IWeaponType[] weapons;
  private final IResources resources;
  private final IGenericTraitCollection colletion;
  private final IExaltedRuleSet rules;

  public MeleeWeaponDataSource(
      IResources resources,
      IGenericTraitCollection collection,
      IExaltedRuleSet rules,
      IWeaponType[] weapons) {
    this.resources = resources;
    this.colletion = collection;
    this.rules = rules;
    this.weapons = weapons;
  }

  public int getRowCount() {
    return weapons.length;
  }

  public Object getValue(int currentRow, String columnName) {
    if (currentRow >= getRowCount()) {
      return ""; //$NON-NLS-1$
    }
    IWeaponType weapon = weapons[currentRow];
    for (AbilityType ability : weapon.getAllowedAbilities()) {
      if (COLUMN_PRINT_NAME.equals(columnName)) {
        String weaponName = resources.getString(weapon.getId());
        if (weapon.getAllowedAbilities().length == 1) {
          return weaponName;
        }
        return weaponName + " (" + resources.getString(ability.getId()) + ")"; //$NON-NLS-1$//$NON-NLS-2$
      }
      int dexterityValue = colletion.getTrait(AttributeType.Dexterity).getCurrentValue();
      if (COLUMN_SPEED.equals(columnName)) {
        int witsValue = colletion.getTrait(AttributeType.Wits).getCurrentValue();
        return String.valueOf(dexterityValue + witsValue + weapon.getSpeed());
      }
      int abilityValue = colletion.getTrait(ability).getCurrentValue();
      if (COLUMN_ACCURACY.equals(columnName)) {
        return String.valueOf(dexterityValue + abilityValue + weapon.getAccuracy());
      }
      if (COLUMN_DAMAGE.equals(columnName)) {
        int strengthValue = colletion.getTrait(AttributeType.Strength).getCurrentValue();
        final String damageValue = String.valueOf(strengthValue + weapon.getDamage());
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
        return String.valueOf(dexterityValue + abilityValue + weapon.getDefense());
      }
      if (COLUMN_RATE.equals(columnName)) {
        return weapon.getRate() != null && rules == ExaltedRuleSet.PowerCombat ? String.valueOf(weapon.getRate()) : "-"; //$NON-NLS-1$
      }
    }
    throw new IllegalArgumentException("No column with name '" + columnName + "'."); //$NON-NLS-1$ //$NON-NLS-2$
  }
}