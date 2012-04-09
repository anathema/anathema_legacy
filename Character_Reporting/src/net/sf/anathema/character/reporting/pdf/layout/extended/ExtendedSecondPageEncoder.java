package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.COMBAT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.HEALTH;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.MOVEMENT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.PANOPLY;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.POSSESSIONS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.SOCIAL_COMBAT;

public class ExtendedSecondPageEncoder implements PageEncoder {

  private PageConfiguration pageConfiguration;

  public ExtendedSecondPageEncoder(PageConfiguration pageConfiguration) {
    this.pageConfiguration = pageConfiguration;
  }

  public void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    SheetPage page = sheet.startPortraitPage(graphics, session);
    LayoutField health = page.place(HEALTH).atStartOf(page).withHeight(175).now();
    LayoutField arsenal = page.place(ARSENAL).rightOf(health).withSameHeight().andColumnSpan(2).now();
    LayoutField movement = page.place(MOVEMENT).below(health).withHeight(76).now();
    LayoutField panoply = page.place(PANOPLY).rightOf(movement).withSameHeight().andColumnSpan(2).now();
    LayoutField socialCombat = page.place(SOCIAL_COMBAT).below(movement).withHeight(125).now();
    LayoutField combat = page.place(COMBAT).rightOf(socialCombat).withSameHeight().andColumnSpan(2).now();
    LayoutField possessions = page.place(POSSESSIONS).below(socialCombat).fillToBottomOfPage().andColumnSpan(3).now();
    new CopyrightEncoder(pageConfiguration).encodeCopyright(graphics);
  }
}
