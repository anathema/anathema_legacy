package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.impl.reporting.second.AbstractEquipmentTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.FatigueStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.HardnessStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.IArmourStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.armourstats.SoakArmourStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;

public class ArmourTableEncoder extends AbstractEquipmentTableEncoder<IArmourStats> {

  private final IResources resources;

  public ArmourTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    PdfPTable armourTable = super.createTable(directContent, character, bounds);
    IArmourStats totalArmour = getEquipmentModel(character).getTotalPrintArmour(getLineCount());
    IEquipmentStatsGroup<IArmourStats>[] groups = createStatsGroups(character);
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        armourTable.addCell(createSpaceCell());
      }
      IEquipmentStatsGroup<IArmourStats> group = groups[index];
      if (group instanceof IArmourStatsGroup) {
        ((IArmourStatsGroup) group).addTotal(armourTable, getFont(), totalArmour);
      }
      else {
        group.addContent(armourTable, getFont(), totalArmour);
      }
    }
    return armourTable;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IEquipmentStatsGroup<IArmourStats>[] createStatsGroups(IGenericCharacter character) {
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup<IArmourStats>(resources),
        new SoakArmourStatsGroup(resources),
        new HardnessStatsGroup(resources),
        new MobilityPenaltyStatsGroup(resources),
        new FatigueStatsGroup(resources) };
  }

  @Override
  protected int getLineCount() {
    return 5;
  }

  @Override
  protected IArmourStats[] getPrintStats(IGenericCharacter character) {
    return getEquipmentModel(character).getPrintArmours();
  }
}