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
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class FirstEditionWeaponryTableEncoder extends AbstractWeaponryTableEncoder {

  private final IGenericTraitCollection collection;

  public FirstEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources, IGenericTraitCollection collection) {
    super(baseFont, resources);
    this.collection = collection;
  }

  @Override
  protected AbstractSpeedWeaponStatsGroup createSpeedGroup() {
    return new FirstEditionSpeedWeaponStatsGroup(getResources(), collection);
  }

  @Override
  protected AbstractDefenceWeaponStatsGroup createDefenceGroup(IGenericCharacter character) {
    return new FirstEditionDefenceWeaponStatsGroup(getResources(), character);
  }

  @Override
  protected AccuracyWeaponStatsGroup createAccuracyGroup(IGenericCharacter character) {
    return new FirstEditionAccuracyWeaponStatsGroup(getResources(), character);
  }

  @Override
  protected RateWeaponStatsGroup createRateGroup(IGenericCharacter character) {
    if (character.getRules() == ExaltedRuleSet.CoreRules) {
      return new CoreRulesRateWeaponStatsGroup(getResources());
    }
    return new RateWeaponStatsGroup(getResources());
  }
}