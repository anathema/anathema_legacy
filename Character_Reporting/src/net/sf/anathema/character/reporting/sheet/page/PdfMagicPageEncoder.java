package net.sf.anathema.character.reporting.sheet.page;

import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.PdfComboEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.PdfMagicEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.generic.PdfGenericCharmEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfMagicPageEncoder extends AbstractPdfPageEncoder {

  private final boolean pureMagic;
  private final boolean essenceBoxNeeded;

  public PdfMagicPageEncoder(
      IPdfPartEncoder partEncoder,
      PdfEncodingRegistry encodingRegistry,
      IResources resources,
      PdfPageConfiguration configuration,
      boolean pureMagic, boolean essenceBoxNeeded) {
    super(partEncoder, encodingRegistry, resources, configuration);
    this.pureMagic = pureMagic;
    this.essenceBoxNeeded = essenceBoxNeeded;
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
    if (!pureMagic)
    {
	    encodeBackgrounds(directContent, character, description, distanceFromTop, backgroundHeight);
	    encodePossessions(directContent, character, description, distanceFromTop, backgroundHeight);
	    encodeLanguages(directContent, character, description, distanceFromTop, languageHeight);
	    distanceFromTop += languageHeight + IVoidStateFormatConstants.PADDING;
	    encodeExperience(directContent, character, description, distanceFromTop, experienceHeight);
	    distanceFromTop += experienceHeight + IVoidStateFormatConstants.PADDING;
    }

    if (essenceBoxNeeded) {
      float essenceHeight = encodeEssence(directContent, character, description, distanceFromTop, CONTENT_HEIGHT);
      distanceFromTop += calculateBoxIncrement(essenceHeight);
    }
    float comboHeight = encodeCombos(directContent, character, distanceFromTop);
    if (comboHeight > 0) {
      distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
    }
    if (character.getTemplate().getEdition() == ExaltedEdition.SecondEdition) {
      float genericCharmsHeight = encodeGenericCharms(directContent, character, description, distanceFromTop);
      if (genericCharmsHeight != 0)
    	  distanceFromTop += genericCharmsHeight + IVoidStateFormatConstants.PADDING;
    }

    float remainingHeight = getPageConfiguration().getContentHeight() - distanceFromTop;
    List<IMagicStats> printMagic = PdfMagicEncoder.collectPrintMagic(character);
    encodeCharms(directContent, printMagic, distanceFromTop, remainingHeight);
    while (!printMagic.isEmpty()) {
      document.newPage();
      encodeCharms(directContent, printMagic, 0, getPageConfiguration().getContentHeight());
    }
  }

  private float encodeCombos(PdfContentByte directContent, IGenericCharacter character, float distanceFromTop)
      throws DocumentException {
    Bounds restOfPage = new Bounds(
        getPageConfiguration().getLeftX(),
        getPageConfiguration().getLowerContentY(),
        getPageConfiguration().getContentWidth(),
        getPageConfiguration().getContentHeight() - distanceFromTop);
    return new PdfComboEncoder(getResources(), getBaseFont()).encodeCombos(directContent, character, restOfPage);
  }

  private float encodeExperience(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = getPageConfiguration().getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = new PdfExperienceEncoder(getResources(), getBaseFont());
    getBoxEncoder().encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeLanguages(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = getPageConfiguration().getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getRegistry().getLinguisticsEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeBackgrounds(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop, float height) throws DocumentException {
    Bounds backgroundBounds = getPageConfiguration().getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfBackgroundEncoder(getResources(), getBaseFont());
    getBoxEncoder().encodeBox(directContent, encoder, character, description, backgroundBounds);
    return height;
  }

  private float encodePossessions(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, character, description,
                          getRegistry().getPossessionsEncoder(),
                          2, 1, distanceFromTop, height);
  }

  private float encodeEssence(PdfContentByte directContent,
                              IGenericCharacter character,
                              IGenericDescription description,
                              float distanceFromTop, float height)
      throws DocumentException {
    // TODO: Eliminate unchecked casting
    return encodeVariableBox(directContent, character, description,
                             (IPdfVariableContentBoxEncoder) getPartEncoder().getEssenceEncoder(),
                             1, 3, distanceFromTop, height);
  }

  private float encodeGenericCharms(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop)
      throws DocumentException {
	if (character.getGenericCharmStats().length > 0)
	{
	  float height = 55 + character.getGenericCharmStats().length * 11;
	  return encodeFixedBox(directContent, character, description,
	                        new PdfGenericCharmEncoder(getResources(), getBaseFont()),
	                        1, 3, distanceFromTop, height);
	}
	else
		return 0;
  }

  private float encodeCharms(PdfContentByte directContent,
                             List<IMagicStats> printMagic,
                             float distanceFromTop, float height)
      throws DocumentException {
    return encodeFixedBox(directContent, null, null,
                          new PdfMagicEncoder(getResources(),
                                              getBaseFont(),
                                              printMagic),
                          1, 3, distanceFromTop, height);
  }
}