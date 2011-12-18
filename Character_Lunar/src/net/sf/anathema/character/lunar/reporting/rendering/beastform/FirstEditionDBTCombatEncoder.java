package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.FirstEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class FirstEditionDBTCombatEncoder implements IPdfContentBoxEncoder {

  private final static float PADDING = 3;
	
  private final IResources resources;
  private final BaseFont baseFont;

  public FirstEditionDBTCombatEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) {
    String initiativeLabel = resources.getString("Sheet.Combat.BaseInitiative"); //$NON-NLS-1$
    String dodgePoolLabel = resources.getString("Sheet.Combat.DodgePool"); //$NON-NLS-1$
    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
    String stunningLabel = resources.getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
    IBeastformModel additionalModel = (IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
    int initiative = CharacterUtilties.getTotalValue(
    		traitCollection,
        AttributeType.Dexterity,
        AttributeType.Wits);
    int dodgePool = CharacterUtilties.getDodgePool(character);
    int knockdownThreshold = CharacterUtilties.getTotalValue(
    		traitCollection,
        AttributeType.Stamina,
        AbilityType.Resistance);
    int knockdownPool = CharacterUtilties.getKnockdownPool(character, traitCollection, null);
    int stunningThreshold = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Stamina);
    int stunningPool = CharacterUtilties.getTotalValue(
    		traitCollection,
        AttributeType.Stamina,
        AbilityType.Resistance);
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
    
    IPdfTableEncoder rulesEncoder = new FirstEditionCombatRulesTableEncoder(resources, baseFont);
    Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - encoder.getHeight() - PADDING);
    try {
		rulesEncoder.encodeTable(directContent, character, ruleBounds);
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
	@Override
	public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
		return "Combat";
	}
	
	public boolean hasContent(IGenericCharacter character)
	  {
		  return true;
	  }
}
