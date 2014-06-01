package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.item.EquipmentStatsUIConfiguration;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.lib.file.RelativePath;

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
  public Collection<RelativePath> getIcons(IEquipmentItem item) {
    ArrayList<RelativePath> paths = new ArrayList<>();
    for (IEquipmentStats stats : item.getStats()) {
      paths.add(configuration.getIconsRelativePath(stats));
    }
    return paths;
  }
}
