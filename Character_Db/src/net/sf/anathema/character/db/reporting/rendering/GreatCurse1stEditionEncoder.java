package net.sf.anathema.character.db.reporting.rendering;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.db.reporting.content.Db1stEditionGreatCurseContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class GreatCurse1stEditionEncoder extends AbstractBoxContentEncoder<Db1stEditionGreatCurseContent> {

  public GreatCurse1stEditionEncoder() {
    super(Db1stEditionGreatCurseContent.class);
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Db1stEditionGreatCurseContent content = createContent(reportContent);
    Phrase phrase = new Phrase(content.getGreatCurseMessage(), graphics.createTableFont());
    graphics.createSimpleColumn(bounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }
}
