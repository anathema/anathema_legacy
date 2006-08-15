package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import javax.swing.Icon;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentTypeChoiceProperties {

  private final IResources resources;

  public EquipmentTypeChoiceProperties(IResources resources) {
    this.resources = resources;
  }

  public Icon getIcon(EquipmentStatisticsType type) {
    return new EquipmentUI(resources).getIcon(type);
  }

  public String getLabel(EquipmentStatisticsType type) {
    // todo vom (26.04.2006) (sieroux): Richtige i18n
    return type.name();
  }

  public String getOffensiveLabel() {
    return "Offensive:";
  }

  public String getDefensiveLabel() {
    return "Defensive:";
  }

  public String getTypeChoiceTitle() {
    return "Statistics Type Selection";
  }

  public String getTypeChoiceMessage() {
    return "Please select the type of the statstics to add.";
  }
}