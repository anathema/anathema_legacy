package net.sf.anathema.character.lunar.reporting.rendering.knacks;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.lunar.reporting.content.knacks.KnackContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class KnackEncoder extends AbstractBoxContentEncoder<KnackContent> {

  public KnackEncoder() {
    super(KnackContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    KnackContent knackContent = createContent(reportContent);
    KnackTableEncoder encoder = new KnackTableEncoder();
    encoder.encodeTable(graphics, knackContent, bounds);
  }
}
