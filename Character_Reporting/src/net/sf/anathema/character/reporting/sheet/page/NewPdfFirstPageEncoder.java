package net.sf.anathema.character.reporting.sheet.page;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAbilitiesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAttributesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfBackgroundEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfExperienceEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.NewSecondEditionPersonalInfoEncoder;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionPersonalInfoEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class NewPdfFirstPageEncoder implements IPdfPageEncoder {
  public static final float CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  private static final float ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final PdfEncodingRegistry registry;
  private final IPdfPartEncoder partEncoder;

  public NewPdfFirstPageEncoder(
      IPdfPartEncoder partEncoder,
      PdfEncodingRegistry registry,
      IResources resources,
      int essenceMax,
      PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.essenceMax = essenceMax;
    this.resources = resources;
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(
      Document document,
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description) throws DocumentException {
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    
    int distanceFromTop = 0;
    final int firstRowHeight = (characterType.isExaltType() ? 60 : 50);
    encodePersonalInfo(directContent, character, description, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    // First column - attributes & abilities
    encodeFirstColumn(directContent, character, distanceFromTop);
    
    // Second column - top-down
    float secondDistanceFromTop = distanceFromTop;
    float virtueHeight = encodeVirtues(directContent, secondDistanceFromTop, 72, character);
    float virtueIncrement = calculateBoxIncrement(virtueHeight);
    secondDistanceFromTop += virtueIncrement;
    float estimatedGreatCurseHeight = (characterType.isExaltType() ? virtueHeight : 0);
    float actualGreatCurseHeight = encodeGreatCurse(directContent, character, secondDistanceFromTop, estimatedGreatCurseHeight);
    float greatCurseIncrement = (actualGreatCurseHeight != 0 ? calculateBoxIncrement(actualGreatCurseHeight) : 0);
    secondDistanceFromTop += greatCurseIncrement;
    // Second column - bottom-up
    float willpowerHeight = encodeWillpowerDots(directContent, character, CONTENT_HEIGHT - 30, 30);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    float meritsIncrement = 0;
    // Second column - fill in (bottom-up) with merits & flaws, intimacies
    if (hasMeritsAndFlaws(character)) {
      float height = (CONTENT_HEIGHT - willpowerIncrement - secondDistanceFromTop - IVoidStateFormatConstants.PADDING) / 2f;
      meritsIncrement = calculateBoxIncrement(encodeMeritsAndFlaws(directContent, character, 2,
                                                                   CONTENT_HEIGHT - willpowerIncrement - height, height));
    }
    encodeIntimacies(directContent, character, secondDistanceFromTop,
                     CONTENT_HEIGHT - secondDistanceFromTop - meritsIncrement - willpowerIncrement);

    // Third column - bottom-up
    float thirdBottom = CONTENT_HEIGHT;
    float essenceHeight = encodeEssenceDots(directContent, character, thirdBottom - willpowerHeight, willpowerHeight);
    thirdBottom -= calculateBoxIncrement(essenceHeight);
    float experienceHeight = encodeExperience(directContent, character, thirdBottom - 30, 30);
    thirdBottom -= calculateBoxIncrement(experienceHeight);
    float languageHeight = encodeLinguistics(directContent, character, thirdBottom - 60, 60);
    thirdBottom -= calculateBoxIncrement(languageHeight);
    // Third column - fill in (bottom-up) with backgrounds, mutations
    float mutationsIncrement = 0;
    if (hasMutations(character))
    {
      float height = (thirdBottom - distanceFromTop - IVoidStateFormatConstants.PADDING) / 2f;
      mutationsIncrement = calculateBoxIncrement(encodeMutations(directContent, character, 3, thirdBottom - height, height));
    }
    thirdBottom -= mutationsIncrement;
    encodeBackgrounds(directContent, character, distanceFromTop, thirdBottom - distanceFromTop);
    
    encodeCopyright(directContent);
  }
  
  private int getColumn(int boxId)
  {
	  return boxId % 2 == 1 ? 3 : 2;
  }
  
  private float getPosition(int position, float height, int id)
  {
	  return position + (id > 1 ? height : 0);
  }

  private void encodeCopyright(PdfContentByte directContent) throws DocumentException {
    int lineHeight = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 2;
    Font copyrightFont = new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    float copyrightHeight = pageConfiguration.getPageHeight() - pageConfiguration.getContentHeight();
    Bounds firstColumnBounds = pageConfiguration.getFirstColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    Anchor voidstatePhrase = new Anchor("Inspired by Voidstate\nhttp://www.voidstate.com", copyrightFont); //$NON-NLS-1$
    voidstatePhrase.setReference("http://www.voidstate.com"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(directContent, voidstatePhrase, firstColumnBounds, lineHeight);
    
    // TODO: Eliminate these hard-coded copyright dates; these should be in a properties file or something.
    Anchor anathemaPhrase = new Anchor("Created with Anathema \u00A92011\nhttp://anathema.sf.net", copyrightFont); //$NON-NLS-1$
    anathemaPhrase.setReference("http://anathema.sf.net"); //$NON-NLS-1$
    Bounds anathemaBounds = pageConfiguration.getSecondColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    PdfTextEncodingUtilities.encodeText(directContent, anathemaPhrase, anathemaBounds, lineHeight, Element.ALIGN_CENTER);
    Anchor whitewolfPhrase = new Anchor("Exalted \u00A92011 by White Wolf, Inc.\nhttp://www.white-wolf.com", //$NON-NLS-1$
        copyrightFont);
    whitewolfPhrase.setReference("http://www.white-wolf.com"); //$NON-NLS-1$
    
    Bounds whitewolfBounds = pageConfiguration.getThirdColumnRectangle(CONTENT_HEIGHT, copyrightHeight);
    PdfTextEncodingUtilities.encodeText(
        directContent,
        whitewolfPhrase,
        whitewolfBounds,
        lineHeight,
        Element.ALIGN_RIGHT);
  }

  private float encodeEssenceDots(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = partEncoder.getDotsEncoder(OtherTraitType.Essence, EssenceTemplate.SYSTEM_ESSENCE_MAX, "Essence");
    boxEncoder.encodeBox(directContent, encoder, character, essenceBounds);
    return height;
  }

  private String getHeaderLabel(String key) {
    return resources.getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      int distanceFromTop,
      final int firstRowHeight) {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 3);
    String name = description.getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(directContent, infoBounds, title);
    encodePersonalInfos(directContent, character, description, infoContentBounds);
  }

  private void encodeFirstColumn(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    float attributeHeight = encodeAttributes(directContent, character, distanceFromTop);
    encodeAbilities(directContent, character, distanceFromTop + attributeHeight + PADDING);
  }

  private void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, float distanceFromTop)
      throws DocumentException {
    float abilitiesHeight = CONTENT_HEIGHT - distanceFromTop;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IPdfContentBoxEncoder encoder = PdfAbilitiesEncoder.createWithCraftsAndSpecialties(baseFont, resources, essenceMax);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
  }

  private float encodeAttributes(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    float attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    IPdfContentBoxEncoder encoder = new PdfAttributesEncoder(
        baseFont,
        resources,
        essenceMax,
        partEncoder.isEncodeAttributeAsFavorable());
    boxEncoder.encodeBox(directContent, encoder, character, attributeBounds);
    return attributeHeight;
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private float encodeAnima(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = partEncoder.getAnimaEncoder();
    if (encoder != null)
    {
    	boxEncoder.encodeBox(directContent, encoder, character, animaBounds);
    	return ANIMA_HEIGHT;
    }
    return 0;
  }
  
  private float encodeBackgrounds(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	    IPdfContentBoxEncoder contentEncoder = new PdfBackgroundEncoder(resources, baseFont);
	    boxEncoder.encodeBox(directContent, contentEncoder, character, bounds);
	    return height;
	  }

  private void encodePersonalInfos(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      Bounds infoBounds) {
    NewSecondEditionPersonalInfoEncoder encoder = new NewSecondEditionPersonalInfoEncoder(baseFont, resources);
    encoder.encodePersonalInfos(directContent, character, description, infoBounds);
  }

  private float encodeVirtues(
      PdfContentByte directContent,
      float distanceFromTop,
      float height,
      IGenericCharacter character) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = new PdfVirtueEncoder(resources, baseFont);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeWillpowerDots(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = partEncoder.getDotsEncoder(OtherTraitType.Willpower, 10, "Willpower");
    boxEncoder.encodeBox(directContent, encoder, character, willpowerBounds);
    return height;
  }

  private float encodeGreatCurse(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = partEncoder.getGreatCurseEncoder();
    if (encoder != null)
    {
    	boxEncoder.encodeBox(directContent, encoder, character, bounds);
    	return height;
    }
    else
    	return 0;
  }
  
  /*private float encodeOverdrive(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
	    IPdfContentBoxEncoder encoder = partEncoder.getOverdriveEncoder();
	    if (encoder != null)
	    {
	    	boxEncoder.encodeBox(directContent, encoder, character, bounds);
	    	return height;
	    }
	    else
	    	return 0;
	  }*/

  private float encodeIntimacies(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = partEncoder.getIntimaciesEncoder(registry);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }
  
  private float encodeLinguistics(
		  PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException
  {
	  Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	  IPdfContentBoxEncoder encoder = registry.getLinguisticsEncoder();
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);
	  return height;
  }
  
  private float encodeExperience(
		  PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException
  {
	  Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	  IPdfContentBoxEncoder encoder = new PdfExperienceEncoder(resources, baseFont);
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);
	  return height;
  }
  
  private void encodeNotes(
		  PdfContentByte directContent,
	      IGenericCharacter character,
	      int boxId,
	      float distanceFromTop,
	      float frameHeight,
	      float height) throws DocumentException
  {
	  Bounds bounds = null;
	  int columns = 1;
	  float position = distanceFromTop;
	  switch (boxId)
	  {
	  case 0:
		  bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, 2 * height, 2);
		  break;
	  case 1:
	  case 2:
		  bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop + frameHeight, height, 2);
		  position += height + PADDING;
		  columns = 2;
		  break;
	  case 3:
		  bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop + frameHeight, height);
		  position += height + PADDING;
		  break;
	  case 4:
		  return;
	  }
	  IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(columns, "Notes");;
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);	  
  }
  
  private boolean hasMutations(IGenericCharacter character)
  {
	  return registry.getMutationsEncoder().hasContent(character);
  }
  
  private boolean hasMeritsAndFlaws(IGenericCharacter character)
  {
	  return registry.getMeritsAndFlawsEncoder().hasContent(character);
  }
  
  private boolean hasThaumaturgy(IGenericCharacter character)
  {
	  return registry.getThaumaturgyEncoder().hasContent(character);
  }
  
  private float encodeMutations(
		  PdfContentByte directContent,
	      IGenericCharacter character,
	      int column,
	      float distanceFromTop,
	      float height) throws DocumentException
  {
	  Bounds bounds = column == 2 ? pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1) :
		  pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	  IPdfContentBoxEncoder encoder = registry.getMutationsEncoder();
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);	  
	  return height;
  }
  
  private float encodeMeritsAndFlaws(
		  PdfContentByte directContent,
	      IGenericCharacter character,
	      int column,
	      float distanceFromTop,
	      float height) throws DocumentException
  {
	  Bounds bounds = column == 2 ? pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1) :
		  pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	  IPdfContentBoxEncoder encoder = registry.getMeritsAndFlawsEncoder();
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);
	  return height;
  }
  
  private void encodeThaumaturgy(
		  PdfContentByte directContent,
	      IGenericCharacter character,
	      int column,
	      float distanceFromTop,
	      float height) throws DocumentException
  {
	  Bounds bounds = column == 2 ? pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1) :
		  pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	  IPdfContentBoxEncoder encoder = registry.getThaumaturgyEncoder();
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);	  
  }
}