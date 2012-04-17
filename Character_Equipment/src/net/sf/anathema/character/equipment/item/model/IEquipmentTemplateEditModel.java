package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEquipmentTemplateEditModel {

  void addStatistics(IEquipmentStats stats);

  void removeStatistics(IEquipmentStats... stats);

  IItemDescription getDescription();

  IEquipmentStats[] getStats();

  boolean isDirty();

  void setEditTemplate(String templateId);

  void addStatsChangeListener(IChangeListener changeListener);

  IEquipmentTemplate createTemplate();

  String getEditTemplateId();

  void setNewTemplate();
  
  void copyNewTemplate( String salt );

  void setMagicalMaterial(MagicalMaterial newValue);

  void setMaterialComposition(MaterialComposition newValue);
  
  void addCostChangeListener(IChangeListener listener);
  
  void setCost(ItemCost cost);
  
  ItemCost getCost();

  MagicalMaterial getMagicalMaterial();

  MaterialComposition getMaterialComposition();

  void addMagicalMaterialChangeListener(IChangeListener listener);

  void addCompositionChangeListener(IChangeListener listener);

  void replaceStatistics(IEquipmentStats selectedStats, IEquipmentStats equipmentStats);
}