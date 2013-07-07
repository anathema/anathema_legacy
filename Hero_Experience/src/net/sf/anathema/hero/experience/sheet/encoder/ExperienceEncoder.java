package net.sf.anathema.hero.experience.sheet.encoder;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.experience.sheet.content.ExperienceContent;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.FONT_SIZE;

public class ExperienceEncoder extends AbstractContentEncoder<ExperienceContent> {

  public ExperienceEncoder() {
    super(ExperienceContent.class);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    ExperienceContent content = createContent(reportSession);
    Phrase phrase = new Phrase(content.getExperienceText(), graphics.createTextFont());
    graphics.createSimpleColumn(bounds).withLeading((float) (FONT_SIZE + 4)).andTextPart(phrase).encode();
  }
}
