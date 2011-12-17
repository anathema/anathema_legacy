package net.sf.anathema.character.reporting.sheet.second;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.util.LabelledValueEncoder;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionCombatValueEncoder implements IPdfContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public SecondEditionCombatValueEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public float encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    String joinLabel = resources.getString("Sheet.Combat.JoinBattle"); //$NON-NLS-1$
    String dodgeLabel = resources.getString("Sheet.Combat.DodgeDV"); //$NON-NLS-1$
    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
    String stunningLabel = resources.getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
    IGenericTraitCollection traitCollection = character.getTraitCollection();
    int joinBattle = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Wits, AbilityType.Awareness);
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    IEquipmentModifiers equipmentModifiers = character.getEquipmentModifiers();
    int dodgeDV = CharacterUtilties.getDodgeDv(characterType, traitCollection, equipmentModifiers);
    int knockdownThreshold = CharacterUtilties.getTotalValue(
        traitCollection,
        AttributeType.Stamina,
        AbilityType.Resistance);
    int knockdownPool = CharacterUtilties.getKnockdownPool(character, equipmentModifiers);
    int stunningThreshold = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Stamina);
    int stunningPool = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Stamina, AbilityType.Resistance);

    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(baseFont, 4, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(directContent, 0, joinLabel, joinBattle);
    encoder.addLabelledValue(directContent, 1, dodgeLabel, dodgeDV);
    encoder.addLabelledValue(directContent, 2, knockdownLabel, knockdownThreshold, knockdownPool);
    encoder.addLabelledValue(directContent, 3, stunningLabel, stunningThreshold, stunningPool);
    String mobilityPenaltyLabel = "-" + resources.getString("Sheet.Combat.MobilityPenalty"); //$NON-NLS-1$ //$NON-NLS-2$
    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
    encoder.addComment(directContent, mobilityPenaltyLabel, 1);
    encoder.addComment(directContent, thresholdPoolLabel, 2);
    encoder.addComment(directContent, thresholdPoolLabel, 3);
    return encoder.getHeight();
  }
}
