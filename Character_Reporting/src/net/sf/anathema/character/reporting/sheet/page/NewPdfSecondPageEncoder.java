package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
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

public class NewPdfSecondPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final BaseFont baseFont;

  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final PdfEncodingRegistry registry;
  private final IPdfPartEncoder partEncoder;

  public NewPdfSecondPageEncoder(
      IPdfPartEncoder partEncoder,
      PdfEncodingRegistry registry,
      IResources resources,
      int essenceMax,
      PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(
      Document document,
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description) throws DocumentException {
    float leftDistanceFromTop = 0;
    float healthHeight = encodeHealth(directContent, character, leftDistanceFromTop, 175);
    leftDistanceFromTop += calculateBoxIncrement(healthHeight);
    float movementHeight = encodeMovement(directContent, character, leftDistanceFromTop, 76);
    leftDistanceFromTop += calculateBoxIncrement(movementHeight);
    float socialCombatHeight = encodeSocialCombatStats(directContent, character, leftDistanceFromTop, 125);
    leftDistanceFromTop += calculateBoxIncrement(socialCombatHeight);

    float rightDistanceFromTop = 0;
    float weaponryHeight = encodeWeaponry(directContent, character, rightDistanceFromTop, 140);
    rightDistanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, rightDistanceFromTop, 111);
    rightDistanceFromTop += calculateBoxIncrement(armourHeight);
    float combatHeight = encodeCombatStats(directContent, character, rightDistanceFromTop, 125);
    rightDistanceFromTop += calculateBoxIncrement(combatHeight);

    float distanceFromTop = Math.max(leftDistanceFromTop, rightDistanceFromTop);
    encodeInventory(directContent, character, distanceFromTop, CONTENT_HEIGHT - distanceFromTop);
    
    /*
    
    encodeMassCombat(directContent, character, distanceFromTop, 120);
    */
    
    encodeCopyright(directContent);
  }

  private void encodeCopyright(PdfContentByte directContent) throws DocumentException {
    int lineHeight = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 2;
    Font copyrightFont = new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    float copyrightHeight = pageConfiguration.getPageHeight() - pageConfiguration.getContentHeight();
    Bounds firstColumnBounds = pageConfiguration.getFirstColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    Anchor voidstatePhrase = new Anchor("Inspired by Voidstate\nhttp://www.voidstate.com", copyrightFont); //$NON-NLS-1$
    voidstatePhrase.setReference("http://www.voidstate.com"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(directContent, voidstatePhrase, firstColumnBounds, lineHeight);
    Anchor anathemaPhrase = new Anchor("Created with Anathema \u00A92007\nhttp://anathema.sf.net", copyrightFont); //$NON-NLS-1$
    anathemaPhrase.setReference("http://anathema.sf.net"); //$NON-NLS-1$
    Bounds anathemaBounds = pageConfiguration.getSecondColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    PdfTextEncodingUtilities.encodeText(directContent, anathemaPhrase, anathemaBounds, lineHeight, Element.ALIGN_CENTER);
    Anchor whitewolfPhrase = new Anchor("Exalted \u00A92007 by White Wolf, Inc.\nhttp://www.white-wolf.com", //$NON-NLS-1$
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

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }
  
  private void encodeInventory(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException
  {
	  Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 3);
	  IPdfContentBoxEncoder encoder = registry.getPossessionsEncoder();
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);	  
  }
  
  private float encodeMassCombat(
	      PdfContentByte directContent,
	      IGenericCharacter character,
	      float distanceFromTop,
	      float height) throws DocumentException
  {
	  Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
	  IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(1, "MassCombat");
	  boxEncoder.encodeBox(directContent, encoder, character, bounds);
	  return height;
  }

  private float encodeArmourAndSoak(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder contentEncoder = registry.getArmourContentEncoder();
    boxEncoder.encodeBox(directContent, contentEncoder, character, bounds);
    return height;
  }

  private float encodeSocialCombatStats(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = partEncoder.getSocialCombatEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeCombatStats(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = partEncoder.getCombatStatsEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeHealth(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = partEncoder.getHealthEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeMovement(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = partEncoder.getMovementEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeWeaponry(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder weaponryEncoder = registry.getWeaponContentEncoder();
    boxEncoder.encodeBox(directContent, weaponryEncoder, character, bounds);
    return height;
  }
}