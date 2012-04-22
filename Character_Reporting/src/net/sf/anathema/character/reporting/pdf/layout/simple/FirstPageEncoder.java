package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ABILITIES_WITH_CRAFTS_AND_SPECIALTIES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ANIMA;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ATTRIBUTES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.COMBAT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ESSENCE_SIMPLE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.GREAT_CURSE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.HEALTH_AND_MOVEMENT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.INTIMACIES_SIMPLE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.MUTATIONS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.NOTES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.PANOPLY;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.PERSONAL_INFO;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.SOCIAL_COMBAT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.VIRTUES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.WILLPOWER_SIMPLE;

public class FirstPageEncoder implements PageEncoder {

  private static final float ANIMA_HEIGHT = 128;
  private static final int ATTRIBUTE_HEIGHT = 128;
  private static final int FIRST_ROW_HEIGHT = 51;
  private static final int VIRTUE_HEIGHT = 72;
  private static final int SOCIAL_COMBAT_HEIGHT = 115;
  private static final int WILLPOWER_HEIGHT = 43;
  private static final int ARMOUR_HEIGHT = 68;
  private static final int HEALTH_HEIGHT = 99;
  private final PageConfiguration configuration;

  public FirstPageEncoder(PageConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    SheetPage page = sheet.startPortraitPage(graphics, session, FIRST_PAGE_CONTENT_HEIGHT);
    LayoutField personalInfo = page.place(PERSONAL_INFO).atStartOf(page).withHeight(FIRST_ROW_HEIGHT).andColumnSpan(2).now();
    LayoutField essence = page.place(ESSENCE_SIMPLE).rightOf(personalInfo).withSameHeight().now();
    LayoutField attributes = page.place(ATTRIBUTES).below(personalInfo).withHeight(ATTRIBUTE_HEIGHT).now();
    page.place(ABILITIES_WITH_CRAFTS_AND_SPECIALTIES).below(attributes).fillToBottomOfPage().now();
    LayoutField anima = page.place(ANIMA, NOTES).below(essence).withHeight(ANIMA_HEIGHT).now();
    LayoutField social = page.place(SOCIAL_COMBAT).below(anima).withHeight(SOCIAL_COMBAT_HEIGHT).now();
    LayoutField virtues = page.place(VIRTUES).rightOf(attributes).withHeight(VIRTUE_HEIGHT).now();
    LayoutField greatCurse = page.place(GREAT_CURSE, MUTATIONS).below(virtues).alignBottomTo(anima).now();
    LayoutField willpower = page.place(WILLPOWER_SIMPLE).below(greatCurse).withHeight(WILLPOWER_HEIGHT).now();
    LayoutField intimacies = page.place(INTIMACIES_SIMPLE, NOTES).below(willpower).alignBottomTo(social).now();
    LayoutField arsenal = page.place(ARSENAL).below(intimacies).withPreferredHeight().andColumnSpan(2).now();
    LayoutField panoply = page.place(PANOPLY).below(arsenal).withHeight(ARMOUR_HEIGHT).andColumnSpan(2).now();
    LayoutField health = page.place(HEALTH_AND_MOVEMENT).below(panoply).withHeight(HEALTH_HEIGHT).andColumnSpan(2).now();
    page.place(COMBAT).below(health).fillToBottomOfPage().andColumnSpan(2).now();
    new CopyrightEncoder(configuration, FIRST_PAGE_CONTENT_HEIGHT).encodeCopyright(graphics);
  }
}
