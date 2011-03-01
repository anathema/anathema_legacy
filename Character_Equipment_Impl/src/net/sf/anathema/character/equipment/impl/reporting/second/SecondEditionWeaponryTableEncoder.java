package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.impl.reporting.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.AbstractWeaponryTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.RateWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.SecondEditionDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.SecondEditionSpeedWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionWeaponryTableEncoder extends AbstractWeaponryTableEncoder {

  public SecondEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont, resources);
  }

  @Override
  protected SecondEditionSpeedWeaponStatsGroup createSpeedGroup(IGenericTraitCollection collection) {
    return new SecondEditionSpeedWeaponStatsGroup(getResources());
  }

  @Override
  protected AbstractDefenceWeaponStatsGroup createDefenceGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new SecondEditionDefenceWeaponStatsGroup(getResources(), character, traitCollection);
  }

  @Override
  protected AccuracyWeaponStatsGroup createAccuracyGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new AccuracyWeaponStatsGroup(getResources(), traitCollection);
  }

  @Override
  protected RateWeaponStatsGroup createRateGroup(IGenericCharacter character) {
    return new RateWeaponStatsGroup(getResources());
  }
}