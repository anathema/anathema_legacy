package net.sf.anathema.character.lunar.reporting.rendering.knacks;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.lunar.reporting.content.knacks.KnackContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;

public class KnackEncoder implements IBoxContentEncoder {

  private final BaseFont baseFont;

  public KnackEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
  }

  private KnackContent getKnackContent(ReportContent reportContent) {
    return reportContent.createSubContent(KnackContent.class);
  }

  @Override
  public String getHeaderKey(ReportContent content) {
    KnackContent knackContent = getKnackContent(content);
    return knackContent.getHeaderKey();
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    KnackContent knackContent = getKnackContent(reportContent);
    KnackTableEncoder encoder = new KnackTableEncoder(baseFont);
    PdfContentByte directContent = graphics.getDirectContent();
    encoder.encodeTable(graphics, knackContent, bounds);
  }

  @Override
  public boolean hasContent(ReportContent content) {
    KnackContent knackContent = getKnackContent(content);
    return knackContent.hasContent();
  }
}
