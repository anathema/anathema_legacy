package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class NewPdfSecondPageEncoder extends AbstractPdfPageEncoder {

  public NewPdfSecondPageEncoder(IPdfPartEncoder partEncoder,
                                 PdfEncodingRegistry registry,
                                 IResources resources, int essenceMax,
                                 PdfPageConfiguration pageConfiguration) {
    super(partEncoder, registry, resources, pageConfiguration);
  }

  public void encode(Document document, PdfContentByte directContent,
                     IGenericCharacter character,
                     IGenericDescription description) throws DocumentException {
    // Left column (top-down)
    float leftDistanceFromTop = 0;
    float healthHeight = encodeHealth(directContent, character,
                                      description, leftDistanceFromTop, 175);
    leftDistanceFromTop += calculateBoxIncrement(healthHeight);
    float movementHeight = encodeMovement(directContent, character,
                                          description, leftDistanceFromTop, 76);
    leftDistanceFromTop += calculateBoxIncrement(movementHeight);
    float socialCombatHeight = encodeSocialCombatStats(directContent, character,
                                                       description, leftDistanceFromTop, 125);
    leftDistanceFromTop += calculateBoxIncrement(socialCombatHeight);

    // Right columns (top-down)
    float rightDistanceFromTop = 0;
    float weaponryHeight = encodeWeaponry(directContent, character,
                                          description, rightDistanceFromTop, 140);
    rightDistanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character,
                                             description, rightDistanceFromTop, 111);
    rightDistanceFromTop += calculateBoxIncrement(armourHeight);
    float combatHeight = encodeCombatStats(directContent, character,
                                           description, rightDistanceFromTop, 125);
    rightDistanceFromTop += calculateBoxIncrement(combatHeight);

    // Fill in remaining space with inventory
    float distanceFromTop = Math.max(leftDistanceFromTop, rightDistanceFromTop);
    encodeInventory(directContent, character,
                    description, distanceFromTop, CONTENT_HEIGHT - distanceFromTop);

    encodeCopyright(directContent);
  }

  private float encodeInventory(PdfContentByte directContent,
                                IGenericCharacter character, IGenericDescription description,
                                float distanceFromTop, float height)
      throws DocumentException {
    return encodeFixedBox(directContent, character, description,
                          getRegistry().getPossessionsEncoder(),
                          1, 3, distanceFromTop, height);
  }

  private float encodeArmourAndSoak(PdfContentByte directContent,
                                    IGenericCharacter character, IGenericDescription description,
                                    float distanceFromTop, float height)
      throws DocumentException {
    return encodeFixedBox(directContent, character, description,
                          getRegistry().getArmourContentEncoder(),
                          2, 2, distanceFromTop, height);
  }

  private float encodeSocialCombatStats(PdfContentByte directContent,
                                        IGenericCharacter character, IGenericDescription description,
                                        float distanceFromTop, float height)
      throws DocumentException {
    return encodeFixedBox(directContent, character, description,
                          getPartEncoder().getSocialCombatEncoder(),
                          1, 1, distanceFromTop, height);
  }

  private float encodeCombatStats(PdfContentByte directContent,
                                  IGenericCharacter character, IGenericDescription description,
                                  float distanceFromTop, float height)
      throws DocumentException {
    return encodeFixedBox(directContent, character, description,
                          getPartEncoder().getCombatStatsEncoder(),
                          2, 2, distanceFromTop, height);
  }

  private float encodeHealth(PdfContentByte directContent,
                             IGenericCharacter character, IGenericDescription description,
                             float distanceFromTop, float height)
      throws DocumentException {
    return encodeFixedBox(directContent, character, description,
                          getPartEncoder().getHealthEncoder(),
                          1, 1, distanceFromTop, height);
  }

  private float encodeMovement(PdfContentByte directContent,
                               IGenericCharacter character, IGenericDescription description,
                               float distanceFromTop, float height)
      throws DocumentException {
    return encodeFixedBox(directContent, character, description,
                          getPartEncoder().getMovementEncoder(),
                          1, 1, distanceFromTop, height);
  }

  private float encodeWeaponry(PdfContentByte directContent,
                               IGenericCharacter character, IGenericDescription description,
                               float distanceFromTop, float height)
      throws DocumentException {
    return encodeFixedBox(directContent, character, description,
                          getRegistry().getWeaponContentEncoder(),
                          2, 2, distanceFromTop, height);
  }
}