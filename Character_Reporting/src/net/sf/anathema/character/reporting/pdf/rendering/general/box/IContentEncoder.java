package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;

public interface IContentEncoder {

  public float encode(SheetGraphics graphics, ReportContent content, Bounds bounds);
}
