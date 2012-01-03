package net.sf.anathema.character.abyssal.reporting;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.abyssal.reporting.content.Abyssal1stResonanceContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class AbyssalResonanceEncoder implements ContentEncoder {
  private final VirtueFlawBoxEncoder traitEncoder = new VirtueFlawBoxEncoder();

  public String getHeaderKey(ReportContent content) {
    return createContent(content).getHeaderKey();
  }

  public boolean hasContent(ReportContent content) {
    return createContent(content).hasContent();
  }

  private Abyssal1stResonanceContent createContent(ReportContent content) {
    return content.createSubContent(Abyssal1stResonanceContent.class);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Abyssal1stResonanceContent content = createContent(reportContent);
    Bounds textBounds = traitEncoder.encode(graphics, bounds, 0);
    Phrase phrase = new Phrase("", graphics.createTableFont()); //$NON-NLS-1$
    phrase.add(graphics.createSymbolChunk());
    phrase.add(content.getSocialPool() + "\n"); //$NON-NLS-1$
    phrase.add(graphics.createSymbolChunk());
    phrase.add(content.getVirtueDifficultyLabel());
    graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }
}
