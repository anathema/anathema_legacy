package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactAttuneType;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactStats;

public class ArtifactPresentationStrategy implements StatsPresentationStrategy {
  private final ArtifactStats stats;
  private final EquipmentHeroEvaluator heroEvaluator;
  private final IEquipmentItem item;

  public ArtifactPresentationStrategy(ArtifactStats stats, EquipmentHeroEvaluator heroEvaluator, IEquipmentItem item) {
    this.stats = stats;
    this.heroEvaluator = heroEvaluator;
    this.item = item;
  }

  @Override
  public boolean shouldStatsBeShown() {
    ArtifactAttuneType[] attuneTypes = heroEvaluator.getAttuneTypes(item);
    for (ArtifactAttuneType type : attuneTypes) {
      if (stats.getAttuneType() == type) {
        return true;
      }
    }
    return false;
  }
}
