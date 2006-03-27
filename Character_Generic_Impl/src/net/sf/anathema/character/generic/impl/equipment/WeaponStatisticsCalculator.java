package net.sf.anathema.character.generic.impl.equipment;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class WeaponStatisticsCalculator {

  private final IExaltedRuleSet rules;
  private final IGenericTraitCollection collection;
  private final boolean isExalted;

  public WeaponStatisticsCalculator(IGenericTraitCollection collection, IExaltedRuleSet rules, boolean isExalted) {
    this.collection = collection;
    this.rules = rules;
    this.isExalted = isExalted;
  }

  public IEquippedWeapon[] calculateWeaponStatistics(final IWeaponType weapon) {
    List<IEquippedWeapon> finalStatistics = new ArrayList<IEquippedWeapon>();
    AbilityType[] allowedAbilities = weapon.getAllowedAbilities();
    for (AbilityType ability : allowedAbilities) {
      AbilityType printAbility = allowedAbilities.length > 1 ? ability : null;
      int speed = calulateWeaponSpeed(weapon);
      int accuracy = calculateWeaponAccuracy(weapon, ability);
      int damage = calculateWeaponDamage(weapon);
      Integer defense = calculateDefense(weapon, ability);
      finalStatistics.add(new EquippedWeapon(
          weapon.getId(),
          printAbility,
          speed,
          accuracy,
          damage,
          weapon.getDamageType(),
          defense,
          weapon.getRate()));
    }
    return finalStatistics.toArray(new IEquippedWeapon[finalStatistics.size()]);
  }

  private Integer calculateDefense(final IWeaponType weapon, AbilityType ability) {
    final int dexterityValue = collection.getTrait(AttributeType.Dexterity).getCurrentValue();
    final int abilityValue = collection.getTrait(ability).getCurrentValue();
    final int[] defense = new int[1];
    rules.accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        defense[0] = dexterityValue + abilityValue + weapon.getDefense();
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        defense[0] = dexterityValue + abilityValue + weapon.getDefense();
      }

      public void visitSecondEdition(IExaltedRuleSet set) {
        double physicalDefense = (dexterityValue + abilityValue + weapon.getDefense()) / 2;
        defense[0] = (int) (isExalted ? Math.ceil(physicalDefense) : Math.floor(physicalDefense));
      }
    });
    return defense[0];
  }

  private int calculateWeaponDamage(IWeaponType weapon) {
    return collection.getTrait(AttributeType.Strength).getCurrentValue() + weapon.getDamage();
  }

  private int calculateWeaponAccuracy(IWeaponType weapon, AbilityType ability) {
    final int dexterityValue = collection.getTrait(AttributeType.Dexterity).getCurrentValue();
    final int abilityValue = collection.getTrait(ability).getCurrentValue();
    return dexterityValue + abilityValue + weapon.getAccuracy();
  }

  private int calulateWeaponSpeed(final IWeaponType weapon) {
    final int dexterityValue = collection.getTrait(AttributeType.Dexterity).getCurrentValue();
    final int witsValue = collection.getTrait(AttributeType.Wits).getCurrentValue();
    final int[] speed = new int[1];
    rules.accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        speed[0] = dexterityValue + witsValue + weapon.getSpeed();
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        speed[0] = dexterityValue + witsValue + weapon.getSpeed();
      }

      public void visitSecondEdition(IExaltedRuleSet set) {
        speed[0] = weapon.getSpeed();
      }
    });
    return speed[0];
  }
}