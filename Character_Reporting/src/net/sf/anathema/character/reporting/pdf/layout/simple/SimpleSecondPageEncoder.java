package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.AbstractPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfComboEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfGenericCharmEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfMagicEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

public class SimpleSecondPageEncoder extends AbstractPageEncoder {

  private final PdfPageConfiguration configuration;
  private final PdfBoxEncoder boxEncoder;
  private final IResources resources;
  private final SimpleEncodingRegistry encodingRegistry;

  public SimpleSecondPageEncoder(EncoderRegistry encoderRegistry, IResources resources, SimpleEncodingRegistry encodingRegistry,
    PdfPageConfiguration configuration) {
    super(resources, encoderRegistry);
    this.resources = resources;
    this.encodingRegistry = encodingRegistry;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder(resources);
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    float distanceFromTop = 0;
    float languageHeight = 60;
    float backgroundHeight = 104;
    float experienceHeight = backgroundHeight - languageHeight - IVoidStateFormatConstants.PADDING;
    encodeBackgrounds(graphics, content, distanceFromTop, backgroundHeight);
    encodePossessions(graphics, content, distanceFromTop, backgroundHeight);
    encodeLanguages(graphics, content, distanceFromTop, languageHeight);
    distanceFromTop += languageHeight + IVoidStateFormatConstants.PADDING;
    encodeExperience(graphics, content, distanceFromTop, experienceHeight);
    distanceFromTop += experienceHeight + IVoidStateFormatConstants.PADDING;
    float comboHeight = encodeCombos(graphics, content, distanceFromTop);
    if (comboHeight > 0) {
      distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
    }
    if (content.getCharacter().getTemplate().getEdition() == ExaltedEdition.SecondEdition) {
      float genericCharmsHeight = encodeGenericCharms(graphics, content, distanceFromTop);
      if (genericCharmsHeight != 0) {
        distanceFromTop += genericCharmsHeight + IVoidStateFormatConstants.PADDING;
      }
    }

    float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    List<IMagicStats> printMagic = PdfMagicEncoder.collectPrintMagic(content);
    encodeCharms(graphics, printMagic, distanceFromTop, remainingHeight);
    while (!printMagic.isEmpty()) {
      document.newPage();
      encodeCharms(graphics, printMagic, 0, configuration.getContentHeight());
    }
  }

  private float encodeCombos(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    Bounds restOfPage = new Bounds(configuration.getLeftX(), configuration.getLowerContentY(), configuration.getContentWidth(),
      configuration.getContentHeight() - distanceFromTop);
    return new PdfComboEncoder(resources).encodeCombos(graphics, content, restOfPage);
  }

  private float encodeExperience(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = new ExperienceBoxContentEncoder();
    boxEncoder.encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeLanguages(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    return encodeBox(graphics, content, bounds, EncoderIds.LANGUAGES);
  }

  private float encodeBackgrounds(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds backgroundBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new PdfBackgroundEncoder(resources);
    boxEncoder.encodeBox(content, graphics, encoder, backgroundBounds);
    return height;
  }

  private float encodePossessions(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    return encodeBox(graphics, content, bounds, EncoderIds.POSSESSIONS);
  }

  private float encodeGenericCharms(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    if (content.getCharacter().getGenericCharmStats().length > 0) {
      float height = 55 + content.getCharacter().getGenericCharmStats().length * 11;
      Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
      ContentEncoder encoder = new PdfGenericCharmEncoder(resources);
      boxEncoder.encodeBox(content, graphics, encoder, bounds);
      return height;
    }
    else {
      return 0;
    }
  }

  private float encodeCharms(SheetGraphics graphics, List<IMagicStats> printMagic, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    ContentEncoder encoder = new PdfMagicEncoder(resources, printMagic);
    boxEncoder.encodeBox(null, graphics, encoder, bounds);
    return height;
  }
}
