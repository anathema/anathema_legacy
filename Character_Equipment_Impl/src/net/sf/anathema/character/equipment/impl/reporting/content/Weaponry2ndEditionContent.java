package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.RateWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.SecondEditionDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.SecondEditionSpeedWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.lib.resources.IResources;

public class Weaponry2ndEditionContent extends AbstractWeaponryContent {

  public Weaponry2ndEditionContent(IResources resources, IGenericCharacter character) {
    super(resources, character);
  }

  @Override
  public int getLineCount() {
    return 10;
  }

  @Override
  protected SecondEditionSpeedWeaponStatsGroup createSpeedGroup() {
    return new SecondEditionSpeedWeaponStatsGroup(getResources(), getEquipmentModifiers());
  }

  @Override
  protected AbstractDefenceWeaponStatsGroup createDefenceGroup() {
    return new SecondEditionDefenceWeaponStatsGroup(getResources(), getCharacter(), getTraitCollection(), getEquipmentModifiers());
  }

  @Override
  protected AccuracyWeaponStatsGroup createAccuracyGroup() {
    return new AccuracyWeaponStatsGroup(getResources(), getTraitCollection(), getEquipmentModifiers());
  }

  @Override
  protected RateWeaponStatsGroup createRateGroup() {
    return new RateWeaponStatsGroup(getResources(), getEquipmentModifiers());
  }
}
