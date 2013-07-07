package net.sf.anathema.character.equipment.impl.reporting.rendering.panoply;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.table.ITableEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.Resources;

public class ArmourEncoder implements ContentEncoder {

  private Resources resources;
  private final ITableEncoder encoder;

  public ArmourEncoder(Resources resources, ITableEncoder encoder) {
    this.resources = resources;
    this.encoder = encoder;
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.ArmourSoak");
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    encoder.encodeTable(graphics, session, bounds);
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
