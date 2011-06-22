package net.sf.anathema.character.reporting.sheet.page;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
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

public class PdfFirstPageEncoder implements IPdfPageEncoder {
  public static final float CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  private static final float ANIMA_HEIGHT = 128;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final PdfEncodingRegistry registry;
  private final IPdfPartEncoder partEncoder;

  public PdfFirstPageEncoder(
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
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(directContent, character, description, distanceFromTop, firstRowHeight);
    encodeEssence(directContent, character, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    encodeFirstColumn(directContent, character, distanceFromTop);
    
    float extraBoxTop = distanceFromTop;
    int extraBoxHeight = 115;
    /*float actualOverdriveHeight = encodeOverdrive(directContent, character, distanceFromTop, 43);
    float overdriveIncrement = actualOverdriveHeight != 0 ? calculateBoxIncrement(actualOverdriveHeight) : 0;
    backgroundTop += overdriveIncrement;*/
    float actualAnimaHeight = encodeAnima(directContent, character, extraBoxTop, ANIMA_HEIGHT);
    float animaIncrement = actualAnimaHeight != 0 ? calculateBoxIncrement(actualAnimaHeight) : 0;
    extraBoxTop += actualAnimaHeight != 0 ? animaIncrement : 0;
    extraBoxHeight += actualAnimaHeight != 0 ? -3*ANIMA_HEIGHT/4 : ANIMA_HEIGHT;
    
    float virtueHeight = encodeVirtues(directContent, distanceFromTop, 72, character);
    float virtueIncrement = calculateBoxIncrement(virtueHeight);
    distanceFromTop += virtueIncrement;
    float estimatedGreatCurseHeight = actualAnimaHeight - virtueHeight - IVoidStateFormatConstants.PADDING;
    float actualGreatCurseHeight = encodeGreatCurse(directContent, character, distanceFromTop, estimatedGreatCurseHeight);
    float greatCurseIncrement = actualGreatCurseHeight != 0 ? calculateBoxIncrement(actualGreatCurseHeight) : 0;
    distanceFromTop  += greatCurseIncrement;
    extraBoxHeight += greatCurseIncrement;

    //float socialCombatHeight = encodeSocialCombatStats(directContent, character, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, character, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    extraBoxHeight += willpowerIncrement;

    float intimaciesSpace = extraBoxHeight + animaIncrement
    	- virtueIncrement - greatCurseIncrement - willpowerIncrement;
    float intimaciesHeight = encodeIntimacies(directContent, character, distanceFromTop, intimaciesSpace);
    distanceFromTop += calculateBoxIncrement(intimaciesHeight);

    float lastRowHeight = firstRowHeight;
    float remainingHeight = PdfFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop - lastRowHeight;
    float frameHeight = remainingHeight / 2;
    float boxHeight = frameHeight - PADDING;
    int boxPosition = distanceFromTop;
    int boxId = 0;
    
    if (hasMutations(character))
    	encodeMutations(directContent, character, getColumn(boxId), getPosition(boxPosition, frameHeight, boxId++), boxHeight);
    if (hasMeritsAndFlaws(character))
    	encodeMeritsAndFlaws(directContent, character, getColumn(boxId), getPosition(boxPosition, frameHeight, boxId++), boxHeight);
    if (hasThaumaturgy(character))
    	encodeThaumaturgy(directContent, character, getColumn(boxId), getPosition(boxPosition, frameHeight, boxId++), boxHeight);
    
    //odd case... extend the background box here, I don't know what else to do
    if (boxId == 1)
    	extraBoxHeight += boxHeight + PADDING;
    
    encodeNotes(directContent, character, boxId, boxPosition, frameHeight, boxHeight);
    
    //if (hasOverDrive...)
		encodeBackgrounds(directContent, character, extraBoxTop, extraBoxHeight);
    
    distanceFromTop += remainingHeight;
    encodeLinguistics(directContent, character, distanceFromTop, lastRowHeight);
    encodeExperience(directContent, character, distanceFromTop, lastRowHeight);
    
    
    
    /*float weaponryHeight = encodeWeaponry(directContent, character, distanceFromTop, partEncoder.getWeaponryHeight());
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, character, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = PdfFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, character, distanceFromTop, remainingHeight);*/
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

  private float encodeEssence(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = partEncoder.getEssenceEncoder();
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
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
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
    SecondEditionPersonalInfoEncoder encoder = new SecondEditionPersonalInfoEncoder(baseFont, resources);
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

  private float encodeWillpower(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    boxEncoder.encodeBox(directContent, new PdfWillpowerEncoder(baseFont), character, willpowerBounds);
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
  
  private float encodeOverdrive(
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
	  }

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
  
  private void encodeLinguistics(
		  PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException
  {
	  Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
	  IPdfContentBoxEncoder encoder = registry.getLinguisticsEncoder();
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);	  
  }
  
  private void encodeExperience(
		  PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException
  {
	  Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	  IPdfContentBoxEncoder encoder = new PdfExperienceEncoder(resources, baseFont);
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);	  
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
  
  private void encodeMutations(
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
  }
  
  private void encodeMeritsAndFlaws(
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