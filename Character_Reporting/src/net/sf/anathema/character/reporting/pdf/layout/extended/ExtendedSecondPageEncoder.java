package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.EncoderIds.ARSENAL;

public class ExtendedSecondPageEncoder extends AbstractPdfPageEncoder {

  private EncoderRegistry encoderRegistry;

  public ExtendedSecondPageEncoder(EncoderRegistry encoderRegistry, IExtendedPartEncoder partEncoder, ExtendedEncodingRegistry registry,
                                   IResources resources, PageConfiguration pageConfiguration) {
    super(partEncoder, registry, resources, pageConfiguration);
    this.encoderRegistry = encoderRegistry;
  }

  public void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException {
    // Left column (top-down)
    float leftDistanceFromTop = 0;
    float healthHeight = encodeHealth(graphics, content, leftDistanceFromTop, 175);
    leftDistanceFromTop += calculateBoxIncrement(healthHeight);
    float movementHeight = encodeMovement(graphics, content, leftDistanceFromTop, 76);
    leftDistanceFromTop += calculateBoxIncrement(movementHeight);
    float socialCombatHeight = encodeSocialCombatStats(graphics, content, leftDistanceFromTop, 125);
    leftDistanceFromTop += calculateBoxIncrement(socialCombatHeight);

    // Right columns (top-down)
    float rightDistanceFromTop = 0;
    float weaponryHeight = encodeWeaponry(graphics, content, rightDistanceFromTop, 140);
    rightDistanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(graphics, content, rightDistanceFromTop, 111);
    rightDistanceFromTop += calculateBoxIncrement(armourHeight);
    float combatHeight = encodeCombatStats(graphics, content, rightDistanceFromTop, 125);
    rightDistanceFromTop += calculateBoxIncrement(combatHeight);

    // Fill in remaining space with inventory
    float distanceFromTop = Math.max(leftDistanceFromTop, rightDistanceFromTop);
    encodeInventory(graphics, content, distanceFromTop, getContentHeight() - distanceFromTop);

    encodeCopyright(graphics);
  }

  private float encodeInventory(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder possessionsEncoder = encoderRegistry.createEncoder(getResources(), content, EncoderIds.POSSESSIONS);
    return encodeFixedBox(graphics, content, possessionsEncoder, 1, 3, distanceFromTop, height);
  }

  private float encodeArmourAndSoak(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder armourContentEncoder = encoderRegistry.createEncoder(getResources(), content, EncoderIds.PANOPLY);
    return encodeFixedBox(graphics, content, armourContentEncoder, 2, 2, distanceFromTop, height);
  }

  private float encodeSocialCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop,
    float height) throws DocumentException {
    ContentEncoder encoder = encoderRegistry.createEncoder(getResources(), content, EncoderIds.SOCIAL_COMBAT, EncoderIds.MERITS_AND_FLAWS);
    return encodeFixedBox(graphics, content, encoder, 1, 1, distanceFromTop, height);
  }

  private float encodeCombatStats(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder encoder = encoderRegistry.createEncoder(getResources(), content, EncoderIds.COMBAT);
    return encodeFixedBox(graphics, content, encoder, 2, 2, distanceFromTop, height);
  }

  private float encodeHealth(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, getPartEncoder().getHealthEncoder(), 1, 1, distanceFromTop, height);
  }

  private float encodeMovement(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    return encodeFixedBox(graphics, content, getPartEncoder().getMovementEncoder(), 1, 1, distanceFromTop, height);
  }

  private float encodeWeaponry(SheetGraphics graphics, ReportContent content, float distanceFromTop, float height) throws DocumentException {
    ContentEncoder weaponryEncoder = encoderRegistry.createEncoder(getResources(), content, ARSENAL);
    return encodeFixedBox(graphics, content, weaponryEncoder, 2, 2, distanceFromTop, height);
  }
}
