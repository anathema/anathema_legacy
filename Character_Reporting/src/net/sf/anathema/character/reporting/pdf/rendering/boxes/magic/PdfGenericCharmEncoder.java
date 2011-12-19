package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class PdfGenericCharmEncoder implements IVariableBoxContentEncoder {

  private final PdfGenericCharmTableEncoder tableEncoder;

  public PdfGenericCharmEncoder(IResources resources, BaseFont baseFont) {
    this.tableEncoder = new PdfGenericCharmTableEncoder(resources, baseFont);
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "GenericCharms"; //$NON-NLS-1$
  }

  @Override
  public float getRequestedHeight(ReportContent content, float width) {
    return tableEncoder.getRequestedHeight(content);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    tableEncoder.encodeTable(graphics.getDirectContent(), reportContent, bounds);
  }

  public boolean hasContent(ReportContent content) {
    return tableEncoder.hasContent(content);
  }
}
