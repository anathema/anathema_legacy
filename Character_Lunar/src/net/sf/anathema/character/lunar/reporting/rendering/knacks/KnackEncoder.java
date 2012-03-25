package net.sf.anathema.character.lunar.reporting.rendering.knacks;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.lunar.reporting.content.knacks.KnackContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class KnackEncoder extends AbstractContentEncoder<KnackContent> {

  public KnackEncoder() {
    super(KnackContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    KnackContent knackContent = createContent(reportSession);
    KnackTableEncoder encoder = new KnackTableEncoder();
    encoder.encodeTable(graphics, knackContent, bounds);
  }
}
