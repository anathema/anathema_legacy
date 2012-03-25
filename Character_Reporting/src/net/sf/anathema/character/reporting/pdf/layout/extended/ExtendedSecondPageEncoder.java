package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;

public class ExtendedSecondPageEncoder extends AbstractExtendedPdfPageEncoder {

  private EncoderRegistry encoderRegistry;

  public ExtendedSecondPageEncoder(EncoderRegistry encoderRegistry, IExtendedPartEncoder partEncoder, IResources resources, PageConfiguration pageConfiguration) {
    super(partEncoder, resources, pageConfiguration);
    this.encoderRegistry = encoderRegistry;
  }

  public void encode(Document document, SheetGraphics graphics, ReportSession session) throws DocumentException {
    // Left column (top-down)
    float leftDistanceFromTop = 0;
    float healthHeight = encodeHealth(graphics, session, leftDistanceFromTop, 175);
    leftDistanceFromTop += calculateBoxIncrement(healthHeight);
    float movementHeight = encodeMovement(graphics, session, leftDistanceFromTop, 76);
    leftDistanceFromTop += calculateBoxIncrement(movementHeight);
    float socialCombatHeight = encodeSocialCombatStats(graphics, session, leftDistanceFromTop, 125);
    leftDistanceFromTop += calculateBoxIncrement(socialCombatHeight);

    // Right columns (top-down)
    float rightDistanceFromTop = 0;
    float weaponryHeight = encodeWeaponry(graphics, session, rightDistanceFromTop, 140);
    rightDistanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(graphics, session, rightDistanceFromTop, 111);
    rightDistanceFromTop += calculateBoxIncrement(armourHeight);
    float combatHeight = encodeCombatStats(graphics, session, rightDistanceFromTop, 125);
    rightDistanceFromTop += calculateBoxIncrement(combatHeight);

    // Fill in remaining space with inventory
    float distanceFromTop = Math.max(leftDistanceFromTop, rightDistanceFromTop);
    encodeInventory(graphics, session, distanceFromTop, getContentHeight() - distanceFromTop);

    encodeCopyright(graphics);
  }

  private float encodeInventory(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder possessionsEncoder = encoderRegistry.createEncoder(getResources(), session, EncoderIds.POSSESSIONS);
    return encodeFixedBox(graphics, session, possessionsEncoder, 1, 3, distanceFromTop, height);
  }

  private float encodeArmourAndSoak(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder armourContentEncoder = encoderRegistry.createEncoder(getResources(), session, EncoderIds.PANOPLY);
    return encodeFixedBox(graphics, session, armourContentEncoder, 2, 2, distanceFromTop, height);
  }

  private float encodeSocialCombatStats(SheetGraphics graphics, ReportSession session, float distanceFromTop,
                                        float height) throws DocumentException {
    ContentEncoder encoder = encoderRegistry.createEncoder(getResources(), session, EncoderIds.SOCIAL_COMBAT, EncoderIds.MERITS_AND_FLAWS);
    return encodeFixedBox(graphics, session, encoder, 1, 1, distanceFromTop, height);
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = encoderRegistry.createEncoder(getResources(), session, EncoderIds.COMBAT);
    return encodeFixedBox(graphics, session, encoder, 2, 2, distanceFromTop, height);
  }

  private float encodeHealth(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, getPartEncoder().getHealthEncoder(), 1, 1, distanceFromTop, height);
  }

  private float encodeMovement(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, session, getPartEncoder().getMovementEncoder(), 1, 1, distanceFromTop, height);
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportSession session, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder weaponryEncoder = encoderRegistry.createEncoder(getResources(), session, ARSENAL);
    return encodeFixedBox(graphics, session, weaponryEncoder, 2, 2, distanceFromTop, height);
  }
}
