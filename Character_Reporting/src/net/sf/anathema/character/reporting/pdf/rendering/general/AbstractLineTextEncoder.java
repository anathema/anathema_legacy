package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Line;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

public abstract class AbstractLineTextEncoder extends AbstractPdfEncoder implements IBoxContentEncoder {
  private final static float LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final BaseFont baseFont;

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public AbstractLineTextEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
    Font font = TableEncodingUtilities.createFont(baseFont);
    Phrase phrase = new Phrase();
    addToPhrase(reportContent.getCharacter(), font, phrase);
    Bounds textBounds = new Bounds(graphics.getBounds().x, graphics.getBounds().y, graphics.getBounds().width, graphics.getBounds().height - 2);
    float yPosition = PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), phrase, textBounds, LINE_HEIGHT).getYLine();
    yPosition -= LINE_HEIGHT;
    while (yPosition > graphics.getBounds().y) {
      Line.createHorizontalByCoordinate(new Position(graphics.getBounds().x, yPosition), graphics.getBounds().getMaxX()).encode(graphics
        .getDirectContent());
      yPosition -= LINE_HEIGHT;
    }
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }

  protected abstract void addToPhrase(IGenericCharacter character, Font font, Phrase phrase);
}
