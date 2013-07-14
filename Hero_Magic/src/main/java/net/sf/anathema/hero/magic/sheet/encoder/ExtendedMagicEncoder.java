package net.sf.anathema.hero.magic.sheet.encoder;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.magic.sheet.content.AbstractMagicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

public class ExtendedMagicEncoder<C extends AbstractMagicContent> implements ContentEncoder {
  private Resources resources;

  private final MagicTableEncoder tableEncoder;
  private final String headerKey;

  public ExtendedMagicEncoder(Resources resources, Class<C> contentClass, boolean sectionHeaderLines, String headerKey) {
    this.resources = resources;
    this.tableEncoder = new MagicTableEncoder(sectionHeaderLines, contentClass);
    this.headerKey = headerKey;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    float top = bounds.getMinY();
    Bounds remainingBounds = new Bounds(bounds.getMinX(), top, bounds.getWidth(), bounds.getMaxY() - top);
    tableEncoder.encodeTable(graphics, reportSession, remainingBounds);
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header." + headerKey);
  }
}
