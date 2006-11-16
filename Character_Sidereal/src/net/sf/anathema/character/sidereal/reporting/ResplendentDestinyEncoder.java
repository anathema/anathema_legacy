package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfLineEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class ResplendentDestinyEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private IResources resources;
  private BaseFont basefont;
  private float lineHeight;

  public ResplendentDestinyEncoder(BaseFont baseFont, int fontSize, IResources resources) {
    this.resources = resources;
    this.basefont = baseFont;
    this.lineHeight = fontSize * 1.5f;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    int yPosition = (int) (bounds.getMaxY() - lineHeight);
    drawLabelledContent(directContent, getLabel("Label.College"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    drawLabelledContent(directContent, getLabel("Label.Name"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    drawLabelledContent(
        directContent,
        getLabel("Label.EffectDice"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    drawLabelledContent(
        directContent,
        getLabel("Label.Duration"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    drawLabelledContent(directContent, getLabel("Label.Effects"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    encodeLines(directContent, bounds, new Position(bounds.x, yPosition), 4);
    yPosition -= 4 * lineHeight;
    drawLabelledContent(
        directContent,
        getLabel("Label.Trappings"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    encodeLines(directContent, bounds, new Position(bounds.x, yPosition), 5);
  }

  private void encodeLines(PdfContentByte directContent, Bounds bounds, Position lineStartPosition, int count) {
    float minX = bounds.getMinX();
    float maxX = bounds.getMaxX();
    PdfLineEncodingUtilities.encodeHorizontalLines(directContent, lineStartPosition, minX, maxX, lineHeight, count);
  }

  public String getHeaderKey() {
    return "Sidereal.ResplendentDestiny"; //$NON-NLS-1$  
  }

  @Override
  protected BaseFont getBaseFont() {
    return basefont;
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.ResplendentDestiny." + key) + ":"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}