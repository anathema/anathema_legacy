package net.sf.anathema.character.abyssal.reporting;

import java.util.List;

import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.DefaultVoidstateSubreports;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.equipment.MeleeWeaponType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;

public class AbyssalVoidStateSubreports extends DefaultVoidstateSubreports {

  public AbyssalVoidStateSubreports() {
    super(CharacterType.ABYSSAL);
  }

  @Override
  public void buildPowerCombatBrawlWeaponList(List<IWeaponType> weapons) {
    super.buildPowerCombatBrawlWeaponList(weapons);
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Fang", AbilityType.Brawl, -3, 0, 0, HealthType.Lethal, -2, 1)); //$NON-NLS-1$
  }

  @Override
  public void buildCoreRulesBrawlWeaponList(List<IWeaponType> weapons) {
    super.buildCoreRulesBrawlWeaponList(weapons);
    weapons.add(new MeleeWeaponType("Weapons.Brawl.Fang", AbilityType.Brawl, -3, 0, 0, HealthType.Lethal, -2, null)); //$NON-NLS-1$
  }
}