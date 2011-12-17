package net.sf.anathema.character.reporting.extended.page;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfPageEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.extended.ExtendedEncodingRegistry;
import net.sf.anathema.character.reporting.extended.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.extended.util.PdfBoxEncoder;
import net.sf.anathema.lib.resources.IResources;

public class PdfSecondPageEncoder implements IPdfPageEncoder {
  public static final int CONTENT_HEIGHT = 755;
  private final BaseFont baseFont;

  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final ExtendedEncodingRegistry registry;
  private final IExtendedPartEncoder partEncoder;

  public PdfSecondPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry, IResources resources, int essenceMax,
                              PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.baseFont = registry.getBaseFont();
    this.registry = registry;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(Document document, PdfContentByte directContent, IGenericCharacter character, IGenericDescription description) throws








































                                                                                                                                    DocumentException {
    int distanceFromTop = 0;

    /*   encodeFirstColumn(directContent, character, distanceFromTop);
  encodeAnima(directContent, character, distanceFromTop, ANIMA_HEIGHT);
  float virtueHeight = encodeVirtues(directContent, distanceFromTop, 72, character);
  distanceFromTop += calculateBoxIncrement(virtueHeight);
  float greatCurseHeigth = ANIMA_HEIGHT - virtueHeight - IVoidStateFormatConstants.PADDING;
  encodeGreatCurse(directContent, character, distanceFromTop, greatCurseHeigth);
  distanceFromTop += calculateBoxIncrement(greatCurseHeigth);*/

    float healthHeight = encodeMovementAndHealth(directContent, character, description, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float socialCombatHeight = encodeSocialCombatStats(directContent, character, description, distanceFromTop, 125);
    encodeCombatStats(directContent, character, description, distanceFromTop, socialCombatHeight);

    /*    float willpowerHeight = encodeWillpower(directContent, character, distanceFromTop, 43);
float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
distanceFromTop += willpowerIncrement;
float intimaciesHeight = encodeIntimacies(directContent, character, distanceFromTop, socialCombatHeight
   - willpowerIncrement);*/
    distanceFromTop += calculateBoxIncrement(socialCombatHeight);

    encodeInventory(directContent, character, description, distanceFromTop, CONTENT_HEIGHT - distanceFromTop);

    float weaponryHeight = encodeWeaponry(directContent, character, description, distanceFromTop, 120);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, description, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    encodeMassCombat(directContent, character, description, distanceFromTop, 120);

    new CopyrightEncoder(baseFont, pageConfiguration, CONTENT_HEIGHT).encodeCopyright(directContent);
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  private void encodeInventory(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, int height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = registry.getPossessionsEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
  }

  private float encodeMassCombat(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, int height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(1, "MassCombat");
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeArmourAndSoak(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder contentEncoder = registry.getArmourContentEncoder();
    boxEncoder.encodeBox(directContent, contentEncoder, character, description, bounds);
    return height;
  }

  private float encodeSocialCombatStats(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = partEncoder.getSocialCombatEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeCombatStats(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = partEncoder.getCombatStatsEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeMovementAndHealth(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, height, 3);
    IPdfContentBoxEncoder encoder = partEncoder.getHealthAndMovementEncoder();
    boxEncoder.encodeBox(directContent, encoder, character, description, bounds);
    return height;
  }

  private float encodeWeaponry(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, float distanceFromTop, float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder weaponryEncoder = registry.getWeaponContentEncoder();
    boxEncoder.encodeBox(directContent, weaponryEncoder, character, description, bounds);
    return height;
  }
}
