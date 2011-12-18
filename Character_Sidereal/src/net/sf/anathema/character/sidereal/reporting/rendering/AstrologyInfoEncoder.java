package net.sf.anathema.character.sidereal.reporting.rendering;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
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

  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
    new AstrologyTableEncoder(resources, basefont).encodeTable(graphics.getDirectContent(), reportContent, graphics.getBounds());

  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Sidereal.Astrology"; //$NON-NLS-1$
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
