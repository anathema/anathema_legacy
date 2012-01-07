package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.BackgroundsEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.ExperienceBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.ComboEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.GenericCharmEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.MagicEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

public class ExtendedMagic1stEditionPageEncoder extends AbstractPdfPageEncoder {

  private final boolean pureMagic;

  public ExtendedMagic1stEditionPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry encodingRegistry, IResources resources, PageConfiguration configuration, boolean pureMagic) {
    super(partEncoder, encodingRegistry, resources, configuration);
    this.pureMagic = pureMagic;
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws

          DocumentException {
    float distanceFromTop = 0;
    float languageHeight = 60;
    float backgroundHeight = 104;
    float experienceHeight = backgroundHeight - languageHeight - IVoidStateFormatConstants.PADDING;
    if (!pureMagic) {
      encodeBackgrounds(graphics, content, distanceFromTop, backgroundHeight);
      encodePossessions(graphics, content, distanceFromTop, backgroundHeight);
      encodeLanguages(graphics, content, distanceFromTop, languageHeight);
      distanceFromTop += languageHeight + IVoidStateFormatConstants.PADDING;
      encodeExperience(graphics, content, distanceFromTop, experienceHeight);
      distanceFromTop += experienceHeight + IVoidStateFormatConstants.PADDING;
    }

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

    float remainingHeight = getPageConfiguration().getContentHeight() - distanceFromTop;
    List<IMagicStats> printMagic = MagicEncoder.collectPrintMagic(content);
    encodeCharms(graphics, content, printMagic, distanceFromTop, remainingHeight);
    while (!printMagic.isEmpty()) {
      document.newPage();
      encodeCharms(graphics, content, printMagic, 0, getPageConfiguration().getContentHeight());
    }
  }

  private float encodeCombos(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    Bounds restOfPage = new Bounds(getPageConfiguration().getLeftX(), getPageConfiguration().getLowerContentY(), getPageConfiguration().getContentWidth(), getPageConfiguration().getContentHeight() - distanceFromTop);
    return new ComboEncoder(getResources()).encodeCombos(graphics, content, restOfPage);
  }

  private float encodeExperience(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = getPageConfiguration().getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = new ExperienceBoxContentEncoder();
    getBoxEncoder().encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeLanguages(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = getPageConfiguration().getThirdColumnRectangle(distanceFromTop, height);
    ContentEncoder encoder = getRegistry().getLinguisticsEncoder();
    getBoxEncoder().encodeBox(content, graphics, encoder, bounds);
    return height;
  }

  private float encodeBackgrounds(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds backgroundBounds = getPageConfiguration().getFirstColumnRectangle(distanceFromTop, height, 1);
    ContentEncoder encoder = new BackgroundsEncoder(getResources());
    getBoxEncoder().encodeBox(content, graphics, encoder, backgroundBounds);
    return height;
  }

  private float encodePossessions(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, getRegistry().getPossessionsEncoder(), 2, 1, distanceFromTop, height);
  }

  private float encodeGenericCharms(SheetGraphics graphics, ReportContent content, float distanceFromTop) throws DocumentException {
    if (content.getCharacter().getGenericCharmStats().length > 0) {
      float height = 55 + content.getCharacter().getGenericCharmStats().length * 11;
      return encodeFixedBox(graphics, content, new GenericCharmEncoder(getResources()), 1, 3, distanceFromTop, height);
    } else {
      return 0;
    }
  }

  private float encodeCharms(SheetGraphics graphics, ReportContent content, List<IMagicStats> printMagic, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, new MagicEncoder(getResources(), printMagic), 1, 3, distanceFromTop, height);
  }
}
