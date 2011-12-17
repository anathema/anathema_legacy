package net.sf.anathema.character.equipment.impl.reporting.sheet;

import net.sf.anathema.character.equipment.impl.reporting.stats.weapons.*;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionWeaponryTableEncoder extends AbstractWeaponryTableEncoder {

  public SecondEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont, resources);
  }

  @Override
  protected SecondEditionSpeedWeaponStatsGroup createSpeedGroup(IGenericTraitCollection collection, IEquipmentModifiers equipmentModifiers) {
    return new SecondEditionSpeedWeaponStatsGroup(getResources(), equipmentModifiers);
  }

  @Override
  protected AbstractDefenceWeaponStatsGroup createDefenceGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new SecondEditionDefenceWeaponStatsGroup(getResources(), character, traitCollection, character.getEquipmentModifiers());
  }

  @Override
  protected AccuracyWeaponStatsGroup createAccuracyGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new AccuracyWeaponStatsGroup(getResources(), traitCollection, character.getEquipmentModifiers());
  }

  @Override
  protected RateWeaponStatsGroup createRateGroup(IGenericCharacter character) {
    return new RateWeaponStatsGroup(getResources(), character.getEquipmentModifiers());
  }
}