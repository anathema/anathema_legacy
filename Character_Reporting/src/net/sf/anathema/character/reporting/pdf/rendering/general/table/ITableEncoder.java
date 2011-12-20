package net.sf.anathema.character.reporting.pdf.rendering.general.table;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;

public interface ITableEncoder<C> {

  float encodeTable(SheetGraphics graphics, C content, Bounds bounds) throws DocumentException;

  boolean hasContent(C content);
}
