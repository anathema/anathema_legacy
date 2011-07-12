package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.impl.reporting.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.AbstractWeaponryTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.RateWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.SecondEditionDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.SecondEditionSpeedWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionWeaponryTableEncoder extends AbstractWeaponryTableEncoder {

  public SecondEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources, IEquipmentModifiers equipment) {
    super(baseFont, resources, equipment);
  }

  @Override
  protected int getLineCount() {
    return 10;
  }

  @Override
  protected SecondEditionSpeedWeaponStatsGroup createSpeedGroup(IGenericTraitCollection collection) {
    return new SecondEditionSpeedWeaponStatsGroup(getResources(), equipment);
  }

  @Override
  protected AbstractDefenceWeaponStatsGroup createDefenceGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new SecondEditionDefenceWeaponStatsGroup(getResources(), character, traitCollection, equipment);
  }

  @Override
  protected AccuracyWeaponStatsGroup createAccuracyGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new AccuracyWeaponStatsGroup(getResources(), traitCollection, equipment);
  }

  @Override
  protected RateWeaponStatsGroup createRateGroup(IGenericCharacter character) {
    return new RateWeaponStatsGroup(getResources(), equipment);
  }
}