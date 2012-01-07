package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.AbstractPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.ComboEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.GenericCharmEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.MagicEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.PADDING;

public class SecondPageEncoder extends AbstractPageEncoder {

  private final PageConfiguration configuration;
  private final PdfBoxEncoder boxEncoder;
  private final IResources resources;

  public SecondPageEncoder(EncoderRegistry encoders, IResources resources, PageConfiguration configuration) {
    super(resources, encoders);
    this.resources = resources;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder();
  }

  @Override
  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    float distanceFromTop = 0;
    float languageHeight = 60;
    float backgroundHeight = 104;
    float experienceHeight = backgroundHeight - languageHeight - PADDING;
    encodeBackgrounds(graphics, content, distanceFromTop, backgroundHeight);
    encodePossessions(graphics, content, distanceFromTop, backgroundHeight);
    encodeLanguages(graphics, content, distanceFromTop, languageHeight);
    distanceFromTop += languageHeight + PADDING;
    encodeExperience(graphics, content, distanceFromTop, experienceHeight);
    distanceFromTop += experienceHeight + PADDING;
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
    Bounds restOfPage = new Bounds(configuration.getLeftX(), configuration.getLowerContentY(), configuration.getContentWidth(), configuration.getContentHeight() - distanceFromTop);
    return new ComboEncoder(resources).encodeCombos(graphics, content, restOfPage);
  }

  private float encodeExperience(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    return encodeBox(graphics, content, bounds, EncoderIds.EXPERIENCE);
  }

  private float encodeLanguages(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    return encodeBox(graphics, content, bounds, EncoderIds.LANGUAGES);
  }

  private float encodeBackgrounds(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds backgroundBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    return encodeBox(graphics, content, backgroundBounds, EncoderIds.BACKGROUNDS);
  }

  private float encodePossessions(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeBox(graphics, content, bounds, EncoderIds.POSSESSIONS);
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
