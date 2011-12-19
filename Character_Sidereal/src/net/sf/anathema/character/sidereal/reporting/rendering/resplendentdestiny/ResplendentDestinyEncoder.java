package net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfLineEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class ResplendentDestinyEncoder extends AbstractPdfEncoder implements IBoxContentEncoder {

  private IResources resources;
  private BaseFont basefont;
  private float lineHeight;

  public ResplendentDestinyEncoder(BaseFont baseFont, int fontSize, IResources resources) {
    this.resources = resources;
    this.basefont = baseFont;
    this.lineHeight = fontSize * 1.5f;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    int yPosition = (int) (bounds.getMaxY() - lineHeight);
    drawLabelledContent(graphics.getDirectContent(), getLabel("Label.College"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    drawLabelledContent(graphics.getDirectContent(), getLabel("Label.Name"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    drawLabelledContent(graphics.getDirectContent(),
        getLabel("Label.EffectDice"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    drawLabelledContent(graphics.getDirectContent(),
        getLabel("Label.Duration"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    drawLabelledContent(graphics.getDirectContent(), getLabel("Label.Effects"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    encodeLines(graphics.getDirectContent(), bounds, new Position(bounds.x, yPosition), 4);
    yPosition -= 4 * lineHeight;
    drawLabelledContent(graphics.getDirectContent(),
        getLabel("Label.Trappings"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    encodeLines(graphics.getDirectContent(), bounds, new Position(bounds.x, yPosition), 5);
  }

  private void encodeLines(PdfContentByte directContent, Bounds bounds, Position lineStartPosition, int count) {
    float minX = bounds.getMinX();
    float maxX = bounds.getMaxX();
    PdfLineEncodingUtilities.encodeHorizontalLines(directContent, lineStartPosition, minX, maxX, lineHeight, count);
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Sidereal.ResplendentDestiny"; //$NON-NLS-1$  
  }

  @Override
  protected BaseFont getBaseFont() {
    return basefont;
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.ResplendentDestiny." + key) + ":"; //$NON-NLS-1$ //$NON-NLS-2$
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
