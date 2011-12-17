package net.sf.anathema.character.equipment.impl.reporting.sheet;

import net.sf.anathema.character.equipment.impl.reporting.stats.weapons.*;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class FirstEditionWeaponryTableEncoder extends AbstractWeaponryTableEncoder {

  public FirstEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont, resources);
  }

  @Override
  protected AbstractSpeedWeaponStatsGroup createSpeedGroup(IGenericTraitCollection collection, IEquipmentModifiers equipment) {
    return new FirstEditionSpeedWeaponStatsGroup(getResources(), collection, equipment);
  }

  @Override
  protected AbstractDefenceWeaponStatsGroup createDefenceGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new FirstEditionDefenceWeaponStatsGroup(getResources(), character, traitCollection, character.getEquipmentModifiers());
  }

  @Override
  protected AccuracyWeaponStatsGroup createAccuracyGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new FirstEditionAccuracyWeaponStatsGroup(getResources(), character, traitCollection, character.getEquipmentModifiers());
  }

  @Override
  protected RateWeaponStatsGroup createRateGroup(IGenericCharacter character) {
    if (character.getRules() == ExaltedRuleSet.CoreRules) {
      return new CoreRulesRateWeaponStatsGroup(getResources(), character.getEquipmentModifiers());
    }
    return new RateWeaponStatsGroup(getResources(), character.getEquipmentModifiers());
  }
}