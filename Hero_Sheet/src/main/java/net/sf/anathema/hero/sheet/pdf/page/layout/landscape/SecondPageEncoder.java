package net.sf.anathema.hero.sheet.pdf.page.layout.landscape;

import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncoderRegistry;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.page.PageEncoder;
import net.sf.anathema.hero.sheet.pdf.page.layout.Sheet;
import net.sf.anathema.hero.sheet.pdf.page.layout.SheetPage;
import net.sf.anathema.hero.sheet.pdf.page.layout.field.LayoutField;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.ARSENAL;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.CHARMS_AND_SORCERY;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.COMBAT;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.ESSENCE_SIMPLE;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.GENERIC_CHARMS;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.HEALTH_AND_MOVEMENT;
import static net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds.PANOPLY;

public class SecondPageEncoder implements PageEncoder {

  private static final int COMBAT_HEIGHT = 125;
  private static final int ARMOUR_HEIGHT = 68;
  private static final int HEALTH_HEIGHT = 110;
  private EncoderRegistry encoders;
  private Resources resources;

  public SecondPageEncoder(EncoderRegistry encoders, Resources resources) {
    this.encoders = encoders;
    this.resources = resources;
  }

  @Override
  public void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    SheetPage page = sheet.startLandscapePage(graphics, session);
    LayoutField genericCharms = page.place(GENERIC_CHARMS).atStartOf(page).withPreferredHeight().andColumnSpan(3).now();
    page.place(CHARMS_AND_SORCERY).below(genericCharms).fillToBottomOfPage().andColumnSpan(3).now();

    LayoutField combat = page.place(COMBAT).rightOf(genericCharms).withHeight(COMBAT_HEIGHT).andColumnSpan(2).now();
    LayoutField health = page.place(HEALTH_AND_MOVEMENT).below(combat).withHeight(HEALTH_HEIGHT).andColumnSpan(2).now();
    LayoutField panoply = page.place(PANOPLY).below(health).withHeight(ARMOUR_HEIGHT).andColumnSpan(2).now();
    LayoutField arsenal = page.place(ARSENAL).below(panoply).withHeight(132).andColumnSpan(2).now();
    LayoutField essence = page.place(ESSENCE_SIMPLE).below(arsenal).fillToBottomOfPage().andColumnSpan(2).now();
    encodeAdditionalMagicPages(sheet, graphics, session);
  }

  private void encodeAdditionalMagicPages(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    while (session.isPageBreakRequired()) {
      SheetPage page = sheet.startPortraitPage(graphics, session);
      page.place(CHARMS_AND_SORCERY).atStartOf(page).fillToBottomOfPage().andColumnSpan(3).now();
    }
  }
}
