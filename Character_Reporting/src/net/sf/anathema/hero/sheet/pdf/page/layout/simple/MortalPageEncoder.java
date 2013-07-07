package net.sf.anathema.hero.sheet.pdf.page.layout.simple;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.page.layout.Sheet;
import net.sf.anathema.hero.sheet.pdf.page.layout.SheetPage;
import net.sf.anathema.hero.sheet.pdf.page.layout.field.LayoutField;
import net.sf.anathema.hero.sheet.pdf.encoder.general.CopyrightEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.page.PageConfiguration;
import net.sf.anathema.hero.sheet.pdf.page.PageEncoder;

import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.ABILITIES_WITH_SPECIALTIES;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.ARSENAL;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.ATTRIBUTES;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.BACKGROUNDS;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.COMBAT;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.EXPERIENCE;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.HEALTH_AND_MOVEMENT;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.INTIMACIES_SIMPLE;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.LANGUAGES;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.NOTES;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.PANOPLY;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.PERSONAL_INFO;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.SOCIAL_COMBAT;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.VIRTUES;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.WILLPOWER_SIMPLE;

public class MortalPageEncoder implements PageEncoder {

  private static final float ANIMA_HEIGHT = 128;
  private static final int ATTRIBUTE_HEIGHT = 128;
  private static final int FIRST_ROW_HEIGHT = 51;
  private static final int VIRTUE_HEIGHT = 72;
  private static final int SOCIAL_COMBAT_HEIGHT = 115;
  private static final int WILLPOWER_HEIGHT = 43;
  private static final int ARMOUR_HEIGHT = 80;
  private static final int HEALTH_HEIGHT = 99;
  private final PageConfiguration configuration;

  public MortalPageEncoder(PageConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    SheetPage page = sheet.startPortraitPage(graphics, session, FIRST_PAGE_CONTENT_HEIGHT);
    LayoutField personalInfo = page.place(PERSONAL_INFO).atStartOf(page).withHeight(FIRST_ROW_HEIGHT).andColumnSpan(2).now();
    LayoutField experience = page.place(EXPERIENCE).rightOf(personalInfo).withSameHeight().now();
    LayoutField attributes = page.place(ATTRIBUTES).below(personalInfo).withHeight(ATTRIBUTE_HEIGHT).now();
    page.place(ABILITIES_WITH_SPECIALTIES).below(attributes).fillToBottomOfPage().now();
    LayoutField backgrounds = page.place(BACKGROUNDS).below(experience).withHeight(ANIMA_HEIGHT).now();
    LayoutField social = page.place(SOCIAL_COMBAT).below(backgrounds).withHeight(SOCIAL_COMBAT_HEIGHT).now();
    LayoutField virtues = page.place(VIRTUES).rightOf(attributes).withHeight(VIRTUE_HEIGHT).now();
    LayoutField languages = page.place(LANGUAGES).below(virtues).alignBottomTo(backgrounds).now();
    LayoutField willpower = page.place(WILLPOWER_SIMPLE).below(languages).withHeight(WILLPOWER_HEIGHT).now();
    LayoutField intimacies = page.place(INTIMACIES_SIMPLE, NOTES).below(willpower).alignBottomTo(social).now();
    LayoutField arsenal = page.place(ARSENAL).below(intimacies).withPreferredHeight().andColumnSpan(2).now();
    LayoutField panoply = page.place(PANOPLY).below(arsenal).withHeight(ARMOUR_HEIGHT).andColumnSpan(2).now();
    LayoutField health = page.place(HEALTH_AND_MOVEMENT).below(panoply).withHeight(HEALTH_HEIGHT).andColumnSpan(2).now();
    page.place(COMBAT).below(health).fillToBottomOfPage().andColumnSpan(2).now();
    new CopyrightEncoder(configuration, FIRST_PAGE_CONTENT_HEIGHT).encodeCopyright(graphics);
  }
}
