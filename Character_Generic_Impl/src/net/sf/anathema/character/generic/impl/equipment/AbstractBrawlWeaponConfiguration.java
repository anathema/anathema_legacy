package net.sf.anathema.character.generic.impl.equipment;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.IBrawlWeaponConfiguration;
import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;

public abstract class AbstractBrawlWeaponConfiguration implements IBrawlWeaponConfiguration {
  private final IExaltedRuleSet rules;

  public AbstractBrawlWeaponConfiguration(IExaltedRuleSet rules) {
    this.rules = rules;
  }

  public IWeaponType[] getBrawlWeaponList() {
    final List<IWeaponType> weapons = new ArrayList<IWeaponType>();
    rules.accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        buildCoreRulesBrawlWeaponList(weapons);
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        buildPowerCombatBrawlWeaponList(weapons);
      }

      public void visitSecondEdition(IExaltedRuleSet set) {
        buildSecondEditionBrawlWeaponList(weapons);
      }
    });
    return weapons.toArray(new IWeaponType[weapons.size()]);
  }

  protected abstract void buildSecondEditionBrawlWeaponList(List<IWeaponType> weapons);

  protected abstract void buildPowerCombatBrawlWeaponList(List<IWeaponType> weapons);

  protected abstract void buildCoreRulesBrawlWeaponList(List<IWeaponType> weapons);
}