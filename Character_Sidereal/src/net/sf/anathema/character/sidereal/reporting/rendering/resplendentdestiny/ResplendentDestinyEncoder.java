package net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class ResplendentDestinyEncoder implements ContentEncoder {

  private IResources resources;
  private float lineHeight;

  public ResplendentDestinyEncoder(int fontSize, IResources resources) {
    this.resources = resources;
    this.lineHeight = fontSize * 1.5f;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    int yPosition = (int) (bounds.getMaxY() - lineHeight);
    graphics.drawLabelledContent(getLabel("Label.College"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    graphics.drawLabelledContent(getLabel("Label.Name"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    graphics.drawLabelledContent(getLabel("Label.EffectDice"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    graphics.drawLabelledContent(getLabel("Label.Duration"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    graphics.drawLabelledContent(getLabel("Label.Effects"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    encodeLines(graphics, bounds, new Position(bounds.x, yPosition), 4);
    yPosition -= 4 * lineHeight;
    graphics.drawLabelledContent(getLabel("Label.Trappings"), null, new Position(bounds.x, yPosition), bounds.width); //$NON-NLS-1$
    yPosition -= lineHeight;
    encodeLines(graphics, bounds, new Position(bounds.x, yPosition), 5);
  }

  private void encodeLines(SheetGraphics graphics, Bounds bounds, Position lineStartPosition, int count) {
    float minX = bounds.getMinX();
    float maxX = bounds.getMaxX();
    new HorizontalLineEncoder().encodeLines(graphics, lineStartPosition, minX, maxX, lineHeight, count);
  }

  protected final String getLabel(String key) {
    return resources.getString("Sheet.ResplendentDestiny." + key) + ":"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Sidereal.ResplendentDestiny");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
