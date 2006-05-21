package net.sf.anathema.character.reporting.sheet.second.equipment;

import java.awt.Color;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class WeaponEncodingUtilities {

  public static Float[] createStandardColumnWeights(int columnCount) {
    Float[] columnWeights = new Float[columnCount];
    for (int index = 0; index < columnWeights.length; index++) {
      columnWeights[index] = new Float(1);
    }
    return columnWeights;
  }

  public static final PdfPCell createContentCellTable(
      Color borderColor,
      String text,
      Font font,
      float borderWidth,
      int border) {
    return createContentCellTable(borderColor, text, font, borderWidth, border, true);
  }

  public static final PdfPCell createContentCellTable(
      Color borderColor,
      String text,
      Font font,
      float borderWidth,
      int border,
      boolean enabled) {
    PdfPCell innerCell = new PdfPCell(new Phrase(text, font));
    innerCell.setPaddingTop(0.5f);
    innerCell.setBorderColor(borderColor);
    innerCell.setBorderWidth(borderWidth);
    innerCell.setBorder(border);
    if (!enabled) {
      innerCell.setBackgroundColor(borderColor);
    }

    PdfPTable cellTable = new PdfPTable(1);
    cellTable.setWidthPercentage(100);
    cellTable.addCell(innerCell);

    PdfPCell outerCell = new PdfPCell();
    outerCell.addElement(cellTable);
    outerCell.setBorder(Rectangle.NO_BORDER);
    outerCell.setPadding(1);
    return outerCell;
  }
}