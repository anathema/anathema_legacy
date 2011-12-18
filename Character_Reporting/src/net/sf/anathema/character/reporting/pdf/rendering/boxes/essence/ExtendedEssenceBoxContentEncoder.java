package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ExtendedEssenceBoxContentEncoder extends AbstractPdfEncoder implements IVariableBoxContentEncoder {

  private BaseFont baseFont;
  private EssenceTableEncoder poolTable;

  public ExtendedEssenceBoxContentEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
    this.poolTable = new EssenceTableEncoder(this.baseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey(ReportContent reportContent) {
    return createEssenceContent(reportContent).getHeader();
  }

  public float getRequestedHeight(ReportContent content, float width) {
    try {
      ExtendedEssenceContent essenceContent = createEssenceContent(content);
      return poolTable.getTableHeight(essenceContent, width);
    }
    catch (DocumentException e) {
      return 100;
    }
  }

  private ExtendedEssenceContent createEssenceContent(ReportContent content) {
    return content.createSubContent(ExtendedEssenceContent.class);
  }

  public void encode(PdfGraphics graphics, ReportContent content) throws DocumentException {
    ExtendedEssenceContent essenceContent = createEssenceContent(content);
    poolTable.encodeTable(graphics.getDirectContent(), essenceContent, graphics.getBounds());
  }

  public boolean hasContent(ReportContent content) {
    return createEssenceContent(content).hasContent();
  }
}
