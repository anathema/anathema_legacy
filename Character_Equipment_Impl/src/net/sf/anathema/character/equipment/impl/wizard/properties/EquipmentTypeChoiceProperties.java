package net.sf.anathema.character.equipment.impl.wizard.properties;

import javax.swing.Icon;

import net.sf.anathema.character.equipment.impl.model.EquipmentStatisticsType;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentTypeChoiceProperties implements IEquipmentTypeChoiceProperties {

  private static final int ICON_SIZE = 20;
  private final IResources resources;

  public EquipmentTypeChoiceProperties(IResources resources) {
    this.resources = resources;
  }

  public Icon getIcon(EquipmentStatisticsType type) {
    return resources.getImageIcon(type.name() + ICON_SIZE + ".png"); //$NON-NLS-1$
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
    return "Statistics type Selection";
  }

  public String getTypeChoiceMessage() {
    return "Please select the type of the statstics to add.";
  }
}