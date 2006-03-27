package net.sf.anathema.character.generic.impl;

import net.sf.anathema.character.generic.ICombatStatisticsConfiguration;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class CombatStatisticsConfiguration implements ICombatStatisticsConfiguration {

  private final IExaltedRuleSet rules;
  private final IGenericTraitCollection collection;
  private final boolean isExalted;

  public CombatStatisticsConfiguration(IGenericTraitCollection collection, IExaltedRuleSet rules, boolean isExalted) {
    this.collection = collection;
    this.rules = rules;
    this.isExalted = isExalted;
  }

  public int getBaseInitiative() {
    return getDexterityValue() + collection.getTrait(AttributeType.Wits).getCurrentValue();
  }

  public int getDodgePool() {
    final int dodgeValue = collection.getTrait(AbilityType.Dodge).getCurrentValue();
    final int[] dodgePool = new int[] { dodgeValue + getDexterityValue() };
    rules.accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        if (dodgeValue == 0) {
          dodgePool[0] = Math.max(0, dodgePool[0] - 2);
        }
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        int permanentEssence = collection.getTrait(OtherTraitType.Essence).getCurrentValue();
        if (permanentEssence > 1) {
          dodgePool[0] = dodgePool[0] + permanentEssence;
        }
      }

      public void visitSecondEdition(IExaltedRuleSet set) {
        int permanentEssence = collection.getTrait(OtherTraitType.Essence).getCurrentValue();
        double dodgeDefense = dodgePool[0];
        if (permanentEssence > 1) {
          dodgeDefense += permanentEssence;
        }
        dodgeDefense /= 2;
        dodgePool[0] = (int) (isExalted ? Math.ceil(dodgeDefense) : Math.floor(dodgeDefense));
      }
    });
    return dodgePool[0];
  }

  public int getKnockdownResistance() {
    return getStaminaValue() + collection.getTrait(AbilityType.Resistance).getCurrentValue();
  }

  public int getStunThreshold() {
    return getStaminaValue();
  }

  public int getStunDuration() {
    return Math.max(6 - getStaminaValue(), 0);
  }

  private int getStaminaValue() {
    return collection.getTrait(AttributeType.Stamina).getCurrentValue();
  }

  private int getDexterityValue() {
    return collection.getTrait(AttributeType.Dexterity).getCurrentValue();
  }

  public int getMinimumDamage() {
    final int[] damage = new int[1];
    rules.accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        damage[0] = 1;
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        damage[0] = collection.getTrait(OtherTraitType.Essence).getCurrentValue();
      }

      public void visitSecondEdition(IExaltedRuleSet set) {
        damage[0] = collection.getTrait(OtherTraitType.Essence).getCurrentValue();
      }
    });
    return damage[0];
  }
}