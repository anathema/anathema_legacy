package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.item.EquipmentStatsUIConfiguration;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleEquipmentItemRenderer implements EquipmentItemRenderer {
  private final EquipmentStatsUIConfiguration configuration;

  public SimpleEquipmentItemRenderer(Resources resources) {
    this.configuration = new EquipmentStatsUIConfiguration(resources);
  }

  @Override
  public String getLabel(IEquipmentItem item) {
    return item.getTemplateId();
  }

  @Override
  public Collection<RelativePathWithDisabling> getIcons(IEquipmentItem item) {
    ArrayList<RelativePathWithDisabling> paths = new ArrayList<>();
    for (IEquipmentStats stats : item.getStats()) {
      RelativePathWithDisabling pathWithDisabling = new RelativePathWithDisabling();
      pathWithDisabling.path = configuration.getIconsRelativePath(stats);
      pathWithDisabling.enabled = item.isPrintEnabled(stats);
      paths.add(pathWithDisabling);
    }
    return paths;
  }
}