package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.equipment.editor.model.IItemDescription;
import net.sf.anathema.lib.control.ChangeListener;

import java.util.List;

public interface IEquipmentTemplateEditModel {

  void addStatistics(IEquipmentStats stats);

  void removeStatistics(IEquipmentStats... stats);

  IItemDescription getDescription();

  List<IEquipmentStats> getStats();

  boolean isDirty();

  void setEditTemplate(String templateId);

  void addStatsChangeListener(ChangeListener changeListener);

  IEquipmentTemplate createTemplate();

  String getEditTemplateId();

  void setNewTemplate();
  
  void copyNewTemplate( String salt );

  void setMagicalMaterial(MagicalMaterial newValue);

  void setMaterialComposition(MaterialComposition newValue);
  
  void addCostChangeListener(ChangeListener listener);
  
  void setCost(ItemCost cost);
  
  ItemCost getCost();

  MagicalMaterial getMagicalMaterial();

  MaterialComposition getMaterialComposition();

  void addMagicalMaterialChangeListener(ChangeListener listener);

  void addCompositionChangeListener(ChangeListener listener);

  void replaceStatistics(IEquipmentStats selectedStats, IEquipmentStats equipmentStats);
}