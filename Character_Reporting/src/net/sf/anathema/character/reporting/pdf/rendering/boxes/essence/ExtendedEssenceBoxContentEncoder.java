package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;

public class ExtendedEssenceBoxContentEncoder implements IVariableBoxContentEncoder {

  private BaseFont baseFont;
  private ExtendedEssenceTableEncoder poolTable;

  public ExtendedEssenceBoxContentEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
    this.poolTable = new ExtendedEssenceTableEncoder(this.baseFont);
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

  public void encode(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    ExtendedEssenceContent essenceContent = createEssenceContent(content);
    poolTable.encodeTable(graphics.getDirectContent(), essenceContent, bounds);
  }

  public boolean hasContent(ReportContent content) {
    return createEssenceContent(content).hasContent();
  }
}
