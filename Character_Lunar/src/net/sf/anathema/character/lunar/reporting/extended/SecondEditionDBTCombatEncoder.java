package net.sf.anathema.character.lunar.reporting.extended;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.util.LabelledValueEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

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
	    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
	    IEquipmentModifiers equipment = character.getEquipmentModifiers();
	    
	    int joinBattle = CharacterUtilties.getJoinBattle(traitCollection, equipment);
	    int dodgeDV = CharacterUtilties.getDodgeDv(characterType, traitCollection, equipment);
	    int knockdownThreshold = CharacterUtilties.getKnockdownThreshold(traitCollection, equipment);
	    int knockdownPool = CharacterUtilties.getKnockdownPool(character, traitCollection, equipment);
	    int stunningThreshold = CharacterUtilties.getStunningThreshold(traitCollection, equipment);
	    int stunningPool = CharacterUtilties.getStunningPool(traitCollection, equipment);

	    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
	    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
	    LabelledValueEncoder encoder = new LabelledValueEncoder(baseFont, 2, upperLeftCorner, bounds.width, 3);
	    encoder.addLabelledValue(directContent, 0, joinLabel, joinBattle);
	    encoder.addLabelledValue(directContent, 1, dodgeLabel, dodgeDV);
	    
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