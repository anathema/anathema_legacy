package net.sf.anathema.character.sidereal.reporting.rendering;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class AstrologyInfoEncoder implements IBoxContentEncoder {

  private final IResources resources;
  private final BaseFont basefont;

  public AstrologyInfoEncoder(BaseFont baseFont, IResources resources) {
    this.resources = resources;
    this.basefont = baseFont;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    new AstrologyTableEncoder(resources, basefont).encodeTable(graphics, reportContent, bounds);

  }

  public String getHeaderKey(ReportContent content) {
    return "Sidereal.Astrology"; //$NON-NLS-1$
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
