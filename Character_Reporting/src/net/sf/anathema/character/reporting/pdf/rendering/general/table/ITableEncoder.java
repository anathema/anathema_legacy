package net.sf.anathema.character.reporting.pdf.rendering.general.table;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface ITableEncoder<C> {

  float encodeTable(SheetGraphics graphics, C content, Bounds bounds) throws DocumentException;

  boolean hasContent(C content);
}
