package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.item.EquipmentStatsUIConfiguration;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

import java.util.ArrayList;
import java.util.Collection;

public class FilteringEquipmentItemRenderer implements EquipmentItemRenderer {
  private final EquipmentStatsUIConfiguration configuration;
  private final EquipmentHeroEvaluator heroEvaluator;

  public FilteringEquipmentItemRenderer(Resources resources, EquipmentHeroEvaluator heroEvaluator) {
    this.heroEvaluator = heroEvaluator;
    this.configuration = new EquipmentStatsUIConfiguration(resources);
  }

  @Override
  public String getLabel(IEquipmentItem item) {
    return item.getTitle();
  }

  @Override
  public Collection<RelativePathWithDisabling> getIcons(IEquipmentItem item) {
    ArrayList<RelativePathWithDisabling> paths = new ArrayList<>();
    StatsPresentationFactory strategy = new StatsPresentationFactory(heroEvaluator, item);
    for (IEquipmentStats stats : item.getStats()) {
      if (!strategy.choosePresentationStrategy(stats).shouldStatsBeShown()) {
        continue;
      }
      RelativePathWithDisabling pathWithDisabling = new RelativePathWithDisabling();
      pathWithDisabling.path = configuration.getIconsRelativePath(stats);
      pathWithDisabling.enabled = item.isPrintEnabled(stats);
      paths.add(pathWithDisabling);
    }
    return paths;
  }
}