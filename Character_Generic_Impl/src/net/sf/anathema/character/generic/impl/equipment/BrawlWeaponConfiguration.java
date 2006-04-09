package net.sf.anathema.character.generic.impl.equipment;

import java.util.List;

import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class BrawlWeaponConfiguration extends AbstractBrawlWeaponConfiguration {

  public BrawlWeaponConfiguration(IExaltedRuleSet rules) {
    super(rules);
  }

  @Override
  protected void buildSecondEditionBrawlWeaponList(List<IWeaponType> weapons) {
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Fist", AbilityType.MartialArts, 5, 1, 0, HealthType.Bashing, 2, 3)); //$NON-NLS-1$
    weapons.add(new MeleeWeaponType(
        "Weapons.Brawl.Clinch", AbilityType.MartialArts, 5, 0, 0, HealthType.Bashing, null, 1)); //$NON-NLS-1$
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Kick", AbilityType.MartialArts, 5, 0, 3, HealthType.Bashing, -2, 2)); //$NON-NLS-1$
  }

  @Override
  protected void buildPowerCombatBrawlWeaponList(List<IWeaponType> weapons) {
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Fist", AbilityType.Brawl, 0, 1, 0, HealthType.Bashing, 2, 5)); //$NON-NLS-1$
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Clinch", AbilityType.Brawl, -6, 0, 2, HealthType.Bashing, 0, 1)); //$NON-NLS-1$
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Kick", AbilityType.Brawl, -3, 1, 3, HealthType.Bashing, -3, 3)); //$NON-NLS-1$
  }

  @Override
  protected void buildCoreRulesBrawlWeaponList(List<IWeaponType> weapons) {
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Fist", AbilityType.Brawl, 0, 0, 0, HealthType.Bashing, 0, null)); //$NON-NLS-1$
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Clinch", AbilityType.Brawl, 0, 0, 2, HealthType.Bashing, 0, null)); //$NON-NLS-1$
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Kick", AbilityType.Brawl, -3, -1, 2, HealthType.Bashing, -1, null)); //$NON-NLS-1$
  }
}