package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.LabelledValueEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionDBTCombatEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public SecondEditionDBTCombatEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) {
	    String joinLabel = resources.getString("Sheet.Combat.JoinBattle"); //$NON-NLS-1$
	    String dodgeLabel = resources.getString("Sheet.Combat.DodgeDV"); //$NON-NLS-1$
	    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
	    String stunningLabel = resources.getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
	    IBeastformModel additionalModel = (IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
	    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
	    int joinBattle = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Wits, AbilityType.Awareness);
	    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
	    int dodgeDV = CharacterUtilties.getDodgeDv(characterType, traitCollection, character.getEquipmentModifiers());
	    int knockdownThreshold = CharacterUtilties.getTotalValue(
	        traitCollection,
	        AttributeType.Stamina,
	        AbilityType.Resistance);
	    int knockdownPool = CharacterUtilties.getKnockdownPool(character, traitCollection, null);
	    int stunningThreshold = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Stamina);
	    int stunningPool = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Stamina, AbilityType.Resistance);

	    String mobilityPenaltyLabel = "-" + resources.getString("Sheet.Combat.MobilityPenalty"); //$NON-NLS-1$ //$NON-NLS-2$
	    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
	    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
	    LabelledValueEncoder encoder = new LabelledValueEncoder(baseFont, 2, upperLeftCorner, bounds.width, 3);
	    encoder.addLabelledValue(directContent, 0, joinLabel, joinBattle);
	    encoder.addLabelledValue(directContent, 1, dodgeLabel, dodgeDV);
	    encoder.addComment(directContent, mobilityPenaltyLabel, 1);
	    
	    upperLeftCorner = new Position(bounds.x, bounds.getMaxY() - 25);
	    encoder = new LabelledValueEncoder(baseFont, 2, upperLeftCorner, bounds.width, 3);
	    
	    encoder.addLabelledValue(directContent, 0, knockdownLabel, knockdownThreshold, knockdownPool);
	    encoder.addLabelledValue(directContent, 1, stunningLabel, stunningThreshold, stunningPool);
	    encoder.addComment(directContent, thresholdPoolLabel, 0);
  }

	@Override
	public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
		return "Lunar.WarForm.CombatValues";
	}
	
	public boolean hasContent(IGenericCharacter character)
	  {
		  return true;
	  }
}