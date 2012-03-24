package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEquipmentTemplateEditModel {

  void addStatistics(IExaltedRuleSet ruleSet, IEquipmentStats stats);

  void removeStatistics(IExaltedRuleSet ruleSet, IEquipmentStats... stats);

  IItemDescription getDescription();

  IEquipmentStats[] getStats(IExaltedRuleSet ruleSet);

  boolean isDirty();

  void setEditTemplate(String templateId);

  void addStatsChangeListener(IChangeListener changeListener);

  IEquipmentTemplate createTemplate();

  String getEditTemplateId();

  void setNewTemplate();

  void setMagicalMaterial(MagicalMaterial newValue);

  void setMaterialComposition(MaterialComposition newValue);

  MagicalMaterial getMagicalMaterial();

  MaterialComposition getMaterialComposition();

  void addMagicalMaterialChangeListener(IChangeListener listener);

  void addCompositionChangeListener(IChangeListener listener);

  void replaceStatistics(IExaltedRuleSet ruleset, IEquipmentStats selectedStats, IEquipmentStats equipmentStats);
}