package net.sf.anathema.character.sidereal.reporting.rendering;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class AstrologyInfoEncoder implements ContentEncoder {

  private final IResources resources;

  public AstrologyInfoEncoder(IResources resources) {
    this.resources = resources;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    new AstrologyTableEncoder(resources).encodeTable(graphics, reportContent, bounds);

  }

  public String getHeaderKey(ReportContent content) {
    return "Sidereal.Astrology"; //$NON-NLS-1$
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
