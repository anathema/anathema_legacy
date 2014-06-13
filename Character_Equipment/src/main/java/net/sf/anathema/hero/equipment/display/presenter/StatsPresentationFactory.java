package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

public class StatsPresentationFactory {

  private final EquipmentHeroEvaluator evaluator;
  private final IEquipmentItem item;

  public StatsPresentationFactory(EquipmentHeroEvaluator evaluator, IEquipmentItem item) {
    this.evaluator = evaluator;
    this.item = item;
  }

  public StatsPresentationStrategy choosePresentationStrategy(IEquipmentStats equipment) {
    if (equipment instanceof ArtifactStats) {
      return new ArtifactPresentationStrategy((ArtifactStats) equipment, evaluator, item);
    } else {
      return new DefaultPresentationStrategy(equipment);
    }
  }
}
