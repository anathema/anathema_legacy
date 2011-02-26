package net.sf.anathema.character.lunar.reporting;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.equipment.impl.reporting.ArmourEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.lunar.reporting.heartsblood.LunarHeartsBloodEncoder;
import net.sf.anathema.character.lunar.reporting.knacks.KnackEncoder;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfAbilitiesEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfVirtueEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfWillpowerEncoder;
import net.sf.anathema.character.reporting.sheet.common.magic.PdfMagicEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.second.SecondEditionPersonalInfoEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfHeaderBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionLunarAdditionalPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  private static final int ANIMA_HEIGHT = 128;
  private static final int VIRTUE_HEIGHT = 72;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final PdfEncodingRegistry registry;
  private final IPdfPartEncoder partEncoder;

  public SecondEditionLunarAdditionalPageEncoder(
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
      IGenericDescription description) throws DocumentException
      {
	  int firstSet = 0, secondSet = 0;
	  firstSet += encodeSpiritForms(directContent, character, firstSet) + PADDING;
	  secondSet = firstSet;
	  firstSet += encodeArsenel(directContent, character, firstSet,partEncoder.getWeaponryHeight()) + PADDING;
	  firstSet += encodePanopoly(directContent, character, firstSet, 80) + PADDING;
	  firstSet += encodeMovementAndHealth(directContent, character, firstSet, 99);
	  
	  secondSet += encodeCombatStats(directContent, character, secondSet) + PADDING;
	  encodeShapeshifting(directContent, character, secondSet, firstSet - secondSet);
	  
	  firstSet += PADDING;
	  
	  firstSet += encodeKnacks(directContent, character, firstSet) + PADDING;
	  
	  encodeAnimalForms(directContent, character, firstSet, CONTENT_HEIGHT - firstSet);
	  
    /*int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(directContent, character, description, distanceFromTop, firstRowHeight);
    encodeEssence(directContent, character, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    int abilityStartHeight = encodeAttributes(directContent, character, distanceFromTop) + distanceFromTop + PADDING;
    encodeAnima(directContent, character, distanceFromTop, ANIMA_HEIGHT);
    float freeSpace = getOverlapFreeSpaceHeight();
    distanceFromTop += freeSpace;
    distanceFromTop += PADDING;
    encodeVirtues(directContent, distanceFromTop, VIRTUE_HEIGHT, character);
    distanceFromTop += calculateBoxIncrement(VIRTUE_HEIGHT);

    float animalFormHeight = encodeAnimalForms(directContent, character, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, character, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float weaponryHeight = encodeWeaponry(directContent, character, distanceFromTop, partEncoder.getWeaponryHeight());
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, character, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = SecondEditionLunarAdditionalPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, character, distanceFromTop, remainingHeight);
    encodeAbilities(directContent, character, abilityStartHeight, (int) remainingHeight + PADDING);
    encodeGifts(directContent, character, distanceFromTop, remainingHeight);*/
  }
  
  private int encodeSpiritForms(PdfContentByte directContent,
		  IGenericCharacter character,
		  int distanceFromTop)  throws DocumentException
  {
	  int attributeHeight = 70;
      float smallWidth = pageConfiguration.getColumnWidth();
      Bounds spiritBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
	  Bounds beastBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, attributeHeight, 2);
	  SecondEditionLunarSpiritFormEncoder spiritEncoder = new SecondEditionLunarSpiritFormEncoder(
		        baseFont,
		        resources,
		        boxEncoder.calculateInsettedWidth(smallWidth));
	  SecondEditionLunarDBTFormEncoder beastEncoder = new SecondEditionLunarDBTFormEncoder(
		        baseFont,
		        resources,
		        boxEncoder.calculateInsettedWidth(smallWidth));
	  boxEncoder.encodeBox(directContent, spiritEncoder, character, spiritBounds);
      boxEncoder.encodeBox(directContent, beastEncoder, character, beastBounds);
      return attributeHeight;
  }
  
  private float encodeArsenel(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
	    IPdfContentBoxEncoder weaponryEncoder = registry.getWeaponContentEncoder();
	    boxEncoder.encodeBox(directContent, weaponryEncoder, character, bounds);
	    return height;
	  }
  
  private float encodePanopoly(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
	    IPdfContentBoxEncoder contentEncoder = new ArmourEncoder(resources, baseFont, new LunarArmourTableEncoder(
	        baseFont,
	        resources));
	    boxEncoder.encodeBox(directContent, contentEncoder, character, bounds);
	    return height;
	  }
  
  private float encodeMovementAndHealth(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
	    IPdfContentBoxEncoder encoder = partEncoder.getHealthAndMovementEncoder();
	    boxEncoder.encodeBox(directContent, encoder, character, bounds);
	    return height;
	  }
  
  private float encodeCombatStats(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop) throws DocumentException {
	    int height = 64;
	    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	    SecondEditionDBTCombatEncoder encoder = new SecondEditionDBTCombatEncoder(resources, baseFont);
	    boxEncoder.encodeBox(directContent, encoder, character, bounds);
	    return height;
	  }
  
  private void encodeShapeshifting(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	    SecondEditionShapeshiftingEncoder encoder = new SecondEditionShapeshiftingEncoder(resources, baseFont);
	    boxEncoder.encodeBox(directContent, encoder, character, bounds);
	  }
  
  private float encodeKnacks(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop) throws DocumentException {
	  int height = 240;
	  Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 3);
	    IPdfContentBoxEncoder encoder = new KnackEncoder(resources, baseFont);
	    boxEncoder.encodeBox(directContent, encoder, character, bounds);
	    return height;
	  }
  
  private float encodeAnimalForms(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 3);
	    IPdfContentBoxEncoder encoder = new LunarHeartsBloodEncoder(baseFont, resources);
	    boxEncoder.encodeBox(directContent, encoder, character, bounds);
	    return height;
	  }
}