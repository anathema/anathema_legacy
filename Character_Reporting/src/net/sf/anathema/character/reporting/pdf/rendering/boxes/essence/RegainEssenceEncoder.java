package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.essence.RegainEssenceContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class RegainEssenceEncoder extends AbstractContentEncoder<RegainEssenceContent> {

  private RegainEssenceTableEncoder poolTable = new RegainEssenceTableEncoder();

  public RegainEssenceEncoder() {
    super(RegainEssenceContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    RegainEssenceContent essenceContent = createContent(session);
    poolTable.encodeTable(graphics, essenceContent, bounds);
  }
}
