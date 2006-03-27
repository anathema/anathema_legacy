package net.sf.anathema.character.abyssal.reporting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.IWeaponType;
import net.sf.anathema.character.generic.framework.reporting.datasource.MeleeWeaponDataSource;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.generic.impl.equipment.IEquippedWeapon;
import net.sf.anathema.character.generic.impl.equipment.WeaponStatisticsCalculator;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.resources.IResources;

public class AbyssalVoidstateReportTemplate extends ExaltVoidstateReportTemplate {

  public AbyssalVoidstateReportTemplate(IResources resources) {
    super(CharacterType.ABYSSAL, resources);
  }

  @Override
  protected void fillInBrawlWeapons(final Map<Object, Object> parameters, final IGenericCharacter character) {
    IWeaponType[] brawlWeaponList = new AbyssalBrawlWeaponConfiguration(character.getRules()).getBrawlWeaponList();
    WeaponStatisticsCalculator weaponStatisticsCalculator = new WeaponStatisticsCalculator(
        character,
        character.getRules(),
        isExalted(character));
    final List<IEquippedWeapon> weapons = new ArrayList<IEquippedWeapon>();
    for (IWeaponType weapon : brawlWeaponList) {
      IEquippedWeapon[] finalWeaponStatistics = weaponStatisticsCalculator.calculateWeaponStatistics(weapon);
      Collections.addAll(weapons, finalWeaponStatistics);
    }
    parameters.put(MELEE_WEAPON_DATA_SOURCE, new MeleeWeaponDataSource(
        getResources(),
        weapons.toArray(new IEquippedWeapon[weapons.size()])));
  }
}