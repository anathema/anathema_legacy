package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

import java.util.Iterator;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class LineFillingContentEncoder<C extends ListSubBoxContent> extends AbstractContentEncoder<C> {

  protected LineFillingContentEncoder(Class<C> contentClass) {
    super(contentClass);
  }

  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    Phrase phrase = new Phrase();
    addToPhrase(createContent(reportSession), createDefaultFont(graphics), phrase);
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - 2);
    float yPosition = graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode().getYLine();
    yPosition -= REDUCED_LINE_HEIGHT;
    while (yPosition > bounds.y) {
      graphics.createHorizontalLineByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode();
      yPosition -= REDUCED_LINE_HEIGHT;
    }
  }

  private Font createDefaultFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  private final void addToPhrase(C content, Font font, Phrase phrase) {
    for (Iterator<String> entry = content.getPrintEntries().iterator(); entry.hasNext(); ) {
      String text = entry.next();
      text += entry.hasNext() ? ", " : ""; //$NON-NLS-1$ //$NON-NLS-2$
      phrase.add(new Chunk(text, font));
    }
  }
}
