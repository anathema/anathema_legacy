package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEquipmentTemplateEditModel {

  public void addStatistics(IExaltedRuleSet ruleSet, IEquipmentStats stats);

  public void removeStatistics(IExaltedRuleSet ruleSet, IEquipmentStats... stats);

  public IItemDescription getDescription();

  public IEquipmentStats[] getStats(IExaltedRuleSet ruleSet);

  public boolean isDirty();

  public void setEditTemplate(String templateId);

  public void addStatsChangeListener(IChangeListener changeListener);

  public IEquipmentTemplate createTemplate();

  public String getEditTemplateId();

  public void setNewTemplate();

  public void setMagicalMaterial(MagicalMaterial newValue);

  public MagicalMaterial getMagicalMaterial();

  public void addMagicalMaterialChangeListener(IChangeListener listener);
}