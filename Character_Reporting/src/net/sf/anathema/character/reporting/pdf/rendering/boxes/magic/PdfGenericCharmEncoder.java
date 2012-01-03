package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class PdfGenericCharmEncoder implements IVariableBoxContentEncoder {

  private final PdfGenericCharmTableEncoder tableEncoder;

  public PdfGenericCharmEncoder(IResources resources) {
    this.tableEncoder = new PdfGenericCharmTableEncoder(resources);
  }

  public String getHeaderKey(ReportContent content) {
    return "GenericCharms"; //$NON-NLS-1$
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportContent content, float width) {
    return tableEncoder.getRequestedHeight(graphics, content);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    tableEncoder.encodeTable(graphics, reportContent, bounds);
  }

  public boolean hasContent(ReportContent content) {
    return tableEncoder.hasContent(content);
  }
}
