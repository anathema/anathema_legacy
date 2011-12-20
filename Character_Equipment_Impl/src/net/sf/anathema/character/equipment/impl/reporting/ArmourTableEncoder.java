package net.sf.anathema.character.equipment.impl.reporting;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.impl.reporting.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.stats.armour.*;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class ArmourTableEncoder extends AbstractEquipmentTableEncoder<IArmourStats> {

  private final IResources resources;

  public ArmourTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) {
    IGenericCharacter character = content.getCharacter();
    PdfPTable armourTable = super.createTable(graphics, content, bounds);
    IArmourStats totalArmour = getEquipmentModel(character).getTotalPrintArmour(getLineCount());
    IEquipmentStatsGroup<IArmourStats>[] groups = createStatsGroups(content);
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
  protected IEquipmentStatsGroup<IArmourStats>[] createStatsGroups(ReportContent content) {
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup<IArmourStats>(resources),
        new SoakArmourStatsGroup(resources),
        new HardnessStatsGroup(resources),
        new MobilityPenaltyStatsGroup(resources),
        new FatigueStatsGroup(resources) };
  }

  @Override
  protected int getLineCount() {
    return 3;
  }

  @Override
  protected IArmourStats[] getPrintStats(ReportContent content) {
    return getEquipmentModel(content.getCharacter()).getPrintArmours();
  }
}
