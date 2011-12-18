package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;

public interface IBoxContentEncoder {

  public void encode(PdfGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException;

  public boolean hasContent(ReportContent content);

  public String getHeaderKey(ReportContent reportContent);
}
