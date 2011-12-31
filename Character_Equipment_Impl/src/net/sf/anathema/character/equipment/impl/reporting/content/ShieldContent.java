package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.impl.reporting.content.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.FatigueStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.shields.CloseCombatShieldStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.shields.RangedCombatShieldStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class ShieldContent extends AbstractEquipmentContent<IShieldStats> {

  public ShieldContent(IResources resources, IGenericCharacter character) {
    super(resources, character);
  }

  @Override
  public int getLineCount() {
    return 1;
  }

  @Override
  public IShieldStats[] getPrintStats() {
    return getEquipmentModel().getPrintShield();
  }

  @Override
  public IStatsGroup<IShieldStats>[] createStatsGroups() {
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup<IArmourStats>(getResources()),
        new CloseCombatShieldStatsGroup(getResources()),
        new RangedCombatShieldStatsGroup(getResources()),
        new MobilityPenaltyStatsGroup(getResources()),
        new FatigueStatsGroup(getResources()) };
   }
}
