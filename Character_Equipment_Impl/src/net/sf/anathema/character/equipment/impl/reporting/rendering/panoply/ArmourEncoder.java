package net.sf.anathema.character.equipment.impl.reporting.rendering.panoply;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
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

  public void encode(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    float tableHeight = encoder.encodeTable(graphics, session, bounds);
    float remainingHeight = bounds.getHeight() - tableHeight;
    int padding = 1;
    float delimitingLineYPosition = bounds.getMinY() + remainingHeight - padding;
    drawDelimiter(graphics.getDirectContent(), bounds, delimitingLineYPosition);
    Bounds shieldBounds = new Bounds(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), remainingHeight - 2 * padding);
    new ShieldTableEncoder().encodeTable(graphics, session, shieldBounds);
  }

  private void drawDelimiter(PdfContentByte directContent, Bounds bounds, float delimitingLineYPosition) {
    directContent.moveTo(bounds.getMinX() + 3, delimitingLineYPosition);
    directContent.lineTo(bounds.getMaxX() - 3, delimitingLineYPosition);
    directContent.setColorStroke(BaseColor.GRAY);
    directContent.setLineWidth(0.75f);
    directContent.stroke();
    directContent.setColorStroke(BaseColor.BLACK);
  }

  public boolean hasContent(ReportSession session) {
    return true;
  }
}
