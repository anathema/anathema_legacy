package net.sf.anathema.hero.charms.sheet.encoder;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.AbstractMagicContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
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
