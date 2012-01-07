package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.first.rendering.combat.CombatRulesTableEncoder;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionDBTCombatEncoder implements ContentEncoder {

  private final static float PADDING = 3;

  private final IResources resources;

  public FirstEditionDBTCombatEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) {
    String initiativeLabel = resources.getString("Sheet.Combat.BaseInitiative"); //$NON-NLS-1$
    String dodgePoolLabel = resources.getString("Sheet.Combat.DodgePool"); //$NON-NLS-1$
    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
    String stunningLabel = resources.getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
    IBeastformModel additionalModel = (IBeastformModel) reportContent.getCharacter().getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
    int initiative = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Dexterity, AttributeType.Wits);
    int dodgePool = CharacterUtilties.getDodgePool(reportContent.getCharacter());
    int knockdownThreshold = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Stamina, AbilityType.Resistance);
    int knockdownPool = CharacterUtilties.getKnockdownPool(reportContent.getCharacter(), traitCollection, null);
    int stunningThreshold = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Stamina);
    int stunningPool = CharacterUtilties.getTotalValue(traitCollection, AttributeType.Stamina, AbilityType.Resistance);
    int stunningDuration = Math.max(0, 6 - stunningThreshold);

    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(4, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(graphics, 0, initiativeLabel, initiative);
    encoder.addLabelledValue(graphics, 1, dodgePoolLabel, dodgePool);
    encoder.addLabelledValue(graphics, 2, knockdownLabel, knockdownThreshold, knockdownPool);
    encoder.addLabelledValue(graphics, 3, stunningLabel, stunningThreshold, stunningPool, stunningDuration);
    String mobilityPenaltyLabel = "-" + resources.getString("Sheet.Combat.MobilityPenalty"); //$NON-NLS-1$ //$NON-NLS-2$
    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
    String thresholdPoolDurationLabel = resources.getString("Sheet.Combat.ThresholdPoolDuration"); //$NON-NLS-1$
    encoder.addComment(graphics, mobilityPenaltyLabel, 1);
    encoder.addComment(graphics, thresholdPoolLabel, 2);
    encoder.addComment(graphics, thresholdPoolDurationLabel, 3);

    ITableEncoder rulesEncoder = new CombatRulesTableEncoder();
    Bounds ruleBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - encoder.getHeight() - PADDING);
    try {
      rulesEncoder.encodeTable(graphics, reportContent, ruleBounds);
    } catch (DocumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Combat");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
