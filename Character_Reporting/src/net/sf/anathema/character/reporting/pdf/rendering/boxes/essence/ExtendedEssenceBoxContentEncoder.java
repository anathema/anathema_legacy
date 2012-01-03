package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class ExtendedEssenceBoxContentEncoder implements IVariableContentEncoder {

  private ExtendedEssenceTableEncoder poolTable = new ExtendedEssenceTableEncoder();

  public String getHeaderKey(ReportContent content) {
    return createEssenceContent(content).getHeaderKey();
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportContent content, float width) {
    try {
      ExtendedEssenceContent essenceContent = createEssenceContent(content);
      return poolTable.getTableHeight(essenceContent, width);
    } catch (DocumentException e) {
      return 100;
    }
  }

  private ExtendedEssenceContent createEssenceContent(ReportContent content) {
    return content.createSubContent(ExtendedEssenceContent.class);
  }

  public void encode(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    ExtendedEssenceContent essenceContent = createEssenceContent(content);
    poolTable.encodeTable(graphics, essenceContent, bounds);
  }

  public boolean hasContent(ReportContent content) {
    return createEssenceContent(content).hasContent();
  }
}
