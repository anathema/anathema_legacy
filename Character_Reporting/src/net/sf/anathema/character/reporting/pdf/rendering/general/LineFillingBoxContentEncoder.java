package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

public abstract class LineFillingBoxContentEncoder implements IBoxContentEncoder {
  private final static float REDUCED_LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Font font = TableEncodingUtilities.createFont(graphics.getBaseFont());
    Phrase phrase = new Phrase();
    addToPhrase(reportContent.getCharacter(), font, phrase);
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - 2);
    float yPosition = graphics.encodeText(phrase, textBounds, REDUCED_LINE_HEIGHT).getYLine();
    yPosition -= REDUCED_LINE_HEIGHT;
    while (yPosition > bounds.y) {
      graphics.createHorizontalLineByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode();
      yPosition -= REDUCED_LINE_HEIGHT;
    }
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }

  protected abstract void addToPhrase(IGenericCharacter character, Font font, Phrase phrase);
}
