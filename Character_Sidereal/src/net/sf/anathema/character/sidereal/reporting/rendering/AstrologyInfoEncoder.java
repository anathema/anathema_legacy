package net.sf.anathema.character.sidereal.reporting.rendering;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.Resources;

public class AstrologyInfoEncoder implements ContentEncoder {

  private final Resources resources;

  public AstrologyInfoEncoder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    new AstrologyTableEncoder(resources).encodeTable(graphics, reportSession, bounds);

  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Sidereal.Astrology");
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
