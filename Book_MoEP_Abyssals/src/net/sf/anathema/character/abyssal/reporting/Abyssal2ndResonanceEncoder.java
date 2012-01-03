package net.sf.anathema.character.abyssal.reporting;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.abyssal.reporting.content.Abyssal2ndResonanceContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class Abyssal2ndResonanceEncoder implements ContentEncoder {

  private final VirtueFlawBoxEncoder traitEncoder = new VirtueFlawBoxEncoder();

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Abyssal2ndResonanceContent content = createContent(reportContent);
    Bounds textBounds = traitEncoder.encode(graphics, bounds, content.getLimitValue());
    Phrase phrase = new Phrase("", createDefaultFont(graphics)); //$NON-NLS-1$
    phrase.add(new Chunk(content.getFlawedVirtueLabel(), createNameFont(graphics)));
    if (content.isComplete()) {
      phrase.add(new Chunk(content.getFlawedVirtue()));
      phrase.add(".\n");  //$NON-NLS-1$
    }
    else {
      phrase.add(new Chunk("                                          ", createUndefinedFont(graphics))); //$NON-NLS-1$
      phrase.add(".\n");
    }
    phrase.add(content.getResonanceReference());
    graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }

  private Font createDefaultFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  private Font createNameFont(SheetGraphics graphics) {
    Font newFont = createDefaultFont(graphics);
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createUndefinedFont(SheetGraphics graphics) {
    Font newFont = createDefaultFont(graphics);
    newFont.setStyle(Font.UNDERLINE);
    return newFont;
  }

  private Abyssal2ndResonanceContent createContent(ReportContent content) {
    return content.createSubContent(Abyssal2ndResonanceContent.class);
  }

  public String getHeaderKey(ReportContent content) {
    return createContent(content).getHeaderKey();
  }

  public boolean hasContent(ReportContent content) {
    return createContent(content).hasContent();
  }
}
