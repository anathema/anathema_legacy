package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.model.EquipmentModel;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.RateWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.SecondEditionDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.SecondEditionSpeedWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.SubBoxContent;
import net.sf.anathema.lib.resources.Resources;

public class WeaponryContent extends AbstractWeaponryContent implements SubBoxContent {

  private final EquipmentHeroEvaluator provider;
  private final EquipmentOptionsProvider optionProvider;

  public WeaponryContent(Resources resources, IGenericCharacter character) {
    super(resources, character);
    EquipmentModel additionalModel = (EquipmentModel) character.getAdditionalModel(EquipmentAdditionalModelTemplate.ID);
    provider = additionalModel.getCharacterDataProvider();
    optionProvider = additionalModel.getCharacterOptionProvider();
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
    return new SecondEditionDefenceWeaponStatsGroup(getResources(), getCharacter(), getTraitCollection(), provider, optionProvider);
  }

  @Override
  protected AccuracyWeaponStatsGroup createAccuracyGroup() {
    return new AccuracyWeaponStatsGroup(getResources(), getTraitCollection(), provider, optionProvider);
  }

  @Override
  protected RateWeaponStatsGroup createRateGroup() {
    return new RateWeaponStatsGroup(getResources());
  }

  @Override
  public String getHeader() {
    return getResources().getString("Sheet.Header.Weapons");
  }
}
