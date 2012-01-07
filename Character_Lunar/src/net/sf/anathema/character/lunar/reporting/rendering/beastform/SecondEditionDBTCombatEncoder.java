package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionDBTCombatEncoder implements ContentEncoder {

  private final IResources resources;

  public SecondEditionDBTCombatEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
    String joinLabel = resources.getString("Sheet.Combat.JoinBattle"); //$NON-NLS-1$
    String dodgeLabel = resources.getString("Sheet.Combat.DodgeDV"); //$NON-NLS-1$
    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
    String stunningLabel = resources.getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
    IBeastformModel additionalModel = (IBeastformModel) reportContent.getCharacter().getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
    ICharacterType characterType = reportContent.getCharacter().getTemplate().getTemplateType().getCharacterType();
    IEquipmentModifiers equipment = reportContent.getCharacter().getEquipmentModifiers();

    int joinBattle = CharacterUtilties.getJoinBattle(traitCollection, equipment);
    int dodgeDV = CharacterUtilties.getDodgeDv(characterType, traitCollection, equipment);
    int knockdownThreshold = CharacterUtilties.getKnockdownThreshold(traitCollection, equipment);
    int knockdownPool = CharacterUtilties.getKnockdownPool(reportContent.getCharacter(), traitCollection, equipment);
    int stunningThreshold = CharacterUtilties.getStunningThreshold(traitCollection, equipment);
    int stunningPool = CharacterUtilties.getStunningPool(traitCollection, equipment);

    String mobilityPenaltyLabel = "-" + resources.getString("Sheet.Combat.MobilityPenalty"); //$NON-NLS-1$ //$NON-NLS-2$
    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(2, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(graphics, 0, joinLabel, joinBattle);
    encoder.addLabelledValue(graphics, 1, dodgeLabel, dodgeDV);
    encoder.addComment(graphics, mobilityPenaltyLabel, 1);

    upperLeftCorner = new Position(bounds.x, bounds.getMaxY() - 25);
    encoder = new LabelledValueEncoder(2, upperLeftCorner, bounds.width, 3);

    encoder.addLabelledValue(graphics, 0, knockdownLabel, knockdownThreshold, knockdownPool);
    encoder.addLabelledValue(graphics, 1, stunningLabel, stunningThreshold, stunningPool);
    encoder.addComment(graphics, thresholdPoolLabel, 0);
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Lunar.WarForm.CombatValues");
  }
}
