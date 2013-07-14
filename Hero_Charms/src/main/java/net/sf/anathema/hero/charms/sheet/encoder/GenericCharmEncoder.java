package net.sf.anathema.hero.charms.sheet.encoder;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.IVariableContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

public class GenericCharmEncoder implements IVariableContentEncoder {

  private final GenericCharmTableEncoder tableEncoder;
  private Resources resources;

  public GenericCharmEncoder(Resources resources) {
    this.resources = resources;
    this.tableEncoder = new GenericCharmTableEncoder(resources);
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.GenericCharms");
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportSession session, float width) {
    return tableEncoder.getRequestedHeight(graphics, width, session);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    tableEncoder.encodeTable(graphics, reportSession, bounds);
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return tableEncoder.hasContent(session);
  }
}
