package net.sf.anathema.character.reporting.pdf.layout.landscape;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.SimpleCharmContent;
import net.sf.anathema.character.reporting.pdf.layout.Body;
import net.sf.anathema.character.reporting.pdf.layout.RegisteredEncoderList;
import net.sf.anathema.character.reporting.pdf.layout.Sheet;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.framework.reporting.pdf.PageSize;
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
  public void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) throws DocumentException {
    SheetPage page = createPage(graphics, session);
    Body body = sheet.startLandscapePage();
    LayoutField genericCharms = page.place(GENERIC_CHARMS).atStartOf(body).withPreferredHeight().andColumnSpan(3).now();
    page.place(CHARMS_AND_SORCERY).below(genericCharms).fillToBottomOfPage().andColumnSpan(3).now();

    LayoutField combat = page.place(COMBAT).rightOf(genericCharms).withHeight(COMBAT_HEIGHT).andColumnSpan(2).now();
    LayoutField health = page.place(HEALTH_AND_MOVEMENT).below(combat).withHeight(HEALTH_HEIGHT).andColumnSpan(2).now();
    LayoutField panoply = page.place(PANOPLY).below(health).withHeight(ARMOUR_HEIGHT).andColumnSpan(2).now();
    LayoutField arsenal = page.place(ARSENAL).below(panoply).withHeight(132).andColumnSpan(2).now();
    LayoutField essence = page.place(ESSENCE_EXTENDED).below(arsenal).fillToBottomOfPage().andColumnSpan(2).now();
    encodeAdditionalMagicPages(sheet, graphics, session);
  }

  private void encodeAdditionalMagicPages(Sheet sheet, SheetGraphics graphics, ReportSession session) {
    SimpleCharmContent charmContent = session.createContent(SimpleCharmContent.class);
    while (charmContent.hasUnprintedCharms()) {
      Body body = sheet.startPortraitPage();
      SheetPage page = createPage(graphics, session);
      page.place(CHARMS_AND_SORCERY).atStartOf(body).fillToBottomOfPage().andColumnSpan(3).now();
    }
  }

  private SheetPage createPage(SheetGraphics graphics, ReportSession session) {
    EncodingMetrics metrics = EncodingMetrics.From(graphics, session);
    RegisteredEncoderList registeredEncoderList = new RegisteredEncoderList(resources, encoders);
    return new SheetPage(registeredEncoderList, metrics, graphics);
  }
}
