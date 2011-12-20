package net.sf.anathema.character.reporting.pdf.rendering.general.table;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;

public abstract class AbstractTableEncoder<C> implements ITableEncoder<C> {

  protected abstract PdfPTable createTable(SheetGraphics graphics, C content, Bounds bounds) throws DocumentException;

  public float encodeTable(SheetGraphics graphics, C content, Bounds bounds) throws DocumentException {
    PdfContentByte directContent = graphics.getDirectContent();
    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable(graphics, content, bounds);
    table.setWidthPercentage(100);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
    return table.getTotalHeight();
  }

  public boolean hasContent(C content) {
    return true;
  }
}
