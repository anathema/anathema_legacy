package net.sf.anathema.character.reporting.pdf.rendering.boxes.social;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.impl.social.InvestigationSocialAttack;
import net.sf.anathema.character.generic.impl.social.PerformanceSocialAttack;
import net.sf.anathema.character.generic.impl.social.PresenceSocialAttack;
import net.sf.anathema.character.generic.social.ISocialCombatStats;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.social.DeceptionStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.social.HonestyStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.social.SocialCombatNameStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.social.SocialRateStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.social.SocialSpeedStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.general.stats.AbstractFixedLineStatsTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SocialCombatStatsTableEncoder extends AbstractFixedLineStatsTableEncoder<ISocialCombatStats> {

  private final IResources resources;

  public SocialCombatStatsTableEncoder(IResources resources) {
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<ISocialCombatStats>[] createStatsGroups(ReportSession session) {
    return new IStatsGroup[]{new SocialCombatNameStatsGroup(resources), new SocialSpeedStatsGroup(
            resources), new HonestyStatsGroup(resources), new DeceptionStatsGroup(resources), new SocialRateStatsGroup(
            resources)};
  }

  @Override
  protected int getLineCount(ReportSession session) {
    return 3;
  }

  @Override
  protected ISocialCombatStats[] getPrintStats(ReportSession session) {
    IGenericCharacter character = session.getCharacter();
    IGenericTraitCollection traitCollection = character.getTraitCollection();
    IEquipmentAdditionalModel equipment = (IEquipmentAdditionalModel) character.getAdditionalModel(
            IEquipmentAdditionalModelTemplate.ID);
    ICharacterStatsModifiers equipmentModifiers = equipment.createStatsModifiers(character);
    return new ISocialCombatStats[]{new PresenceSocialAttack(traitCollection, equipmentModifiers), new PerformanceSocialAttack(
            traitCollection, equipmentModifiers), new InvestigationSocialAttack(traitCollection, equipmentModifiers)};
  }
}
