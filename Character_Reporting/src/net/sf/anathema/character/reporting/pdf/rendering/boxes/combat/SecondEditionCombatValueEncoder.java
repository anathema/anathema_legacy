package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentEncoder;
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
    IEquipmentModifiers equipment = character.getEquipmentModifiers();
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();

    int joinBattle = CharacterUtilties.getJoinBattle(traitCollection, equipment);
    int dodgeDV = CharacterUtilties.getDodgeDv(characterType, traitCollection, equipment);
    int knockdownThreshold = CharacterUtilties.getKnockdownThreshold(traitCollection, equipment);
    int knockdownPool = CharacterUtilties.getKnockdownPool(character, traitCollection, equipment);
    int stunningThreshold = CharacterUtilties.getStunningThreshold(traitCollection, equipment);
    int stunningPool = CharacterUtilties.getStunningPool(traitCollection, equipment);

    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(baseFont, 4, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(directContent, 0, joinLabel, joinBattle);
    encoder.addLabelledValue(directContent, 1, dodgeLabel, dodgeDV);
    encoder.addLabelledValue(directContent, 2, knockdownLabel, knockdownThreshold, knockdownPool);
    encoder.addLabelledValue(directContent, 3, stunningLabel, stunningThreshold, stunningPool);
    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
    encoder.addComment(directContent, thresholdPoolLabel, 2);
    encoder.addComment(directContent, thresholdPoolLabel, 3);
    return encoder.getHeight();
  }
}
