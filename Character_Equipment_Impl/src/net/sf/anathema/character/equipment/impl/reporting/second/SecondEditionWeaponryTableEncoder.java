package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.impl.reporting.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.AbstractWeaponryTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.SecondEditionDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.SecondEditionSpeedWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionWeaponryTableEncoder extends AbstractWeaponryTableEncoder {

  public SecondEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont, resources);
  }

  @Override
  protected SecondEditionSpeedWeaponStatsGroup getSpeedWeaponStatsGroup() {
    return new SecondEditionSpeedWeaponStatsGroup(getResources());
  }

  @Override
  protected AbstractDefenceWeaponStatsGroup getDefenceWeaponStatsGroup(IGenericCharacter character) {
    return new SecondEditionDefenceWeaponStatsGroup(getResources(), character);
  }
}