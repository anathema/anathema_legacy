package net.sf.anathema.character.reporting.sheet.page;

import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.PdfComboEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.PdfMagicEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.generic.PdfGenericCharmEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.stats.IMagicStats;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfSecondPageEncoder implements IPdfPageEncoder {

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

  public void encode(
      Document document,
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description) throws DocumentException {
    float distanceFromTop = 0;
    float languageHeight = 60;
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
    if (character.getTemplate().getEdition() == ExaltedEdition.SecondEdition) {
      float genericCharmsHeight = encodeGenericCharms(directContent, character, distanceFromTop, 110);
      distanceFromTop += genericCharmsHeight + IVoidStateFormatConstants.PADDING;
    }

    float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    List<IMagicStats> printMagic = PdfMagicEncoder.collectPrintMagic(character);
    encodeCharms(directContent, printMagic, distanceFromTop, remainingHeight);
    while (!printMagic.isEmpty()) {
      document.newPage();
      encodeCharms(directContent, printMagic, 0, configuration.getContentHeight());
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
    IPdfContentBoxEncoder encoder = new PdfExperienceEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeLanguages(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds languageBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(2, "Languages"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, languageBounds);
    return height;
  }

  private float encodeBackgrounds(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds backgroundBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfBackgroundEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, backgroundBounds);
    return height;
  }

  private float encodePossessions(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(1, "Possessions"); //$NON-NLS-1$
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeGenericCharms(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    IPdfContentBoxEncoder encoder = new PdfGenericCharmEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeCharms(
      PdfContentByte directContent,
      List<IMagicStats> printMagic,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    IPdfContentBoxEncoder encoder = new PdfMagicEncoder(resources, baseFont, printMagic);
    boxEncoder.encodeBox(directContent, encoder, null, bounds);
    return height;
  }
}