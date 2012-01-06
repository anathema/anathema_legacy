package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.Body;
import net.sf.anathema.character.reporting.pdf.layout.RegisteredEncoderList;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.general.CopyrightEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ABILITIES_WITH_CRAFTS_AND_SPECIALTIES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ANIMA;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ATTRIBUTES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.COMBAT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ESSENCE_SIMPLE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.GREAT_CURSE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.HEALTH_AND_MOVEMENT;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.INTIMACIES_SIMPLE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.MERITS_AND_FLAWS;
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
  private static final int ARMOUR_HEIGHT = 80;
  private static final int HEALTH_HEIGHT = 99;
  private EncoderRegistry encoders;
  private IResources resources;
  private final PageConfiguration configuration;

  public FirstPageEncoder(EncoderRegistry encoders, IResources resources, PageConfiguration configuration) {
    this.encoders = encoders;
    this.resources = resources;
    this.configuration = configuration;
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    SheetPage page = createPage(graphics, content);
    Body body = createBody();
    LayoutField personalInfo = page.place(PERSONAL_INFO).atStartOf(body).withHeight(FIRST_ROW_HEIGHT).spanningTwoColumns().now();
    LayoutField essence = page.place(ESSENCE_SIMPLE).rightOf(personalInfo).withSameHeight().now();
    LayoutField attributes = page.place(ATTRIBUTES).below(personalInfo).withHeight(ATTRIBUTE_HEIGHT).now();
    page.place(ABILITIES_WITH_CRAFTS_AND_SPECIALTIES).below(attributes).fillToBottomOfPage().now();
    LayoutField anima = page.place(ANIMA).below(essence).withHeight(ANIMA_HEIGHT).now();
    LayoutField social = page.place(SOCIAL_COMBAT, MERITS_AND_FLAWS).below(anima).withHeight(SOCIAL_COMBAT_HEIGHT).now();
    LayoutField virtues = page.place(VIRTUES).rightOf(attributes).withHeight(VIRTUE_HEIGHT).now();
    LayoutField greatCurse = page.place(GREAT_CURSE).below(virtues).alignBottomTo(anima).now();
    LayoutField willpower = page.place(WILLPOWER_SIMPLE).below(greatCurse).withHeight(WILLPOWER_HEIGHT).now();
    LayoutField intimacies = page.place(INTIMACIES_SIMPLE, NOTES).below(willpower).alignBottomTo(social).now();
    LayoutField arsenal = page.place(ARSENAL).below(intimacies).withPreferredHeight().spanningTwoColumns().now();
    LayoutField panoply = page.place(PANOPLY).below(arsenal).withHeight(ARMOUR_HEIGHT).spanningTwoColumns().now();
    LayoutField health = page.place(HEALTH_AND_MOVEMENT).below(panoply).withHeight(HEALTH_HEIGHT).spanningTwoColumns().now();
    page.place(COMBAT).below(health).fillToBottomOfPage().spanningTwoColumns().now();
    new CopyrightEncoder(configuration, FIXED_CONTENT_HEIGHT).encodeCopyright(graphics);
  }

  private Body createBody() {
    return new Body(configuration, FIXED_CONTENT_HEIGHT);
  }

  private SheetPage createPage(SheetGraphics graphics, ReportContent content) {
    EncodingMetrics metrics = EncodingMetrics.From(graphics, content);
    RegisteredEncoderList registeredEncoderList = new RegisteredEncoderList(resources, encoders);
    return new SheetPage(registeredEncoderList, metrics, graphics);
  }
}
