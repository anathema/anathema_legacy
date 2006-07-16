package net.sf.anathema.character.reporting.sheet.first;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.util.LabelledValueEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class FirstEditionCombatValueEncoder implements IPdfContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public FirstEditionCombatValueEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public float encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    String initiativeLabel = resources.getString("Sheet.Combat.BaseInitiative"); //$NON-NLS-1$
    String dodgePoolLabel = resources.getString("Sheet.Combat.DodgePool"); //$NON-NLS-1$
    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
    String stunningLabel = resources.getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
    int initiative = CharacterUtilties.getTotalValue(character, AttributeType.Dexterity, AttributeType.Wits);
    int dodgePool = CharacterUtilties.getDodgePool(character);
    int knockdownThreshold = CharacterUtilties.getTotalValue(character, AttributeType.Stamina, AbilityType.Resistance);
    int knockdownPool = CharacterUtilties.getKnockdownPool(character);
    int stunningThreshold = CharacterUtilties.getTotalValue(character, AttributeType.Stamina);
    int stunningPool = CharacterUtilties.getTotalValue(character, AttributeType.Stamina, AbilityType.Resistance);
    int stunningDuration = Math.max(0, 6 - stunningThreshold);

    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(baseFont, 4, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(directContent, 0, initiativeLabel, initiative);
    encoder.addLabelledValue(directContent, 1, dodgePoolLabel, dodgePool);
    encoder.addLabelledValue(directContent, 2, knockdownLabel, knockdownThreshold, knockdownPool);
    encoder.addLabelledValue(directContent, 3, stunningLabel, stunningThreshold, stunningPool, stunningDuration);
    String mobilityPenaltyLabel = "-" + resources.getString("Sheet.Combat.MobilityPenalty"); //$NON-NLS-1$ //$NON-NLS-2$
    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
    String thresholdPoolDurationLabel = resources.getString("Sheet.Combat.ThresholdPoolDuration"); //$NON-NLS-1$
    encoder.addComment(directContent, mobilityPenaltyLabel, 1);
    encoder.addComment(directContent, thresholdPoolLabel, 2);
    encoder.addComment(directContent, thresholdPoolDurationLabel, 3);
    return encoder.getHeight();
  }
}