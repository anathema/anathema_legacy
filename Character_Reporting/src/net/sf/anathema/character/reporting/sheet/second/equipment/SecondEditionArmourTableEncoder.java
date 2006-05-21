package net.sf.anathema.character.reporting.sheet.second.equipment;

import net.sf.anathema.character.reporting.sheet.second.equipment.armourstats.FatiguePenaltyStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.armourstats.HardnessPenaltyStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.armourstats.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.armourstats.SoakArmourStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.stats.IEquipmentStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionArmourTableEncoder extends AbstractEquipmentTableEncoder {

  private final IResources resources;

  public SecondEditionArmourTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected IEquipmentStatsGroup[] createEquipmentGroups() {
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup(resources),
        new SoakArmourStatsGroup(resources),
        new MobilityPenaltyStatsGroup(resources),
        new FatiguePenaltyStatsGroup(resources),
        new HardnessPenaltyStatsGroup(resources) };
  }

  @Override
  protected int getLineCount() {
    return 4;
  }
}