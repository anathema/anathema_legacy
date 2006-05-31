package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.PdfComboEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfSecondPageEncoder implements IPdfPageEncoder {

  private final IPdfContentEncoder nullContentEncoder = new IPdfContentEncoder() {
    public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
        throws DocumentException {
    }
  };
  private final BaseFont baseFont;
  private final PdfPageConfiguration configuration;
  private PdfBoxEncoder boxEncoder;
  private final IResources resources;

  public PdfSecondPageEncoder(IResources resources, BaseFont baseFont, PdfPageConfiguration configuration) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description)
      throws DocumentException {
    float distanceFromTop = 0;
    float languageHeight = 59;
    float backgroundHeight = 104;
    float experienceHeight = backgroundHeight - languageHeight - IVoidStateFormatConstants.PADDING;
    encodeBackgrounds(directContent, character, distanceFromTop, backgroundHeight);
    encodePossessions(directContent, character, distanceFromTop, backgroundHeight);
    encodeLanguages(directContent, character, distanceFromTop, languageHeight);
    distanceFromTop += languageHeight + IVoidStateFormatConstants.PADDING;
    encodeExperience(directContent, character, distanceFromTop, experienceHeight);
    distanceFromTop += experienceHeight + IVoidStateFormatConstants.PADDING;
    float comboHeight = encodeCombos(directContent, character, distanceFromTop);
    if (comboHeight > 0) {
      distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
    }
  }

  private float encodeCombos(PdfContentByte directContent, IGenericCharacter character, float distanceFromTop)
      throws DocumentException {
    Bounds restOfPage = new Bounds(
        configuration.getLeftX(),
        configuration.getLowerContentY(),
        configuration.getContentWidth(),
        configuration.getContentHeight() - distanceFromTop);
    return new PdfComboEncoder(resources, baseFont).encodeCombos(directContent, character, restOfPage);
  }

  private float encodeExperience(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    boxEncoder.encodeBox(directContent, nullContentEncoder, character, bounds, "Experience"); //$NON-NLS-1$
    return height;
  }

  private float encodeLanguages(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds languageBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentEncoder encoder = new PdfHorizontalLineContentEncoder(2);
    boxEncoder.encodeBox(directContent, encoder, character, languageBounds, "Languages"); //$NON-NLS-1$
    return height;
  }

  private float encodeBackgrounds(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds backgroundBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentEncoder encoder = new PdfBackgroundEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, backgroundBounds, "Backgrounds"); //$NON-NLS-1$
    return height;
  }


  private float encodePossessions(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds backgroundBounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentEncoder encoder = new PdfHorizontalLineContentEncoder(1);
    boxEncoder.encodeBox(directContent, encoder, character, backgroundBounds, "Possessions"); //$NON-NLS-1$
    return height;
  }
}