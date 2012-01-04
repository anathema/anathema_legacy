package net.sf.anathema.character.reporting.pdf.rendering.boxes.experience;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.experience.ExperienceContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.FONT_SIZE;

public class ExperienceBoxContentEncoder extends AbstractBoxContentEncoder<ExperienceContent> {

  public ExperienceBoxContentEncoder() {
    super(ExperienceContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    ExperienceContent content = createContent(reportContent);
    Phrase phrase = new Phrase(content.getExperienceText(), graphics.createTextFont());
    graphics.createSimpleColumn(bounds).withLeading((float) (FONT_SIZE + 4)).andTextPart(phrase).encode();
  }
}
