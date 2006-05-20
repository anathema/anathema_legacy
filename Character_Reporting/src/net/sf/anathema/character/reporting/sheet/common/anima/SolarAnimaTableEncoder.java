package net.sf.anathema.character.reporting.sheet.common.anima;

import java.awt.Color;

import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class SolarAnimaTableEncoder {

  private final IResources resources;
  private Font headerFont;
  private Font font;

  public SolarAnimaTableEncoder(IResources resources, BaseFont baseFont, float fontSize) {
    this.resources = resources;
    this.headerFont = new Font(baseFont, fontSize, Font.ITALIC, Color.BLACK);
    this.font = new Font(baseFont, fontSize, Font.NORMAL, Color.BLACK);
  }

  public void encodeTable(PdfContentByte directContent, Bounds bounds) throws DocumentException {
    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable();
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
  }

  private PdfPTable createTable() {
    PdfPTable table = new PdfPTable(new float[] { 0.15f, 0.6f, 0.25f });
    table.setWidthPercentage(100);
    table.addCell(createHeaderCell(resources.getString("Sheet.AnimaTable.Header.Motes"))); //$NON-NLS-1$
    table.addCell(createHeaderCell(resources.getString("Sheet.AnimaTable.Header.BannerFlare"))); //$NON-NLS-1$
    table.addCell(createHeaderCell(resources.getString("Sheet.AnimaTable.Header.Stealth"))); //$NON-NLS-1$

    table.addCell(createContentCell("1-3")); //$NON-NLS-1$
    table.addCell(createContentCell(resources.getString("Sheet.AnimaTable.CasteMarkGlitters"))); //$NON-NLS-1$
    table.addCell(createContentCell(resources.getString("Sheet.AnimaTable.StealthNormal"))); //$NON-NLS-1$

    table.addCell(createContentCell("4-7")); //$NON-NLS-1$
    table.addCell(createContentCell(resources.getString("Sheet.AnimaTable.CasteMarkBurns"))); //$NON-NLS-1$
    table.addCell(createContentCell("+2")); //$NON-NLS-1$

    table.addCell(createContentCell("8-10")); //$NON-NLS-1$
    table.addCell(createContentCell(resources.getString("Sheet.AnimaTable.CoruscantAura"))); //$NON-NLS-1$
    String stealthImpossible = resources.getString("Sheet.AnimaTable.StealthImpossible"); //$NON-NLS-1$
    table.addCell(createContentCell(stealthImpossible));

    table.addCell(createContentCell("11-15")); //$NON-NLS-1$
    table.addCell(createContentCell(resources.getString("Sheet.AnimaTable.BrilliantBonfire"))); //$NON-NLS-1$
    table.addCell(createContentCell(stealthImpossible));

    table.addCell(createContentCell("16+")); //$NON-NLS-1$
    table.addCell(createContentCell(resources.getString("Sheet.AnimaTable.TotemicAura"))); //$NON-NLS-1$
    table.addCell(createContentCell(stealthImpossible));
    return table;
  }

  private PdfPCell createContentCell(String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, font));
    cell.setPaddingTop(1);
    cell.setPaddingBottom(2);
    return cell;
  }

  private PdfPCell createHeaderCell(String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
    cell.setBorder(Rectangle.BOTTOM);
    return cell;
  }
}