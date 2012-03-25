package net.sf.anathema.character.reporting.pdf.rendering.boxes.experience;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.experience.ExperienceContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.FONT_SIZE;

public class ExperienceContentEncoder extends AbstractContentEncoder<ExperienceContent> {

  public ExperienceContentEncoder() {
    super(ExperienceContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    ExperienceContent content = createContent(reportSession);
    Phrase phrase = new Phrase(content.getExperienceText(), graphics.createTextFont());
    graphics.createSimpleColumn(bounds).withLeading((float) (FONT_SIZE + 4)).andTextPart(phrase).encode();
  }
}
