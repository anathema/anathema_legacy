package net.sf.anathema.character.equipment.impl.wizard.properties;

import javax.swing.Icon;

import net.sf.anathema.character.equipment.impl.model.EquipmentStatisticsType;

public interface IEquipmentTypeChoiceProperties {

  public String getLabel(EquipmentStatisticsType type);
  
  public Icon getIcon(EquipmentStatisticsType type);

  public String getOffensiveLabel();

  public String getDefensiveLabel();

  public String getTypeChoiceTitle();

  public String getTypeChoiceMessage();
 }