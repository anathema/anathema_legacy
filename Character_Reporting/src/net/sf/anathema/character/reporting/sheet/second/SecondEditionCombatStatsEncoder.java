package net.sf.anathema.character.reporting.sheet.second;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.CharacterUtiltiies;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.elements.Box;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionCombatStatsEncoder extends AbstractPdfEncoder implements IPdfContentEncoder {

  private final static float BOX_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final static float BOX_WIDTH = 12;
  private final static float PADDING = 3;

  private final IResources resources;
  private final BaseFont baseFont;

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public SecondEditionCombatStatsEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    float firstColumnRightX = bounds.x + bounds.width / 4;
    float secondColumnRightX = bounds.getCenterX();
    float thirdColumnRightX = bounds.x + bounds.width * 3 / 4;
    float fourthColumnRightX = bounds.getMaxX();
    float yPosition = encodeValues(
        directContent,
        character,
        firstColumnRightX,
        secondColumnRightX,
        thirdColumnRightX,
        fourthColumnRightX,
        bounds.getMaxY());
        Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - (bounds.getMaxY() - yPosition) - PADDING);
        encodeRules(directContent, character, ruleBounds);
  }

  private float encodeValues(
      PdfContentByte directContent,
      IGenericCharacter character,
      float firstColumnRightX,
      float secondColumnRightX,
      float thirdColumnRightX,
      float fourthColumnRightX,
      float yPosition) {
    yPosition -= PADDING;
    String joinLabel = resources.getString("Sheet.Combat.JoinBattle"); //$NON-NLS-1$
    String dodgeLabel = resources.getString("Sheet.Combat.DodgeDV"); //$NON-NLS-1$
    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
    String stunningLabel = resources.getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
    int joinBattle = CharacterUtiltiies.getTotalValue(character, AttributeType.Wits, AbilityType.Awareness);
    int dodgeDV = calculateDodgeDV(character);
    int knockdownThreshold = CharacterUtiltiies.getTotalValue(character, AttributeType.Stamina, AbilityType.Resistance);
    int knockdownPool = CharacterUtiltiies.getKnockdownPool(character);
    int stunningThreshold = CharacterUtiltiies.getTotalValue(character, AttributeType.Stamina);
    int stunningPool = CharacterUtiltiies.getTotalValue(character, AttributeType.Stamina, AbilityType.Resistance);
    float baseLine = yPosition - BOX_HEIGHT;
    encodeLabelledValue(directContent, firstColumnRightX, baseLine, joinLabel, joinBattle);
    encodeLabelledValue(directContent, secondColumnRightX, baseLine, dodgeLabel, dodgeDV);
    encodeLabelledValue(directContent, thirdColumnRightX, baseLine, knockdownLabel, knockdownThreshold, knockdownPool);
    encodeLabelledValue(directContent, fourthColumnRightX, baseLine, stunningLabel, stunningThreshold, stunningPool);
    yPosition -= BOX_HEIGHT + PADDING;
    yPosition -= IVoidStateFormatConstants.COMMENT_FONT_SIZE;
    String mobilityPenaltyLabel = "-" + resources.getString("Sheet.Combat.MobilityPenalty"); //$NON-NLS-1$ //$NON-NLS-2$
    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
    drawComment(directContent, mobilityPenaltyLabel, new Position(secondColumnRightX, yPosition), Element.ALIGN_RIGHT);
    drawComment(directContent, thresholdPoolLabel, new Position(fourthColumnRightX, yPosition + 1), Element.ALIGN_RIGHT);
    drawComment(directContent, thresholdPoolLabel, new Position(thirdColumnRightX, yPosition + 1), Element.ALIGN_RIGHT);
    return yPosition;
  }

  private void encodeRules(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException {
    new SecondEditionCombatRulesTableEncoder(resources, baseFont).encodeTable(directContent, character, bounds);
  }

  private int calculateDodgeDV(IGenericCharacter character) {
    return CharacterUtiltiies.getDvValue(character, AttributeType.Dexterity, AbilityType.Dodge, OtherTraitType.Essence);
  }

  private void encodeLabelledValue(
      PdfContentByte directContent,
      float rightX,
      float baseLine,
      String text,
      int... values) {
    float allBoxesWidth = BOX_WIDTH * values.length + (values.length - 1) * PADDING;
    Position textPosition = new Position(rightX - allBoxesWidth - PADDING, baseLine);
    drawText(directContent, text, textPosition, Element.ALIGN_RIGHT);
    for (int index = 0; index < values.length; index++) {
      float boxX = rightX - allBoxesWidth + (BOX_WIDTH + PADDING) * index;
      Bounds boxBounds = new Bounds(boxX, textPosition.y - 2, BOX_WIDTH, BOX_HEIGHT);
      new Box(boxBounds).encodeTotalType(directContent);
      Position valuePosition = new Position(boxBounds.getCenterX(), textPosition.getY());
      drawText(directContent, String.valueOf(values[index]), valuePosition, Element.ALIGN_CENTER);
    }
  }
}