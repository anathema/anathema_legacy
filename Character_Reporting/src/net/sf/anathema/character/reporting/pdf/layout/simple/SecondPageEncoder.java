package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.AllMagicContent;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.BACKGROUNDS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.CHARMS_AND_SORCERY;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.COMBOS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.EXPERIENCE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.GENERIC_CHARMS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.LANGUAGES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.POSSESSIONS;

public class SecondPageEncoder implements PageEncoder {

  public static final float BACKGROUND_HEIGHT = 104;
  public static final float LANGUAGE_HEIGHT = 60;

  @Override
  public void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    SheetPage page = sheet.startPortraitPage(graphics, session);
    LayoutField backgrounds = page.place(BACKGROUNDS).atStartOf(page).withHeight(BACKGROUND_HEIGHT).now();
    LayoutField possessions = page.place(POSSESSIONS).rightOf(backgrounds).withSameHeight().now();
    LayoutField languages = page.place(LANGUAGES).rightOf(possessions).withHeight(LANGUAGE_HEIGHT).now();
    page.place(EXPERIENCE).below(languages).alignBottomTo(backgrounds).now();
    LayoutField combos = page.place(COMBOS).below(backgrounds).withPreferredHeight().andColumnSpan(3).now();
    LayoutField genericCharms = page.place(GENERIC_CHARMS).below(combos).withPreferredHeight().andColumnSpan(3).now();
    page.place(CHARMS_AND_SORCERY).below(genericCharms).fillToBottomOfPage().andColumnSpan(3).now();
    encodeAdditionalMagicPages(sheet, graphics, session);
  }

  private void encodeAdditionalMagicPages(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    AllMagicContent charmContent = session.createContent(AllMagicContent.class);
    while (charmContent.hasUnprintedCharms()) {
      SheetPage page = sheet.startPortraitPage(graphics, session);
      page.place(CHARMS_AND_SORCERY).atStartOf(page).fillToBottomOfPage().andColumnSpan(3).now();
    }
  }
}
