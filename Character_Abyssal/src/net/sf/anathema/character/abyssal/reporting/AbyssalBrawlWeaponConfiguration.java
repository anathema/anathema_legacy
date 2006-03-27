package net.sf.anathema.character.abyssal.reporting;

import java.util.List;

import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.equipment.BrawlWeaponConfiguration;
import net.sf.anathema.character.generic.impl.equipment.MeleeWeaponType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class AbyssalBrawlWeaponConfiguration extends BrawlWeaponConfiguration {

  public AbyssalBrawlWeaponConfiguration(IExaltedRuleSet rules) {
    super(rules);
  }

  @Override
  protected void buildSecondEditionBrawlWeaponList(List<IWeaponType> weapons) {
    throw new UnsupportedOperationException("2nd Edition Abyssals not yet available."); //$NON-NLS-1$
  }

  @Override
  protected void buildPowerCombatBrawlWeaponList(List<IWeaponType> weapons) {
    super.buildPowerCombatBrawlWeaponList(weapons);
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Fang", AbilityType.Brawl, -3, 0, 0, HealthType.Lethal, -2, 1)); //$NON-NLS-1$
  }

  @Override
  protected void buildCoreRulesBrawlWeaponList(List<IWeaponType> weapons) {
    super.buildCoreRulesBrawlWeaponList(weapons);
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Fang", AbilityType.Brawl, -3, 0, 0, HealthType.Lethal, -2, null)); //$NON-NLS-1$
  }
}