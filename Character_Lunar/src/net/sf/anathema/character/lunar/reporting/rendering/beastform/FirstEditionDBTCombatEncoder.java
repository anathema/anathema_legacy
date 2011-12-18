package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.FirstEditionCombatRulesTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.Graphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

public class FirstEditionDBTCombatEncoder implements IBoxContentEncoder {

  private final static float PADDING = 3;
	
  private final IResources resources;
  private final BaseFont baseFont;

  public FirstEditionDBTCombatEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public void encode(Graphics graphics, ReportContent reportContent) {
    String initiativeLabel = resources.getString("Sheet.Combat.BaseInitiative"); //$NON-NLS-1$
    String dodgePoolLabel = resources.getString("Sheet.Combat.DodgePool"); //$NON-NLS-1$
    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
    String stunningLabel = resources.getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
    IBeastformModel additionalModel = (IBeastformModel) reportContent.getCharacter().getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
    int initiative = CharacterUtilties.getTotalValue(
    		traitCollection,
        AttributeType.Dexterity,
        AttributeType.Wits);
    int dodgePool = CharacterUtilties.getDodgePool(reportContent.getCharacter());
    int knockdownThreshold = CharacterUtilties.getTotalValue(
    		traitCollection,
        AttributeType.Stamina,
        AbilityType.Resistance);
    int knockdownPool = CharacterUtilties.getKnockdownPool(reportContent.getCharacter(), traitCollection, null);
    int stunningThreshold = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Stamina);
    int stunningPool = CharacterUtilties.getTotalValue(
    		traitCollection,
        AttributeType.Stamina,
        AbilityType.Resistance);
    int stunningDuration = Math.max(0, 6 - stunningThreshold);

    Position upperLeftCorner = new Position(graphics.getBounds().x, graphics.getBounds().getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(baseFont, 4, upperLeftCorner, graphics.getBounds().width, 3);
    encoder.addLabelledValue(graphics.getDirectContent(), 0, initiativeLabel, initiative);
    encoder.addLabelledValue(graphics.getDirectContent(), 1, dodgePoolLabel, dodgePool);
    encoder.addLabelledValue(graphics.getDirectContent(), 2, knockdownLabel, knockdownThreshold, knockdownPool);
    encoder.addLabelledValue(graphics.getDirectContent(), 3, stunningLabel, stunningThreshold, stunningPool, stunningDuration);
    String mobilityPenaltyLabel = "-" + resources.getString("Sheet.Combat.MobilityPenalty"); //$NON-NLS-1$ //$NON-NLS-2$
    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
    String thresholdPoolDurationLabel = resources.getString("Sheet.Combat.ThresholdPoolDuration"); //$NON-NLS-1$
    encoder.addComment(graphics.getDirectContent(), mobilityPenaltyLabel, 1);
    encoder.addComment(graphics.getDirectContent(), thresholdPoolLabel, 2);
    encoder.addComment(graphics.getDirectContent(), thresholdPoolDurationLabel, 3);
    
    IPdfTableEncoder rulesEncoder = new FirstEditionCombatRulesTableEncoder(resources, baseFont);
    Bounds ruleBounds = new Bounds(graphics.getBounds().x, graphics.getBounds().y, graphics.getBounds().width, graphics.getBounds().height - encoder.getHeight() - PADDING);
    try {
		rulesEncoder.encodeTable(graphics.getDirectContent(), reportContent, ruleBounds);
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
	@Override
	public String getHeaderKey(ReportContent reportContent) {
		return "Combat";
	}
	
	public boolean hasContent(ReportContent content)
	  {
		  return true;
	  }
}
