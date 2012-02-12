package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class ExtendedEssenceBoxContentEncoder extends AbstractBoxContentEncoder<ExtendedEssenceContent> implements IVariableContentEncoder {

  private ExtendedEssenceTableEncoder poolTable = new ExtendedEssenceTableEncoder();

  public ExtendedEssenceBoxContentEncoder() {
    super(ExtendedEssenceContent.class);
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportContent content, float width) {
    try {
      ExtendedEssenceContent essenceContent = createContent(content);
      return poolTable.getTableHeight(essenceContent, width);
    } catch (DocumentException e) {
      return 100;
    }
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent content, Bounds bounds) throws DocumentException {
    ExtendedEssenceContent essenceContent = createContent(content);
    poolTable.encodeTable(graphics, essenceContent, bounds);
  }
}
