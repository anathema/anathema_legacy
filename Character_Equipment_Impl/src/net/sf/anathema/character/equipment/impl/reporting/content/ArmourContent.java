package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.impl.reporting.content.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.FatigueStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.HardnessStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.SoakArmourStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class ArmourContent extends AbstractEquipmentContent<IArmourStats> {

  public ArmourContent(IResources resources, IGenericCharacter character) {
    super(resources, character);
  }

  @Override
  public int getLineCount() {
    return 3;
  }

  @Override
  public IArmourStats[] getPrintStats() {
    return getEquipmentModel().getPrintArmours();
  }

  @Override
  public IStatsGroup<IArmourStats>[] createStatsGroups() {
    return new IEquipmentStatsGroup[] { new EquipmentNameStatsGroup<IArmourStats>(getResources()), new SoakArmourStatsGroup(
      getResources()), new HardnessStatsGroup(getResources()), new MobilityPenaltyStatsGroup(getResources()),
      new FatigueStatsGroup(getResources()) };
  }

  public IArmourStats getTotalArmour() {
    int lineCount = getLineCount();
    return getEquipmentModel().getTotalPrintArmour(lineCount);
  }
}
