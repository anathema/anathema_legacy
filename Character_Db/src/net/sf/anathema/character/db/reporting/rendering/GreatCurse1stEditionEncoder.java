package net.sf.anathema.character.db.reporting.rendering;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.db.reporting.content.Db1stEditionGreatCurseContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class GreatCurse1stEditionEncoder implements ContentEncoder {

  private final IResources resources;

  public GreatCurse1stEditionEncoder(IResources resources) {
    this.resources = resources;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Db1stEditionGreatCurseContent content = createContent(reportContent);
    Phrase phrase = new Phrase(content.getGreatCurseMessage(), graphics.createTableFont());
    graphics.createSimpleColumn(bounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }

  public String getHeaderKey(ReportContent content) {
    return createContent(content).getHeaderKey();
  }

  public boolean hasContent(ReportContent content) {
    return createContent(content).hasContent();
  }

  private Db1stEditionGreatCurseContent createContent(ReportContent content) {
    return content.createSubContent(Db1stEditionGreatCurseContent.class);
  }
}
