package net.sf.anathema.character.lunar.reporting.rendering.beastform;

import net.sf.anathema.character.equipment.impl.character.model.stats.CharacterStatsModifiers;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilities;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.trait.specialties.HighestSpecialty;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.LabelledValueEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.Resources;

public class SecondEditionDBTCombatEncoder implements ContentEncoder {

  private final Resources resources;

  public SecondEditionDBTCombatEncoder(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) {
    String joinLabel = resources.getString("Sheet.Combat.JoinBattle");
    String dodgeLabel = resources.getString("Sheet.Combat.DodgeDV");
    String knockdownLabel = resources.getString("Sheet.Combat.Knockdown");
    String stunningLabel = resources.getString("Sheet.Combat.Stunning");
    IBeastformModel additionalModel = (IBeastformModel) reportSession.getCharacter().getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
    ICharacterType characterType = reportSession.getCharacter().getTemplate().getTemplateType().getCharacterType();
    ICharacterStatsModifiers equipment = CharacterStatsModifiers.extractFromCharacter(reportSession.getCharacter());
    HighestSpecialty awarenessSpecialty = new HighestSpecialty( reportSession.getCharacter(), AbilityType.Awareness );
    HighestSpecialty dodgeSpecialty = new HighestSpecialty( reportSession.getCharacter(), AbilityType.Dodge );
    
    int joinBattle = CharacterUtilities.getJoinBattle(traitCollection, equipment);
    int joinBattleWithSpecialty = CharacterUtilities.getJoinBattleWithSpecialty(traitCollection, equipment, awarenessSpecialty.getValue());
    int dodgeDV = CharacterUtilities.getDodgeDv(characterType, traitCollection, equipment);
    int dodgeDVWithSpecialty = CharacterUtilities.getDodgeDvWithSpecialty(characterType, traitCollection, equipment, dodgeSpecialty.getValue());
    int knockdownThreshold = CharacterUtilities.getKnockdownThreshold(traitCollection);
    int knockdownPool = CharacterUtilities.getKnockdownPool(traitCollection);
    int stunningThreshold = CharacterUtilities.getStunningThreshold(traitCollection);
    int stunningPool = CharacterUtilities.getStunningPool(traitCollection);

    String joinSpecialtyLabel = resources.getString("Sheet.Combat.NormalSpecialty") + awarenessSpecialty;
    String dodgeSpecialtyLabel = resources.getString("Sheet.Combat.NormalSpecialty") + dodgeSpecialty;
    String thresholdPoolLabel = resources.getString("Sheet.Combat.ThresholdPool");
    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(2, upperLeftCorner, bounds.width, 3);
    displayJoinBattleWithSpecialty( graphics, encoder, joinLabel, joinBattle, joinBattleWithSpecialty, joinSpecialtyLabel);
    displayDodgeWithSpecialty(graphics, encoder, dodgeLabel, dodgeDV, dodgeDVWithSpecialty, dodgeSpecialtyLabel);
    upperLeftCorner = new Position(bounds.x, bounds.getMaxY() - 25);
    encoder = new LabelledValueEncoder(2, upperLeftCorner, bounds.width, 3);

    encoder.addLabelledValue(graphics, 0, knockdownLabel, knockdownThreshold, knockdownPool);
    encoder.addLabelledValue(graphics, 1, stunningLabel, stunningThreshold, stunningPool);
    encoder.addComment(graphics, thresholdPoolLabel, 0);
    encoder.addComment(graphics, thresholdPoolLabel, 1);
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Lunar.WarForm.CombatValues");
  }
  
  private void displayJoinBattleWithSpecialty( SheetGraphics graphics, LabelledValueEncoder encoder, String joinLabel, int joinBattle, int joinBattleWithSpecialty, String joinBattleSpecialtyLabel ) {
      if( joinBattle != joinBattleWithSpecialty ) {
        encoder.addLabelledValue(graphics, 0, joinLabel, joinBattle, joinBattleWithSpecialty);
        encoder.addComment(graphics, joinBattleSpecialtyLabel, 0);
      } else {
        encoder.addLabelledValue(graphics, 0, joinLabel, joinBattle);
      }
  }
  
  private void displayDodgeWithSpecialty( SheetGraphics graphics, LabelledValueEncoder encoder, String dodgeLabel, int dodgeDV, int dodgeDVWithSpecialty, String dodgeSpecialtyLabel ) {
      if( dodgeDV != dodgeDVWithSpecialty ) {
        encoder.addLabelledValue(graphics, 1, dodgeLabel, dodgeDV, dodgeDVWithSpecialty);
        encoder.addComment(graphics, dodgeSpecialtyLabel, 1);
      } else {
        encoder.addLabelledValue(graphics, 1, dodgeLabel, dodgeDV);
      }
  }
}
