package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.SimpleCharmContent;
import net.sf.anathema.character.reporting.pdf.layout.AbstractPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.Body;
import net.sf.anathema.character.reporting.pdf.layout.RegisteredEncoderList;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.BACKGROUNDS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.CHARMS_AND_SORCERY;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.COMBOS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.EXPERIENCE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.GENERIC_CHARMS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.LANGUAGES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.POSSESSIONS;

public class SecondPageEncoder extends AbstractPageEncoder {

  public static final float BACKGROUND_HEIGHT = 104;
  public static final float LANGUAGE_HEIGHT = 60;
  private final PageConfiguration configuration;
  private final EncoderRegistry encoders;
  private final IResources resources;

  public SecondPageEncoder(EncoderRegistry encoders, IResources resources, PageConfiguration configuration) {
    super(resources, encoders);
    this.encoders = encoders;
    this.resources = resources;
    this.configuration = configuration;
  }

  private Body createBody() {
    return new Body(configuration);
  }

  private SheetPage createPage(SheetGraphics graphics, ReportSession session) {
    EncodingMetrics metrics = EncodingMetrics.From(graphics, session);
    RegisteredEncoderList registeredEncoderList = new RegisteredEncoderList(resources, encoders);
    return new SheetPage(registeredEncoderList, metrics, graphics);
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportSession session) throws DocumentException {
    SheetPage page = createPage(graphics, session);
    Body body = createBody();
    LayoutField backgrounds = page.place(BACKGROUNDS).atStartOf(body).withHeight(BACKGROUND_HEIGHT).now();
    LayoutField possessions = page.place(POSSESSIONS).rightOf(backgrounds).withSameHeight().now();
    LayoutField languages = page.place(LANGUAGES).rightOf(possessions).withHeight(LANGUAGE_HEIGHT).now();
    page.place(EXPERIENCE).below(languages).alignBottomTo(backgrounds).now();
    LayoutField combos = page.place(COMBOS).below(backgrounds).withPreferredHeight().andColumnSpan(3).now();
    LayoutField genericCharms = page.place(GENERIC_CHARMS).below(combos).withPreferredHeight().andColumnSpan(3).now();
    page.place(CHARMS_AND_SORCERY).below(genericCharms).fillToBottomOfPage().andColumnSpan(3).now();
    encodeAdditionalMagicPages(document, graphics, session);
  }

  private void encodeAdditionalMagicPages(Document document, SheetGraphics graphics, ReportSession session) {
    SimpleCharmContent charmContent = session.createContent(SimpleCharmContent.class);
    while (charmContent.hasUnprintedCharms()) {
      document.newPage();
      SheetPage page = createPage(graphics, session);
      page.place(CHARMS_AND_SORCERY).atStartOf(createBody()).fillToBottomOfPage().andColumnSpan(3).now();
    }
  }
}
