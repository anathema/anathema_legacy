package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionDBTCombatEncoder implements IBoxContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public SecondEditionDBTCombatEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent) {
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
    Position upperLeftCorner = new Position(graphics.getBounds().x, graphics.getBounds().getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(baseFont, 2, upperLeftCorner, graphics.getBounds().width, 3);
    encoder.addLabelledValue(graphics.getDirectContent(), 0, joinLabel, joinBattle);
    encoder.addLabelledValue(graphics.getDirectContent(), 1, dodgeLabel, dodgeDV);
    encoder.addComment(graphics.getDirectContent(), mobilityPenaltyLabel, 1);

    upperLeftCorner = new Position(graphics.getBounds().x, graphics.getBounds().getMaxY() - 25);
    encoder = new LabelledValueEncoder(baseFont, 2, upperLeftCorner, graphics.getBounds().width, 3);

    encoder.addLabelledValue(graphics.getDirectContent(), 0, knockdownLabel, knockdownThreshold, knockdownPool);
    encoder.addLabelledValue(graphics.getDirectContent(), 1, stunningLabel, stunningThreshold, stunningPool);
    encoder.addComment(graphics.getDirectContent(), thresholdPoolLabel, 0);
  }

  @Override
  public String getHeaderKey(ReportContent reportContent) {
    return "Lunar.WarForm.CombatValues";
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
