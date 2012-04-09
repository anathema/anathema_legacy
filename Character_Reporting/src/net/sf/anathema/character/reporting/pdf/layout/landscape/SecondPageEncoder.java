package net.sf.anathema.character.reporting.pdf.layout.landscape;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.AllMagicContent;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.CHARMS_AND_SORCERY;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.COMBAT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ESSENCE_EXTENDED;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.GENERIC_CHARMS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.HEALTH_AND_MOVEMENT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.PANOPLY;

public class SecondPageEncoder implements PageEncoder {

  private static final int COMBAT_HEIGHT = 125;
  private static final int ARMOUR_HEIGHT = 68;
  private static final int HEALTH_HEIGHT = 110;
  private EncoderRegistry encoders;
  private IResources resources;

  public SecondPageEncoder(EncoderRegistry encoders, IResources resources) {
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
    LayoutField essence = page.place(ESSENCE_EXTENDED).below(arsenal).fillToBottomOfPage().andColumnSpan(2).now();
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
