package net.sf.anathema.character.lunar.reporting.rendering.knacks;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.lunar.reporting.content.knacks.KnackContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class KnackEncoder implements ContentEncoder {

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
    KnackTableEncoder encoder = new KnackTableEncoder();
    encoder.encodeTable(graphics, knackContent, bounds);
  }

  @Override
  public boolean hasContent(ReportContent content) {
    KnackContent knackContent = getKnackContent(content);
    return knackContent.hasContent();
  }
}
