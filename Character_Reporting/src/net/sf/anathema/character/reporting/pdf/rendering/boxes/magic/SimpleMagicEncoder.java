package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.AllMagicContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class SimpleMagicEncoder extends AbstractContentEncoder<AllMagicContent> {

  public SimpleMagicEncoder() {
    super(AllMagicContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    float top = bounds.getMinY();
    Bounds remainingBounds = new Bounds(bounds.getMinX(), top, bounds.getWidth(), bounds.getMaxY() - top);
    MagicTableEncoder tableEncoder = new MagicTableEncoder(false, AllMagicContent.class);
    tableEncoder.encodeTable(graphics, reportSession, remainingBounds);
  }
}
