package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

public class EquipmentTypeChoiceProperties {

  private final Resources resources;

  public EquipmentTypeChoiceProperties(Resources resources) {
    this.resources = resources;
  }

  public RelativePath getIcon(EquipmentStatisticsType type) {
    return new EquipmentUI().getIcon(type);
  }

  public String getLabel(EquipmentStatisticsType type) {
    return resources.getString("EquipmentStats." + type.name());
  }

  public String getOffensiveLabel() {
    return resources.getString("Equipment.Creation.SelectType.OffensiveLabel") + ":";
  }

  public String getDefensiveLabel() {
    return resources.getString("Equipment.Creation.SelectType.DefensiveLabel") + ":";
  }

  public String getOtherLabel() {
    return resources.getString("Equipment.Creation.SelectType.OtherLabel") + ":";
  }

  public String getTypeChoiceTitle() {
    return resources.getString("Equipment.Creation.SelectType.PageTitle");
  }

  public String getTypeChoiceMessage() {
    return resources.getString("Equipment.Creation.SelectType.DefaultMessage");
  }
}