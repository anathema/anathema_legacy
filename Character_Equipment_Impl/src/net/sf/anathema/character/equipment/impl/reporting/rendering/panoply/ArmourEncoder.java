package net.sf.anathema.character.equipment.impl.reporting.rendering.panoply;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class ArmourEncoder implements ContentEncoder {

  private IResources resources;
  private final ITableEncoder encoder;

  public ArmourEncoder(IResources resources, ITableEncoder encoder) {
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
