package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

public class NewPdfSecondPageEncoder extends AbstractPdfPageEncoder {

  public NewPdfSecondPageEncoder(IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry, IResources resources, int essenceMax,
    PdfPageConfiguration pageConfiguration) {
    super(partEncoder, registry, resources, pageConfiguration);
  }

  public void encode(Document document, PdfContentByte directContent, ReportContent content) throws DocumentException {
    // Left column (top-down)
    float leftDistanceFromTop = 0;
    float healthHeight = encodeHealth(directContent, content, leftDistanceFromTop, 175);
    leftDistanceFromTop += calculateBoxIncrement(healthHeight);
    float movementHeight = encodeMovement(directContent, content, leftDistanceFromTop, 76);
    leftDistanceFromTop += calculateBoxIncrement(movementHeight);
    float socialCombatHeight = encodeSocialCombatStats(directContent, content, leftDistanceFromTop, 125);
    leftDistanceFromTop += calculateBoxIncrement(socialCombatHeight);

    // Right columns (top-down)
    float rightDistanceFromTop = 0;
    float weaponryHeight = encodeWeaponry(directContent, content, rightDistanceFromTop, 140);
    rightDistanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, content, rightDistanceFromTop, 111);
    rightDistanceFromTop += calculateBoxIncrement(armourHeight);
    float combatHeight = encodeCombatStats(directContent, content, rightDistanceFromTop, 125);
    rightDistanceFromTop += calculateBoxIncrement(combatHeight);

    // Fill in remaining space with inventory
    float distanceFromTop = Math.max(leftDistanceFromTop, rightDistanceFromTop);
    encodeInventory(directContent, content, distanceFromTop, getContentHeight() - distanceFromTop);

    encodeCopyright(directContent);
  }

  private float encodeInventory(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getRegistry().getPossessionsEncoder(), 1, 3, distanceFromTop, height);
  }

  private float encodeArmourAndSoak(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getRegistry().getArmourContentEncoder(), 2, 2, distanceFromTop, height);
  }

  private float encodeSocialCombatStats(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getPartEncoder().getSocialCombatEncoder(), 1, 1, distanceFromTop, height);
  }

  private float encodeCombatStats(PdfContentByte directContent, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getPartEncoder().getCombatStatsEncoder(), 2, 2, distanceFromTop, height);
  }

  private float encodeHealth(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getPartEncoder().getHealthEncoder(), 1, 1, distanceFromTop, height);
  }

  private float encodeMovement(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getPartEncoder().getMovementEncoder(), 1, 1, distanceFromTop, height);
  }

  private float encodeWeaponry(PdfContentByte directContent, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(directContent, content, getRegistry().getWeaponContentEncoder(), 2, 2, distanceFromTop, height);
  }
}
