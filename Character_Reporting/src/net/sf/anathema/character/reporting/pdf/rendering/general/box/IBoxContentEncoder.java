package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface IBoxContentEncoder {

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException;

  public boolean hasContent(ReportContent content);

  public String getHeaderKey(ReportContent content);
}
