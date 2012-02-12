package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.AbstractPageEncoder;
import net.sf.anathema.character.reporting.pdf.layout.Body;
import net.sf.anathema.character.reporting.pdf.layout.RegisteredEncoderList;
import net.sf.anathema.character.reporting.pdf.layout.SheetPage;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.ComboEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.GenericCharmEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.MagicEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.BoxBoundsFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.BACKGROUNDS;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.EXPERIENCE;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.LANGUAGES;
import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.POSSESSIONS;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class SecondPageEncoder extends AbstractPageEncoder {

  public static final float BACKGROUND_HEIGHT = 104;
  public static final float LANGUAGE_HEIGHT = 60;
  private final PageConfiguration configuration;
  private final PdfBoxEncoder boxEncoder;
  private final EncoderRegistry encoders;
  private final IResources resources;

  public SecondPageEncoder(EncoderRegistry encoders, IResources resources, PageConfiguration configuration) {
    super(resources, encoders);
    this.encoders = encoders;
    this.resources = resources;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder();
  }

  private Body createBody() {
    return new Body(configuration);
  }

  private SheetPage createPage(SheetGraphics graphics, ReportContent content) {
    EncodingMetrics metrics = EncodingMetrics.From(graphics, content);
    RegisteredEncoderList registeredEncoderList = new RegisteredEncoderList(resources, encoders);
    return new SheetPage(registeredEncoderList, metrics, graphics);
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    SheetPage page = createPage(graphics, content);
    Body body = createBody();
    LayoutField backgrounds = page.place(BACKGROUNDS).atStartOf(body).withHeight(BACKGROUND_HEIGHT).now();
    LayoutField possessions = page.place(POSSESSIONS).rightOf(backgrounds).withSameHeight().now();
    LayoutField languages = page.place(LANGUAGES).rightOf(possessions).withHeight(LANGUAGE_HEIGHT).now();
    LayoutField experience = page.place(EXPERIENCE).below(languages).alignBottomTo(backgrounds).now();

    float distanceFromTop = BACKGROUND_HEIGHT + PADDING;
    float comboHeight = encodeCombos(graphics, content, distanceFromTop);
    if (comboHeight > 0) {
      distanceFromTop += comboHeight + PADDING;
    }
    if (content.getCharacter().getTemplate().getEdition() == ExaltedEdition.SecondEdition) {
      float genericCharmsHeight = encodeGenericCharms(graphics, content, distanceFromTop);
      if (genericCharmsHeight != 0) {
        distanceFromTop += genericCharmsHeight + PADDING;
      }
    }
    encodeCharms(document, graphics, content, distanceFromTop);
  }

  private float encodeCombos(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    ComboEncoder encoder = new ComboEncoder();
    Bounds restOfPage = new Bounds(configuration.getLeftX(), configuration.getLowerContentY(), configuration.getContentWidth(), configuration.getContentHeight() - distanceFromTop);
    Bounds maxContentBounds = BoxBoundsFactory.calculateContentBounds(restOfPage);
    float yPosition = encoder.encode(graphics, content, maxContentBounds);
    Bounds actualBoxBounds = encoder.calculateActualBoxBounds(restOfPage, yPosition);
    boxEncoder.encodeBox(graphics, actualBoxBounds, encoder.getHeader(content));
    return actualBoxBounds.getHeight();
  }

  private float encodeGenericCharms(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    if (content.getCharacter().getGenericCharmStats().length > 0) {
      float height = 55 + content.getCharacter().getGenericCharmStats().length * 11;
      Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
      ContentEncoder encoder = new GenericCharmEncoder(resources);
      boxEncoder.encodeBox(content, graphics, encoder, bounds);
      return height;
    } else {
      return 0;
    }
  }

  private void encodeCharms(Document document, SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    List<IMagicStats> printMagic = MagicEncoder.collectPrintMagic(content);
    encodeCharms(graphics, printMagic, distanceFromTop, remainingHeight);
    while (!printMagic.isEmpty()) {
      document.newPage();
      encodeCharms(graphics, printMagic, 0, configuration.getContentHeight());
    }
  }

  private float encodeCharms(SheetGraphics graphics, List<IMagicStats> printMagic, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    ContentEncoder encoder = new MagicEncoder(resources, printMagic);
    boxEncoder.encodeBox(null, graphics, encoder, bounds);
    return height;
  }
}
