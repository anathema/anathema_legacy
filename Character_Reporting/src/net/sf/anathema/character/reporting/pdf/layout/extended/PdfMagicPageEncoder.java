package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.backgrounds.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.experience.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfComboEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfGenericCharmEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.PdfMagicEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
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

  public void encode(Document document, PdfContentByte directContent, IGenericCharacter character, IGenericDescription description) throws



































                                                                                                                                    DocumentException {
    float distanceFromTop = 0;
    float languageHeight = 60;
    float backgroundHeight = 104;
    float experienceHeight = backgroundHeight - languageHeight - IVoidStateFormatConstants.PADDING;
    if (!pureMagic) {
      encodeBackgrounds(directContent, character, description, distanceFromTop, backgroundHeight);
      encodePossessions(directContent, character, description, distanceFromTop, backgroundHeight);
      encodeLanguages(directContent, character, description, distanceFromTop, languageHeight);
      distanceFromTop += languageHeight + IVoidStateFormatConstants.PADDING;
      encodeExperience(directContent, character, description, distanceFromTop, experienceHeight);
      distanceFromTop += experienceHeight + IVoidStateFormatConstants.PADDING;
    }

    float comboHeight = encodeCombos(directContent, character, distanceFromTop);
    if (comboHeight > 0) {
      distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
    }
    if (character.getTemplate().getEdition() == ExaltedEdition.SecondEdition) {
      float genericCharmsHeight = encodeGenericCharms(directContent, character, description, distanceFromTop);
      if (genericCharmsHeight != 0) {
        distanceFromTop += genericCharmsHeight + IVoidStateFormatConstants.PADDING;
      }
    }

    float remainingHeight = getPageConfiguration().getContentHeight() - distanceFromTop;
    List<IMagicStats> printMagic = PdfMagicEncoder.collectPrintMagic(character);
    encodeCharms(directContent, printMagic, distanceFromTop, remainingHeight);
    while (!printMagic.isEmpty()) {
      document.newPage();
      encodeCharms(directContent, printMagic, 0, getPageConfiguration().getContentHeight());
    }
  }

  private float encodeCombos(PdfContentByte directContent, IGenericCharacter character, float distanceFromTop) throws DocumentException {
    Bounds restOfPage = new Bounds(getPageConfiguration().getLeftX(), getPageConfiguration().getLowerContentY(), getPageConfiguration().getContentWidth(), getPageConfiguration().getContentHeight() - distanceFromTop);
    return new PdfComboEncoder(getResources(), getBaseFont()).encodeCombos(directContent, character, restOfPage);
  }

  private float encodeExperience(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = getPageConfiguration().getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new PdfExperienceEncoder(getResources(), getBaseFont());
    getBoxEncoder().encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeLanguages(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = getPageConfiguration().getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getRegistry().getLinguisticsEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeBackgrounds(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    Bounds backgroundBounds = getPageConfiguration().getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfBackgroundEncoder(getResources(), getBaseFont());
    getBoxEncoder().encodeBox(directContent, encoder, character, description, backgroundBounds);
    return height;
  }

  private float encodePossessions(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, character, description, getRegistry().getPossessionsEncoder(), 2, 1, distanceFromTop, height);
  }

  private float encodeGenericCharms(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop) throws DocumentException {
    if (character.getGenericCharmStats().length > 0) {
      float height = 55 + character.getGenericCharmStats().length * 11;
      return encodeFixedBox(directContent, character, description, new PdfGenericCharmEncoder(getResources(), getBaseFont()), 1, 3, distanceFromTop, height);
    }
    else {
      return 0;
    }
  }

  private float encodeCharms(PdfContentByte directContent, List<IMagicStats> printMagic, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, null, null, new PdfMagicEncoder(getResources(), getBaseFont(), printMagic), 1, 3, distanceFromTop, height);
  }
}
