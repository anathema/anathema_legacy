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
    return resources.getString("EquipmentStats." + type.name()); //$NON-NLS-1$
  }

  public String getOffensiveLabel() {
    return resources.getString("Equipment.Creation.SelectType.OffensiveLabel") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getDefensiveLabel() {
    return resources.getString("Equipment.Creation.SelectType.DefensiveLabel") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getTypeChoiceTitle() {
    return resources.getString("Equipment.Creation.SelectType.PageTitle"); //$NON-NLS-1$
  }

  public String getTypeChoiceMessage() {
    return resources.getString("Equipment.Creation.SelectType.DefaultMessage"); //$NON-NLS-1$    
  }
}