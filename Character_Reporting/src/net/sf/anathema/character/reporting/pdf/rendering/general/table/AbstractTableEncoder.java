package net.sf.anathema.character.reporting.pdf.rendering.general.table;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;

public abstract class AbstractTableEncoder<C> implements ITableEncoder<C> {

  protected abstract PdfPTable createTable(PdfContentByte directContent, C content, Bounds bounds) throws DocumentException;

  public float encodeTable(PdfContentByte directContent, C content, Bounds bounds) throws DocumentException {
    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable(directContent, content, bounds);
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
