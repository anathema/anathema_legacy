package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfComboEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfGenericCharmEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfMagicEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import java.util.List;

public class PdfMagicPageEncoder extends AbstractPdfPageEncoder {

  private final boolean pureMagic;

  public PdfMagicPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry encodingRegistry, IResources resources,
    PdfPageConfiguration configuration, boolean pureMagic) {
    super(partEncoder, encodingRegistry, resources, configuration);
    this.pureMagic = pureMagic;
  }

  public void encode(Document document, PdfContentByte directContent, ReportContent content) throws

    DocumentException {
    float distanceFromTop = 0;
    float languageHeight = 60;
    float backgroundHeight = 104;
    float experienceHeight = backgroundHeight - languageHeight - IVoidStateFormatConstants.PADDING;
    if (!pureMagic) {
      encodeBackgrounds(directContent, content, distanceFromTop, backgroundHeight);
      encodePossessions(directContent, content, distanceFromTop, backgroundHeight);
      encodeLanguages(directContent, content, distanceFromTop, languageHeight);
      distanceFromTop += languageHeight + IVoidStateFormatConstants.PADDING;
      encodeExperience(directContent, content, distanceFromTop, experienceHeight);
      distanceFromTop += experienceHeight + IVoidStateFormatConstants.PADDING;
    }

    float comboHeight = encodeCombos(directContent, content, distanceFromTop);
    if (comboHeight > 0) {
      distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
    }
    if (content.getCharacter().getTemplate().getEdition() == ExaltedEdition.SecondEdition) {
      float genericCharmsHeight = encodeGenericCharms(directContent, content, distanceFromTop);
      if (genericCharmsHeight != 0) {
        distanceFromTop += genericCharmsHeight + IVoidStateFormatConstants.PADDING;
      }
    }

    float remainingHeight = getPageConfiguration().getContentHeight() - distanceFromTop;
    List<IMagicStats> printMagic = PdfMagicEncoder.collectPrintMagic(content);
    encodeCharms(directContent, content, printMagic, distanceFromTop, remainingHeight);
    while (!printMagic.isEmpty()) {
      document.newPage();
      encodeCharms(directContent, content, printMagic, 0, getPageConfiguration().getContentHeight());
    }
  }

  private float encodeCombos(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    Bounds restOfPage = new Bounds(getPageConfiguration().getLeftX(), getPageConfiguration().getLowerContentY(),
      getPageConfiguration().getContentWidth(), getPageConfiguration().getContentHeight() - distanceFromTop);
    return new PdfComboEncoder(getResources(), getBaseFont()).encodeCombos(directContent, content, restOfPage);
  }

  private float encodeExperience(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = getPageConfiguration().getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = new PdfExperienceEncoder(getResources(), getBaseFont());
    getBoxEncoder().encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeLanguages(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = getPageConfiguration().getThirdColumnRectangle(distanceFromTop, height);
    IBoxContentEncoder encoder = getRegistry().getLinguisticsEncoder();
    getBoxEncoder().encodeBox(content, directContent, encoder, bounds);
    return height;
  }

  private float encodeBackgrounds(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    Bounds backgroundBounds = getPageConfiguration().getFirstColumnRectangle(distanceFromTop, height, 1);
    IBoxContentEncoder encoder = new PdfBackgroundEncoder(getResources(), getBaseFont());
    getBoxEncoder().encodeBox(content, directContent, encoder, backgroundBounds);
    return height;
  }

  private float encodePossessions(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getRegistry().getPossessionsEncoder(), 2, 1, distanceFromTop, height);
  }

  private float encodeGenericCharms(PdfContentByte directContent, ReportContent content, float distanceFromTop) throws DocumentException {
    if (content.getCharacter().getGenericCharmStats().length > 0) {
      float height = 55 + content.getCharacter().getGenericCharmStats().length * 11;
      return encodeFixedBox(directContent, content, new PdfGenericCharmEncoder(getResources(), getBaseFont()), 1, 3, distanceFromTop, height);
    }
    else {
      return 0;
    }
  }

  private float encodeCharms(PdfContentByte directContent, ReportContent content, List<IMagicStats> printMagic, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, new PdfMagicEncoder(getResources(), getBaseFont(), printMagic), 1, 3, distanceFromTop, height);
  }
}
