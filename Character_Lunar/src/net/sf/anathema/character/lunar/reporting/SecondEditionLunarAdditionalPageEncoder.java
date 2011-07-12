package net.sf.anathema.character.lunar.reporting;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
import net.sf.anathema.character.equipment.impl.reporting.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryEncoder;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.lunar.reporting.heartsblood.SecondEditionLunarHeartsBloodEncoder;
import net.sf.anathema.character.lunar.reporting.knacks.KnackEncoder;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.sheet.page.IPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionLunarAdditionalPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final BaseFont baseFont;
  private final BaseFont symbolFont;

  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final IPdfPartEncoder partEncoder;

  public SecondEditionLunarAdditionalPageEncoder(
      IPdfPartEncoder partEncoder,
      PdfEncodingRegistry registry,
      IResources resources,
      int essenceMax,
      PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.symbolFont = registry.getSymbolBaseFont();
    this.resources = resources;
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
	  boolean DBT = hasDBT(character);
	  firstSet += encodeSpiritForms(directContent, character, description, firstSet, DBT);
	  if (DBT)
	  {
		  firstSet += PADDING;
		  secondSet = firstSet;
		  firstSet += encodeArsenel(directContent, character, description,firstSet, partEncoder.getWeaponryHeight()) + PADDING;
		  firstSet += encodePanopoly(directContent, character, description, firstSet, 80) + PADDING;
		  firstSet += encodeMovementAndHealth(directContent, character, description, firstSet, 99);
		  
		  secondSet += encodeCombatStats(directContent, character, description, secondSet) + PADDING;
		  encodePowers(directContent, character, description, secondSet, firstSet - secondSet, false);
	  }
	  else
		  encodePowers(directContent, character, description, secondSet, firstSet, true);
	  
	  firstSet += PADDING;
	  
	  int remaining = (int) (pageConfiguration.getContentHeight() - firstSet);
	  encodeKnacks(directContent, character, description, firstSet, remaining);
	  encodeAnimalForms(directContent, character, description, firstSet, remaining);
  }
  
  private boolean hasDBT(IGenericCharacter character)
  {
	  for (IMagic magic : character.getAllLearnedMagic())
		  if (magic instanceof ICharm &&
			  ((ICharm)magic).getId().equals("Lunar.DeadlyBeastmanTransformation"))
			  return true;
	  return false;
  }
  
  private int encodeSpiritForms(PdfContentByte directContent,
		  IGenericCharacter character,
		  IGenericDescription description,
		  int distanceFromTop, boolean DBT)  throws DocumentException
  {
	  int attributeHeight = 80;
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
	  boxEncoder.encodeBox(directContent, spiritEncoder, character, description, spiritBounds);
	  if (DBT)
		  boxEncoder.encodeBox(directContent, beastEncoder, character, description, beastBounds);
      return attributeHeight;
  }
  
  private float encodeArsenel(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      IGenericDescription description,
	      float distanceFromTop, float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
	    IPdfContentBoxEncoder weaponryEncoder = new WeaponryEncoder(resources, baseFont,
	    		new LunarWeaponTableEncoder(baseFont, resources, character));
	    boxEncoder.encodeBox(directContent, weaponryEncoder, character, description, bounds);
	    return height;
	  }
  
  private float encodePanopoly(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      IGenericDescription description,
	      float distanceFromTop, float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
	    IPdfContentBoxEncoder contentEncoder = new ArmourEncoder(resources, baseFont, new LunarArmourTableEncoder(
	        baseFont,
	        resources));
	    boxEncoder.encodeBox(directContent, contentEncoder, character, description, bounds);
	    return height;
	  }
  
  private float encodeMovementAndHealth(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      IGenericDescription description,
	      float distanceFromTop, float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 2);
	    IPdfContentBoxEncoder encoder = new SecondEditionLunarHealthAndMovementEncoder(
	    		resources, baseFont, symbolFont, character);
	    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
	    return height;
	  }
  
  private float encodeCombatStats(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      IGenericDescription description, float distanceFromTop) throws DocumentException {
	    int height = 64;
	    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	    SecondEditionDBTCombatEncoder encoder = new SecondEditionDBTCombatEncoder(resources, baseFont);
    	boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
	    return height;
	  }
  
  private void encodePowers(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      IGenericDescription description,
	      float distanceFromTop,
	      float height, boolean isHorizontal) throws DocumentException {
	    Bounds bounds = isHorizontal ?
	    		pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2) :
	    		pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
	    SecondEditionPowersEncoder encoder = new SecondEditionPowersEncoder(resources, baseFont, isHorizontal);
	    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
	  }
  
  private void encodeKnacks(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      IGenericDescription description,
	      float distanceFromTop, int height) throws DocumentException {
	  Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
	    IPdfContentBoxEncoder encoder = new KnackEncoder(resources, baseFont);
	    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
	  }
  
  private void encodeAnimalForms(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      IGenericDescription description,
	      float distanceFromTop, float height) throws DocumentException {
	    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
	    IPdfContentBoxEncoder encoder = new SecondEditionLunarHeartsBloodEncoder(baseFont, resources);
	    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
	  }
}