package net.sf.anathema.character.reporting.sheet.second.equipment;

import java.awt.Color;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.DamageWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.DefenceWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.IWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.RateWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.SpeedWeaopnStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.WeaponNameStatsGroup;
import net.sf.anathema.character.reporting.sheet.util.AbstractTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class SecondEditionWeaponryTableEncoder extends AbstractTableEncoder {

  private final IResources resources;
  private final Font font;
  private final Font headerFont;

  public SecondEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources) {
    this.headerFont = new Font(baseFont, IVoidStateFormatConstants.FONT_SIZE, Font.NORMAL, Color.BLACK);
    this.font = new Font(baseFont, IVoidStateFormatConstants.FONT_SIZE, Font.NORMAL, Color.BLACK);
    this.resources = resources;
  }

  @Override
  protected PdfPTable createTable() {
    IWeaponStatsGroup[] groups = new IWeaponStatsGroup[] {
        new WeaponNameStatsGroup(resources),
        new SpeedWeaopnStatsGroup(resources),
        new AccuracyWeaponStatsGroup(resources),
        new DamageWeaponStatsGroup(resources),
        new DefenceWeaponStatsGroup(resources),
        new RateWeaponStatsGroup(resources) };
    float[] columnWidths = calculateColumnWidths(groups);
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    for (int index = 0; index < groups.length; index++) {
      table.addCell(createHeaderCell(
          groups[index].getTitle(),
          groups[index].getColumnCount(),
          index != groups.length - 1));
    }
    for (int line = 0; line < 8; line++) {
      encodeContentLine(table, groups);
    }
    return table;
  }

  private void encodeContentLine(PdfPTable table, IWeaponStatsGroup[] groups) {
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        table.addCell(createSpaceCell());
      }
      groups[index].addContent(table, font);
    }
  }

  private float[] calculateColumnWidths(IWeaponStatsGroup[] groups) {
    Float[] columnWidths = new Float[0];
    for (IWeaponStatsGroup group : groups) {
      if (columnWidths.length != 0) {
        columnWidths = ArrayUtilities.concat(columnWidths, new Float(0.2));
      }
      columnWidths = ArrayUtilities.concat(columnWidths, group.getColumnWeights());
    }
    return net.sf.anathema.lib.lang.ArrayUtilities.toPrimitive(columnWidths);
  }

  private PdfPCell createSpaceCell() {
    PdfPCell cell = new PdfPCell(new Phrase("", font)); //$NON-NLS-1$
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  private PdfPCell createHeaderCell(String text, int columnSpan, boolean useSpaceCell) {
    PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(useSpaceCell ? columnSpan + 1 : columnSpan);
    cell.setPaddingLeft(0);
    cell.setPaddingRight(0);
    return cell;
  }
}