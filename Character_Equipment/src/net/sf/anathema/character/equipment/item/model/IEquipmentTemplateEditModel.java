package net.sf.anathema.character.equipment.item.model;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEquipmentTemplateEditModel {

  public void addStatistics(IExaltedRuleSet ruleSet, IEquipmentStats stats);

  public IItemDescription getDescription();

  public IEquipmentStats[] getStats(IExaltedRuleSet ruleSet);

  public boolean isDirty();

  public void setEditTemplate(String templateId);

  public void addStatsChangeListener(IChangeListener changeListener);
}