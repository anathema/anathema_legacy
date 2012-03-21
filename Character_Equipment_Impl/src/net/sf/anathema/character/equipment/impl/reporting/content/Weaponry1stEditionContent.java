package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AbstractSpeedWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.FirstEditionAccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.FirstEditionDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.FirstEditionSpeedWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.RateWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.lib.resources.IResources;

public class Weaponry1stEditionContent extends AbstractWeaponryContent {

  public Weaponry1stEditionContent(IResources resources, IGenericCharacter character) {
    super(resources, character);
  }

  @Override
  public int getLineCount() {
    return 20;
  }

  @Override
  protected AbstractSpeedWeaponStatsGroup createSpeedGroup() {
    return new FirstEditionSpeedWeaponStatsGroup(getResources(), getTraitCollection(), getEquipmentModifiers());
  }

  @Override
  protected AbstractDefenceWeaponStatsGroup createDefenceGroup() {
    return new FirstEditionDefenceWeaponStatsGroup(getResources(), getCharacter(), getTraitCollection(), getEquipmentModifiers());
  }

  @Override
  protected AccuracyWeaponStatsGroup createAccuracyGroup() {
    return new FirstEditionAccuracyWeaponStatsGroup(getResources(), getCharacter(), getTraitCollection(), getEquipmentModifiers());
  }

  @Override
  protected RateWeaponStatsGroup createRateGroup() {
    return new RateWeaponStatsGroup(getResources(), getEquipmentModifiers());
  }
}
