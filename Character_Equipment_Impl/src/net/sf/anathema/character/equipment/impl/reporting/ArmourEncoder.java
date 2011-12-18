package net.sf.anathema.character.equipment.impl.reporting;

import java.awt.Color;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class ArmourEncoder implements IBoxContentEncoder {

  private final BaseFont baseFont;
  private final IResources resources;
  private final IPdfTableEncoder encoder;

  public ArmourEncoder(IResources resources, BaseFont baseFont, IPdfTableEncoder encoder) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.encoder = encoder;
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "ArmourSoak"; //$NON-NLS-1$
  }

  public void encode(PdfGraphics graphics, ReportContent content) throws DocumentException {
    float tableHeight = encoder.encodeTable(graphics.getDirectContent(), content, graphics.getBounds());
    float remainingHeight = graphics.getBounds().getHeight() - tableHeight;
    float delimitingLineYPosition = graphics.getBounds().getMinY() + remainingHeight - 3;
    drawDelimiter(graphics.getDirectContent(), graphics.getBounds(), delimitingLineYPosition);
    Bounds shieldBounds = new Bounds(graphics.getBounds().getMinX(), graphics.getBounds().getMinY(), graphics.getBounds().getWidth(),
        remainingHeight - 6);
    new ShieldTableEncoder(baseFont, resources).encodeTable(graphics.getDirectContent(), content, shieldBounds);
  }

  private void drawDelimiter(PdfContentByte directContent, Bounds bounds, float delimitingLineYPosition) {
    directContent.moveTo(bounds.getMinX() + 3, delimitingLineYPosition);
    directContent.lineTo(bounds.getMaxX() - 3, delimitingLineYPosition);
    directContent.setColorStroke(Color.GRAY);
    directContent.setLineWidth(0.75f);
    directContent.stroke();
    directContent.setColorStroke(Color.BLACK);
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
