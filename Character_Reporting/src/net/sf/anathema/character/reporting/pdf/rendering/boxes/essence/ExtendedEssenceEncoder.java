package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.essence.ExtendedEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class ExtendedEssenceEncoder extends AbstractContentEncoder<ExtendedEssenceContent> implements IVariableContentEncoder {

  private ExtendedEssenceTableEncoder poolTable = new ExtendedEssenceTableEncoder();

  public ExtendedEssenceEncoder() {
    super(ExtendedEssenceContent.class);
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportSession session, float width) {
    try {
      ExtendedEssenceContent essenceContent = createContent(session);
      return poolTable.getTableHeight(essenceContent, width);
    } catch (DocumentException e) {
      return 100;
    }
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    ExtendedEssenceContent essenceContent = createContent(session);
    poolTable.encodeTable(graphics, essenceContent, bounds);
  }
}
