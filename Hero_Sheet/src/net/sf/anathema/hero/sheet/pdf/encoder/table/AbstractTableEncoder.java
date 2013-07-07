package net.sf.anathema.hero.sheet.pdf.encoder.table;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

public abstract class AbstractTableEncoder<C> implements ITableEncoder<C> {

  protected abstract PdfPTable createTable(SheetGraphics graphics, C content, Bounds bounds) throws DocumentException;

  @Override
  public float encodeTable(SheetGraphics graphics, C content, Bounds bounds) throws DocumentException {
    PdfPTable table = createTable(graphics, content, bounds);
    table.setWidthPercentage(100);
    graphics.createSimpleColumn(bounds).withElement(table).encode();
    return table.getTotalHeight();
  }

  @Override
  public boolean hasContent(C content) {
    return true;
  }
}
