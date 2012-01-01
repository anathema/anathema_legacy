package net.sf.anathema.character.equipment.impl.reporting;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.impl.reporting.content.ArmourContent;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.armour.*;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class ArmourTableEncoder extends EquipmentTableEncoder<IArmourStats, ArmourContent> {

  public ArmourTableEncoder(Class<? extends ArmourContent> contentClass, BaseFont baseFont) {
    super(contentClass, baseFont);
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
    PdfPTable armourTable = super.createTable(graphics, reportContent, bounds);
    ArmourContent content = createContent(reportContent);
    IArmourStats totalArmour = content.getTotalArmour();
    IStatsGroup<IArmourStats>[] groups = content.createStatsGroups();
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        armourTable.addCell(createSpaceCell());
      }
      IStatsGroup<IArmourStats> group = groups[index];
      if (group instanceof IArmourStatsGroup) {
        ((IArmourStatsGroup) group).addTotal(armourTable, getFont(), totalArmour);
      }
      else {
        group.addContent(armourTable, getFont(), totalArmour);
      }
    }
    return armourTable;
  }
}
