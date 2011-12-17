package net.sf.anathema.character.reporting.common.encoder;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.common.Bounds;

public abstract class AbstractTableEncoder implements IPdfTableEncoder {

  protected abstract PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException;

  public float encodeTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException {
    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable(directContent, character, bounds);
    table.setWidthPercentage(100);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
    return table.getTotalHeight();
  }
  
  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}
