package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.util.Bounds;
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
                                      leftDistanceFromTop, 175);
    leftDistanceFromTop += calculateBoxIncrement(healthHeight);
    float movementHeight = encodeMovement(directContent, character,
                                          leftDistanceFromTop, 76);
    leftDistanceFromTop += calculateBoxIncrement(movementHeight);
    float socialCombatHeight = encodeSocialCombatStats(directContent, character,
                                                       leftDistanceFromTop, 125);
    leftDistanceFromTop += calculateBoxIncrement(socialCombatHeight);

    // Right columns (top-down)
    float rightDistanceFromTop = 0;
    float weaponryHeight = encodeWeaponry(directContent, character,
                                          rightDistanceFromTop, 140);
    rightDistanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character,
                                             rightDistanceFromTop, 111);
    rightDistanceFromTop += calculateBoxIncrement(armourHeight);
    float combatHeight = encodeCombatStats(directContent, character,
                                           rightDistanceFromTop, 125);
    rightDistanceFromTop += calculateBoxIncrement(combatHeight);

    // Fill in remaining space with inventory
    float distanceFromTop = Math.max(leftDistanceFromTop, rightDistanceFromTop);
    encodeInventory(directContent, character,
                    distanceFromTop, CONTENT_HEIGHT - distanceFromTop);

    encodeCopyright(directContent);
  }

  private void encodeInventory(PdfContentByte directContent,
                               IGenericCharacter character,
                               float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(1, 3, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getRegistry().getPossessionsEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
  }

  private float encodeArmourAndSoak(PdfContentByte directContent,
                                    IGenericCharacter character,
                                    float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(2, 2, distanceFromTop, height);
    IPdfContentBoxEncoder contentEncoder = getRegistry().getArmourContentEncoder();
    getBoxEncoder().encodeBox(directContent, contentEncoder, character, bounds);
    return height;
  }

  private float encodeSocialCombatStats(PdfContentByte directContent,
                                        IGenericCharacter character,
                                        float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(1, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getPartEncoder().getSocialCombatEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeCombatStats(PdfContentByte directContent,
                                  IGenericCharacter character,
                                  float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(2, 2, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getPartEncoder().getCombatStatsEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeHealth(PdfContentByte directContent,
                             IGenericCharacter character,
                             float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(1, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getPartEncoder().getHealthEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeMovement(PdfContentByte directContent,
                               IGenericCharacter character,
                               float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(1, 1, distanceFromTop, height);
    IPdfContentBoxEncoder encoder = getPartEncoder().getMovementEncoder();
    getBoxEncoder().encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeWeaponry(PdfContentByte directContent,
                               IGenericCharacter character,
                               float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = calculateBounds(2, 2, distanceFromTop, height);
    IPdfContentBoxEncoder weaponryEncoder = getRegistry().getWeaponContentEncoder();
    getBoxEncoder().encodeBox(directContent, weaponryEncoder, character, bounds);
    return height;
  }
}