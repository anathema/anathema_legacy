package net.sf.anathema.character.equipment.impl.reporting.first;

import net.sf.anathema.character.equipment.impl.reporting.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.AbstractSpeedWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.AbstractWeaponryTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.RateWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.first.weaponstats.CoreRulesRateWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.first.weaponstats.FirstEditionAccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.first.weaponstats.FirstEditionDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.first.weaponstats.FirstEditionSpeedWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class FirstEditionWeaponryTableEncoder extends AbstractWeaponryTableEncoder {
	
  public FirstEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources, IEquipmentModifiers equipment) {
    super(baseFont, resources, equipment);
  }

  @Override
  protected AbstractSpeedWeaponStatsGroup createSpeedGroup(IGenericTraitCollection collection) {
    return new FirstEditionSpeedWeaponStatsGroup(getResources(), collection, equipment);
  }

  @Override
  protected AbstractDefenceWeaponStatsGroup createDefenceGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new FirstEditionDefenceWeaponStatsGroup(getResources(), character, traitCollection, equipment);
  }

  @Override
  protected AccuracyWeaponStatsGroup createAccuracyGroup(IGenericCharacter character, IGenericTraitCollection traitCollection) {
    return new FirstEditionAccuracyWeaponStatsGroup(getResources(), character, traitCollection, equipment);
  }

  @Override
  protected RateWeaponStatsGroup createRateGroup(IGenericCharacter character) {
    if (character.getRules() == ExaltedRuleSet.CoreRules) {
      return new CoreRulesRateWeaponStatsGroup(getResources(), equipment);
    }
    return new RateWeaponStatsGroup(getResources(), equipment);
  }
}