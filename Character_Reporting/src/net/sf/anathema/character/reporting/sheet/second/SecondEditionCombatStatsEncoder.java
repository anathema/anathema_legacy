package net.sf.anathema.character.reporting.sheet.second;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.LabelledValueEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionCombatStatsEncoder extends AbstractPdfEncoder implements IPdfContentEncoder {

  private final static float PADDING = 3;

  private final IResources resources;
  private final BaseFont baseFont;
  private final IPdfTableEncoder combatRulesEncoder;

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public SecondEditionCombatStatsEncoder(IResources resources, BaseFont baseFont, IPdfTableEncoder combatRulesEncoder) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.combatRulesEncoder = combatRulesEncoder;
  }

  public String getHeaderKey() {
    return "Combat"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    float height = encodeValues(directContent, character, bounds);
    Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - height - PADDING);
    encodeRules(directContent, character, ruleBounds);
  }

  private float encodeValues(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    String joinLabel = resources.getString("Sheet.Combat.JoinBattle"); //$NON-NLS-1$
    String dodgeLabel = resources.getString("Sheet.Combat.DodgeDV"); //$NON-NLS-1$
    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
    String stunningLabel = resources.getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
    int joinBattle = CharacterUtilties.getTotalValue(character, AttributeType.Wits, AbilityType.Awareness);
    CharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    int dodgeDV = CharacterUtilties.getDodgeDv(characterType, character);
    int knockdownThreshold = CharacterUtilties.getTotalValue(character, AttributeType.Stamina, AbilityType.Resistance);
    int knockdownPool = CharacterUtilties.getKnockdownPool(character);
    int stunningThreshold = CharacterUtilties.getTotalValue(character, AttributeType.Stamina);
    int stunningPool = CharacterUtilties.getTotalValue(character, AttributeType.Stamina, AbilityType.Resistance);

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

  private void encodeRules(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException {
    combatRulesEncoder.encodeTable(directContent, character, bounds);    
  }
}