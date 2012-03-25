package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class GenericCharmEncoder implements IVariableContentEncoder {

  private final GenericCharmTableEncoder tableEncoder;
  private IResources resources;

  public GenericCharmEncoder(IResources resources) {
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
