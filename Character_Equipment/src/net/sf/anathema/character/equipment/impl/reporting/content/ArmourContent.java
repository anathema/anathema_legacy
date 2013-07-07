package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.impl.reporting.content.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.FatigueStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.HardnessStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.SoakArmourStatsGroup;
import net.sf.anathema.character.main.equipment.weapon.IArmourStats;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class ArmourContent extends AbstractEquipmentContent<IArmourStats> {

  public ArmourContent(Hero hero, Resources resources) {
    super(hero, resources);
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
    return new IEquipmentStatsGroup[]{new EquipmentNameStatsGroup<IArmourStats>(getResources()), new SoakArmourStatsGroup(getResources()),
            new HardnessStatsGroup(getResources()), new MobilityPenaltyStatsGroup(getResources()), new FatigueStatsGroup(getResources())};
  }

  public IArmourStats getTotalArmour() {
    int lineCount = getLineCount();
    return getEquipmentModel().getEffectivePrintArmour(getResources(), lineCount);
  }
}
