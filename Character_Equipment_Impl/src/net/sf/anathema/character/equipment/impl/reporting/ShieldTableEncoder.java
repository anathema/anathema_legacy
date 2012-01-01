package net.sf.anathema.character.equipment.impl.reporting;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.impl.reporting.content.ShieldContent;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class ShieldTableEncoder extends EquipmentTableEncoder<IShieldStats, ShieldContent> {

  public ShieldTableEncoder(BaseFont baseFont, IResources resources) {
    super(ShieldContent.class, baseFont);
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) {
    IStatsGroup<IShieldStats>[] groups = createStatsGroups(content);
    float[] columnWidths = calculateColumnWidths(groups);
    PdfPTable shieldTable = new PdfPTable(columnWidths);
    shieldTable.setTotalWidth(bounds.width);
    encodeContent(shieldTable, content, bounds);
    return shieldTable;
  }
}
